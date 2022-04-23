package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {
    private Connection connection;
    private Properties properties;

    public TableEditor(Properties properties) throws SQLException, ClassNotFoundException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws ClassNotFoundException, SQLException {
        Class.forName(properties.getProperty("driver"));
        connection = DriverManager.getConnection(
            properties.getProperty("url"),
            properties.getProperty("name"),
            properties.getProperty("password")
        );
    }

    public void createTable(String tableName) throws SQLException {
        String sql = String.format("CREATE TABLE %s();", tableName);
        executeStatement(sql);
        System.out.println(getTableScheme(connection, tableName));
    }

    public void dropTable(String tableName) throws SQLException {
        String sql = String.format("DROP TABLE %s;", tableName);
        executeStatement(sql);
        System.out.printf("Table %s was deleted", tableName);
    }

    public void addColumn(String tableName, String columnName, String type) throws SQLException {
        String sql = String.format("ALTER TABLE %s ADD %s %s;", tableName, columnName, type);
        executeStatement(sql);
        System.out.println(getTableScheme(connection, tableName));
    }

    public void dropColumn(String tableName, String columnName) throws SQLException {
        String sql = String.format("ALTER TABLE %s DROP COLUMN %s;", tableName, columnName);
        executeStatement(sql);
        System.out.println(getTableScheme(connection, tableName));
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws SQLException {
        String sql = String.format("ALTER TABLE %s RENAME COLUMN %s TO %s;", tableName, columnName, newColumnName);
        executeStatement(sql);
        System.out.println(getTableScheme(connection, tableName));
    }

    private void executeStatement(String sql) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

    public static String getTableScheme(Connection connection, String tableName) throws SQLException {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                    metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Config config = new Config("./app.properties");
        config.load();

        Properties properties = new Properties();
        properties.setProperty("driver", config.value("hibernate.connection.driver_class"));
        properties.setProperty("url", config.value("hibernate.connection.url"));
        properties.setProperty("name", config.value("hibernate.connection.username"));
        properties.setProperty("password", config.value("hibernate.connection.password"));

        try (TableEditor tableEditor = new TableEditor(properties)) {
            tableEditor.createTable("some_table");
            tableEditor.addColumn("some_table", "id", "text");
            tableEditor.renameColumn("some_table", "id", "name");
            tableEditor.dropColumn("some_table", "name");
            tableEditor.dropTable("some_table");
        }
    }
}
