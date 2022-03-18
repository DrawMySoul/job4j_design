package ru.job4j.io.scanner;

import ru.job4j.io.ArgsName;

import java.io.*;
import java.util.*;

public class CSVReader {
	private Map<Integer, List<String>> table = new HashMap<>();

	public void readCSVFile(String path, String delimiter, String filter) {
		try (Scanner scanner = new Scanner(new File(path))) {
			while (scanner.hasNextLine()) {
				String[] line = scanner.nextLine().split(delimiter);
				if (table.isEmpty()) {
					for (int i = 0; i < line.length; i++) {
						if (filter.contains(line[i])) {
							table.put(i, new ArrayList<>());
						}
					}
				} else {
					for (int i = 0; i < line.length; i++) {
						if (table.containsKey(i)) {
							table.get(i).add(line[i]);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void write(String out) {
		try (PrintStream printStream = out.equals("stdout") ? new PrintStream(System.out) : new PrintStream(new FileOutputStream(out))) {
			table.values().forEach(printStream::println);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void handle(ArgsName argsName) throws Exception {
		CSVReader csvReader = new CSVReader();
		csvReader.readCSVFile(argsName.get("path"), argsName.get("delimiter"), argsName.get("filter"));
		csvReader.write(argsName.get("out"));
	}

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

		ArgsName argsName = ArgsName.of(args);
		CSVReader csvReader = new CSVReader();
		csvReader.readCSVFile(argsName.get("path"), argsName.get("delimiter"), argsName.get("filter"));
		//csvReader.write(argsName.get("out"));
	}
}
