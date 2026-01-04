package org.example;

import java.time.LocalDateTime;

public class OpenEndedQuestion extends Question<String> {

    public OpenEndedQuestion(String questionText, String publisherName, LocalDateTime publishedDate, int difficulty, double maxGrade, String correctAnswer) {
        super(questionText, publisherName, publishedDate, difficulty, maxGrade);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public Correctness checkAnswer(String studentInput) {
        if (correctAnswer.equals(studentInput)) {
            return Correctness.CORRECT;
        }

        int maxLen = correctAnswer.length() * 13 / 10;
        int minLen = correctAnswer.length() * 7 / 10;

        if (studentInput.length() > maxLen || studentInput.length() < minLen) {
            return Correctness.INCORRECT;
        }

        if (studentInput.contains(correctAnswer) || correctAnswer.contains(studentInput)) {
            return Correctness.PARTIALLY_CORRECT;
        }

        return Correctness.INCORRECT;
    }

    @Override
    public String parseInput(String input) {
        return input;
    }

    @Override
    public double grade(Correctness correctness) {
        return switch (correctness) {
            case CORRECT -> 1.0 *  this.getMaxGrade();
            case PARTIALLY_CORRECT -> 0.7 * this.getMaxGrade();
            case INCORRECT -> 0.0;
        };
    }
}
