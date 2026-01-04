package org.example;

import java.time.LocalDateTime;

public class MultipleChoiceQuestion extends Question<ResponseOption> {

    public MultipleChoiceQuestion(String questionText, String publisherName, LocalDateTime publishedDate, int difficulty, double maxGrade, ResponseOption correctAnswer) {
        super(questionText, publisherName, publishedDate, difficulty, maxGrade);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public Correctness checkAnswer(ResponseOption studentInput) {
        if (correctAnswer.equals(studentInput)) {
            return Correctness.CORRECT;
        }

        return Correctness.INCORRECT;
    }

    @Override
    public ResponseOption parseInput(String input) {
        return ResponseOption.fromString(input);
    }

    @Override
    public double grade(Correctness correctness) {
        return switch (correctness) {
            case CORRECT -> 1.0 *  this.getMaxGrade();
            case PARTIALLY_CORRECT -> 0.0;
            case INCORRECT -> 0.0;
        };
    }
}
