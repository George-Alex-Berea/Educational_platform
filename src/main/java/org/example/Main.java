package org.example;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm");

    public static void main(String[] args) {
        try {
            System.out.println(args[0]);
            String directoryPath = "src/main/resources/" + args[0] + "/";
            PrintWriter consoleWriter = new PrintWriter(directoryPath + "console.out", StandardCharsets.UTF_8);
            Scanner scanner = new Scanner(new File(directoryPath + "input.in"));
            Platform platform = new Platform(consoleWriter);
            boolean running = true;
            while(running) {
                String line = scanner.nextLine();
                String[] tokens = line.split(" \\| ");
                if (tokens.length < 2) {
                    System.out.println("Invalid input");
                    continue;
                }
                switch (tokens[1]) {
                    case "create_exam" -> {
                        platform.createExam(
                                LocalDateTime.parse(tokens[0], dtf),
                                tokens[2],
                                LocalDateTime.parse(tokens[3], dtf),
                                LocalDateTime.parse(tokens[4], dtf)
                        );
                    }
                    case "add_question" -> {
                        platform.addQuestion(
                                LocalDateTime.parse(tokens[0], dtf),
                                tokens[2], tokens[3], tokens[4],
                                Integer.parseInt(tokens[5]),
                                Double.parseDouble((tokens[6])),
                                tokens[7], tokens[8]
                        );
                    }
                    case "list_questions_history" -> {
                        platform.listQuestionsHistory(
                                LocalDateTime.parse(tokens[0], dtf),
                                directoryPath, tokens[2]
                        );
                    }
                    case "print_exam" -> {
                        platform.printExam(
                                LocalDateTime.parse(tokens[0], dtf),
                                directoryPath, tokens[2]
                        );
                    }
                    case "register_student" -> {
                        platform.registerStudent(
                                LocalDateTime.parse(tokens[0], dtf),
                                tokens[2], tokens[3]
                        );
                    }
                    case "submit_exam" -> {
                        String[] answerTokens = Arrays.copyOfRange(tokens, 4, tokens.length);
                        Collection<String> answers = Arrays.asList(answerTokens);
                        platform.submitExam(
                                LocalDateTime.parse(tokens[0], dtf),
                                tokens[2], tokens[3], answers
                        );
                    }
                    case "show_student_score" -> {
                        platform.showStudentScore(
                                LocalDateTime.parse(tokens[0], dtf),
                                tokens[2], tokens[3]
                        );
                    }
                    case "generate_report" -> {
                        platform.generateReport(
                                LocalDateTime.parse(tokens[0], dtf),
                                directoryPath, tokens[2]
                        );
                    }
                    case "exit" -> {
                        running = false;
                    }
                }
            }
            consoleWriter.close();
            scanner.close();
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }
}