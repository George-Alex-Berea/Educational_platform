package org.example;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.*;

public class Exam {
    private String name;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Set<Question<?>> questionsTimeSorted;
    private Set<Question<?>> questionsDifficultySorted;
    private Map<Student, Double> studentScores;
    private static final Comparator<Question<?>> comparatorTime = new Comparator<>() {
        @Override
        public int compare(Question o1, Question o2) {
            if (o1.getPublishedDate().isBefore(o2.getPublishedDate()))
                return -1;
            if (o1.getPublishedDate().isAfter(o2.getPublishedDate()))
                return 1;
            return o1.getPublisherName().compareTo(o2.getPublisherName());
        }
    };
    private static final Comparator<Question<?>> comparatorDifficulty = new Comparator<>() {
        @Override
        public int compare(Question o1, Question o2) {
            if (o1.getDifficulty() < o2.getDifficulty())
                return -1;
            if (o1.getDifficulty() > o2.getDifficulty())
                return 1;
            return o1.getQuestionText().compareTo(o2.getQuestionText());
        }
    };

    public Exam(String name, LocalDateTime startTime, LocalDateTime endTime) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.questionsTimeSorted = new TreeSet<>(comparatorTime);
        this.questionsDifficultySorted = new TreeSet<>(comparatorDifficulty);
        this.studentScores = new TreeMap<>();
    }

    public void addQuestion(Question<?> q) {
        this.questionsTimeSorted.add(q);
        this.questionsDifficultySorted.add(q);
    }

    public void printQuestions(
            LocalDateTime timestamp, String directoryPath
    ) throws IOException {
        PrintWriter writer = new PrintWriter(directoryPath + "questions_history_" + this.name + "_" + timestamp.format(Main.dtf) + ".out", StandardCharsets.UTF_8);
        for (Question<?> q : questionsTimeSorted) {
            writer.println(q);
        }
        writer.close();
    }

    public void printExam(
            LocalDateTime timestamp, String directoryPath
    ) throws IOException {
        PrintWriter writer = new PrintWriter(directoryPath + "print_exam_" + this.name + "_" + timestamp.format(Main.dtf) + ".out", StandardCharsets.UTF_8);
        for (Question<?> q : questionsDifficultySorted) {
            writer.println(q.examString());
        }
        writer.close();
    }

    public void registerSubmission(
            LocalDateTime timestamp, Student student, Collection<String> answers
    ) {
        if (timestamp.isBefore(this.startTime) || timestamp.isAfter(this.endTime)) {
            studentScores.put(student, 0.0d);
            student.addExam(this, 0.0d);
            throw new SubmissionOutsideTimeIntervalException(
                    timestamp.format(Main.dtf) +
                    " | Submission outside of time interval for student " +
                    student.getName()
            );
        }
        double score = 0.0d;
        Iterator<String> itr = answers.iterator();
        for (Question<?> q : questionsDifficultySorted) {
            Correctness c =  q.checkAnswerFromString(itr.next());
            double grade = q.grade(c);
            score += grade;
        }
        studentScores.put(student, score);
        student.addExam(this, score);
    }

    public void printReport(
            LocalDateTime timestamp, String directoryPath
    ) throws IOException {
        PrintWriter writer = new PrintWriter(directoryPath + "exam_report_" + this.name + "_" + timestamp.format(Main.dtf) + ".out", StandardCharsets.UTF_8);
        for (Student student : studentScores.keySet()) {
            writer.println(student.getName() + " | " +  String.format("%.2f", studentScores.get(student)));
        }
        writer.close();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Set<Question<?>> getQuestionsDifficultySorted() {
        return questionsDifficultySorted;
    }

    public void setQuestionsDifficultySorted(Set<Question<?>> questionsDifficultySorted) {
        this.questionsDifficultySorted = questionsDifficultySorted;
    }
}
