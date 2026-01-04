package org.example;

import java.time.LocalDateTime;

public class FillInTheBlankQuestion extends Question<String> {

    public FillInTheBlankQuestion(String questionText, String publisherName, LocalDateTime publishedDate, int difficulty, double maxGrade, String correctAnswer) {
        super(questionText, publisherName, publishedDate, difficulty, maxGrade);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public Correctness checkAnswer(String studentInput) {
        if (correctAnswer.equals(studentInput)) {
            return Correctness.CORRECT;
        }

        int maxLen = correctAnswer.length() + 2;
        int minLen = correctAnswer.length() - 2;
        if (studentInput.length() > maxLen || studentInput.length() < minLen) {
            return Correctness.INCORRECT;
        }

        if (studentInput.contains(correctAnswer) || correctAnswer.contains(studentInput)) {
            return Correctness.PARTIALLY_CORRECT;
        }

        return Correctness.INCORRECT;
    }

    @Override
    public String parseInput(String studentInput) {
        return studentInput;
    }

    @Override
    public double grade(Correctness correctness) {
        return switch (correctness) {
            case CORRECT -> 1.0 * this.getMaxGrade();
            case PARTIALLY_CORRECT -> 0.5  * this.getMaxGrade();
            case INCORRECT -> 0.0;
        };
    }
}
