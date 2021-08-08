package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ConsoleChat {
	private final String path;
	private final String botAnswers;
	private static final String OUT = "закончить";
	private static final String STOP = "стоп";
	private static final String CONTINUE = "продолжить";

	public ConsoleChat(String path, String botAnswers) {
		this.path = path;
		this.botAnswers = botAnswers;
	}

	public void run() {
		List<String> answers = readPhrases();
		List<String> log = new ArrayList<>();
		boolean out = false;
		boolean shouldAnswer = true;
		try (BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {
			while (!out) {
				String input = userInput.readLine();
				log.add(input);

				if (input.equals(STOP)) {
					shouldAnswer = false;
				} else if (input.equals(CONTINUE)) {
					shouldAnswer = true;
				} else if (input.equals(OUT)) {
					out = true;
					saveLog(log);
				} else if (shouldAnswer) {
					String answer = answers.get((int) (Math.random() * answers.size()));
					log.add(answer);
					System.out.println(answer);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private List<String> readPhrases() {
		List<String> answers = new ArrayList<>();
		try (BufferedReader in = new BufferedReader(new FileReader(botAnswers))) {
			in.lines().forEach(answers::add);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return answers;
	}

	private void saveLog(List<String> log) {
		try (PrintWriter out = new PrintWriter(new FileWriter(path))) {
			log.forEach(out::println);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ConsoleChat cc = new ConsoleChat("", "");
		cc.run();
	}
}
