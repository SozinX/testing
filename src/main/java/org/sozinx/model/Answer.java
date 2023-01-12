package org.sozinx.model;

public class Answer {
    private long id;
    private String answer;
    private int correctness;
    private Question question;

    public Answer(long id, String answer, int correctness, Question question) {
        this.id = id;
        this.answer = answer;
        this.correctness = correctness;
        this.question = question;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getCorrectness() {
        return correctness;
    }

    public void setCorrectness(int correctness) {
        this.correctness = correctness;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
