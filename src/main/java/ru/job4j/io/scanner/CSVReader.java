package ru.job4j.io.scanner;

import ru.job4j.io.ArgsName;

import java.io.*;
import java.util.*;

public class CSVReader {
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("All arguments are absent. Use java -jar target/csvReader.jar -path=PATH -delimiter=DELIMITER_TYPE -out=OUT_FOLDER -filter=FILTER_TYPE");
		} else if (args.length == 1) {
			System.out.println("Arguments(delimiter, out, filter) are absent. Use java -jar target/csvReader.jar -path=PATH -delimiter=DELIMITER_TYPE -out=OUT_FOLDER -filter=FILTER_TYPE");
		} else if (args.length == 2) {
			System.out.println("Arguments(out, filter) are absent. Use java -jar target/csvReader.jar -path=PATH -delimiter=DELIMITER_TYPE -out=OUT_FOLDER -filter=FILTER_TYPE");
		} else if (args.length == 3) {
			System.out.println("Argument filter is absent. Use java -jar target/csvReader.jar -path=PATH -delimiter=DELIMITER_TYPE -out=OUT_FOLDER -filter=FILTER_TYPE");
		}
		CSVReader csvReader = new CSVReader();
		ArgsName argsName = ArgsName.of(args);
		Map<String, List<String>> map = csvReader.readCSVFile(argsName.get("path"), argsName.get("delimiter"));

		if (argsName.get("out").equals("stdout")) {
			csvReader.stdout(argsName.get("filter"), map);
		} else {
			csvReader.writeToFile(map, argsName.get("out"), argsName.get("filter"));
		}
	}

	public Map<String, List<String>> readCSVFile(String path, String delimiter) {
		Map<String, List<String>> table = new LinkedHashMap<>();
		try (Scanner scanner = new Scanner(new File(path))) {
			while (scanner.hasNextLine()) {
				String[] some = scanner.nextLine().split(delimiter);
				if (table.isEmpty()) {
					for (String s : some) {
						table.put(s, new ArrayList<>());
					}
				} else {
					int count = 0;
					for (Map.Entry<String, List<String>> m : table.entrySet()) {
						m.getValue().add(some[count++]);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return table;
	}

	public void stdout(String filter, Map<String, List<String>> table) {
		for (Map.Entry<String, List<String>> column : table.entrySet()) {
			if (filter.contains(column.getKey())) {
				System.out.println(column.getValue());
			}
		}
	}

	public void writeToFile(Map<String, List<String>> table, String outPath, String filter) {
		try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(outPath))) {
			for (Map.Entry<String, List<String>> column : table.entrySet()) {
				if (filter.contains(column.getKey())) {
					out.write(column.getValue().toString().getBytes());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
