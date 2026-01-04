package org.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.*;

public class Platform {
    private Map<String, Exam> exams;
    private Map<String, Student> students;
    private PrintWriter consoleWriter;

    public Platform(PrintWriter consoleWriter) {
        this.exams = new HashMap<>();
        this.students = new HashMap<>();
        this.consoleWriter = consoleWriter;
    }

    public void createExam(
            LocalDateTime timestamp, String examName,
            LocalDateTime startDate, LocalDateTime endDate
    ) {
        Exam exam = new Exam(examName, startDate, endDate);
        this.exams.put(examName, exam);
    }

    public void addQuestion(
            LocalDateTime timestamp, String questionType,
            String examName, String publisherName, int difficulty,
            double maxGrade, String questionText, String correctAnswer
    ) {
        Question<?> question = switch (questionType) {
            case "open_ended" -> new OpenEndedQuestion(
                    questionText, publisherName, timestamp, difficulty, maxGrade, correctAnswer
            );

            case "fill_in_the_blank" -> new FillInTheBlankQuestion(
                    questionText, publisherName, timestamp, difficulty, maxGrade, correctAnswer
            );

            case "multiple_choice" -> new MultipleChoiceQuestion(
                    questionText, publisherName, timestamp, difficulty, maxGrade,
                    ResponseOption.fromString(correctAnswer)
            );

            default -> throw new IllegalArgumentException("Invalid question type: " + questionType);
        };

        Exam targetExam = exams.get(examName);
        targetExam.addQuestion(question);
    }

    public void listQuestionsHistory(
            LocalDateTime timestamp, String directoryPath,
            String examName
    ) throws IOException {
        Exam targetExam = exams.get(examName);
        targetExam.printQuestions(timestamp, directoryPath);
    }

    public void printExam(
            LocalDateTime timestamp, String directoryPath,
            String examName
    ) throws IOException {
        Exam targetExam = exams.get(examName);
        targetExam.printExam(timestamp, directoryPath);
    }

    public void registerStudent(
            LocalDateTime timestamp, String studentName,
            String studentGroup
    ) {
        Student student = new Student(studentName, studentGroup);
        students.put(studentName, student);
    }

    public void submitExam(
            LocalDateTime timestamp, String examName,
            String studentName, Collection<String> answers
    ) {
        try {
            Exam exam = exams.get(examName);
            Student student = students.get(studentName);
            exam.registerSubmission(timestamp, student, answers);
        } catch (SubmissionOutsideTimeIntervalException e) {
            consoleWriter.println(e.getMessage());
            consoleWriter.flush();
        }
    }

    public void showStudentScore(
            LocalDateTime timestamp, String studentName, String examName
    ) {
        Student student = students.get(studentName);
        Exam exam = exams.get(examName);
        double score = student.getScore(exam);
        consoleWriter.println(
                timestamp.format(Main.dtf) + " | The score of " +
                studentName + " for exam " + examName + " is " +
                String.format("%.2f", score)
        );
        consoleWriter.flush();
    }

    public void generateReport(
            LocalDateTime timestamp, String directoryPath,
            String examName
    ) throws IOException {
        Exam exam = exams.get(examName);
        exam.printReport(timestamp, directoryPath);
    }
}
