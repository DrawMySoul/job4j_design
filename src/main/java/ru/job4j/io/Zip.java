package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
	public static void packFiles(List<File> sources, File target) {
		Path rootPath = target.toPath().toAbsolutePath();
		try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
			for (File source : sources) {
				Path currentPath = rootPath.relativize(source.toPath().toAbsolutePath());
				zip.putNextEntry(new ZipEntry(currentPath.toFile().getPath()));
				try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
					zip.write(out.readAllBytes());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void packSingleFile(File source, File target) {
		try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
			zip.putNextEntry(new ZipEntry(source.getPath()));
			try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
				zip.write(out.readAllBytes());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		if (args.length == 0) {
			throw new IllegalArgumentException("All arguments are absent. Usage java -jar pack.jar -d=FOLDER_DIRECTORY -e=EXCLUDE_TYPE -o=OUTPUT_FOLDER");
		} else if (args.length == 1) {
			throw new IllegalArgumentException("Exclude type and output are absent.Usage java -jar pack.jar -d=FOLDER_DIRECTORY -e=EXCLUDE_TYPE -o=OUTPUT_FOLDER");
		} else if (args.length == 2) {
			throw new IllegalArgumentException("Output is absent. Usage java -jar pack.jar -d=FOLDER_DIRECTORY -e=EXCLUDE_TYPE -o=OUTPUT_FOLDER ");
		}

		if (!new File(args[0].split("=")[1]).exists()) {
			throw new IllegalArgumentException("File is not exist");
		}
		ArgsName zip = ArgsName.of(args);
		List<File> sources = Search.search(
			Paths.get(zip.get("d")),
			p -> !p.toFile().getName().endsWith(zip.get("e"))
		).stream()
			.map(Path::toFile)
			.collect(Collectors.toList());

		packFiles(sources, new File(zip.get("o")));
	}
}

