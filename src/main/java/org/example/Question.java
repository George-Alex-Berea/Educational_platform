package org.example;

import java.time.LocalDateTime;

public abstract class Question<answerType> implements Gradable {
    private String questionText;
    private String publisherName;
    private LocalDateTime publishedDate;
    private int difficulty;
    private double maxGrade;
    protected answerType correctAnswer;

    public Question(String questionText, String publisherName, LocalDateTime publishedDate, int difficulty, double maxGrade) {
        this.questionText = questionText;
        this.publisherName = publisherName;
        this.publishedDate = publishedDate;
        this.difficulty = difficulty;
        this.maxGrade = maxGrade;
    }

    public abstract Correctness checkAnswer(answerType studentInput);

    public abstract answerType parseInput(String answer);

    public Correctness checkAnswerFromString(String input) {
        return checkAnswer(parseInput(input));
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public LocalDateTime getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDateTime publishedDate) {
        this.publishedDate = publishedDate;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public double getMaxGrade() {
        return maxGrade;
    }

    public void setMaxGrade(double maxGrade) {
        this.maxGrade = maxGrade;
    }

    public answerType getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(answerType correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String examString() {
        return maxGrade + " | " +
                questionText + " | " +
                difficulty + " | " +
                correctAnswer;
    }

    @Override
    public String toString() {
        return publishedDate.format(Main.dtf) + " | " +
                questionText + " | " +
                correctAnswer + " | " +
                difficulty + " | " +
                maxGrade + " | " +
                publisherName;
    }
}
