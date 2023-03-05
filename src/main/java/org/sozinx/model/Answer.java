package org.sozinx.model;

/**
 * Model of Answer from answer table.
 *
 * @author Ostap Petruniak
 * @see Question
 * @since 1.0
 */
public class Answer {
    private long id;
    private String answer;
    private int correctness;
    private Question question;

    /**
     * Answer constructor.
     *
     * @param id          answer's id or 0 if it is a new object for table
     * @param answer      answer's text
     * @param correctness answer's correctness
     * @param question    question which this answer present
     */
    public Answer(long id, String answer, int correctness, Question question) {
        this.id = id;
        this.answer = answer;
        this.correctness = correctness;
        this.question = question;
    }

    /**
     * Getting answer's id.
     *
     * @return answer's id
     */
    public long getId() {
        return id;
    }

    /**
     * Setting answer's id.
     *
     * @param id new answer's id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Getting answer's text.
     *
     * @return answer's text
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * Setting answer's text.
     *
     * @param answer new answer's text
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * Getting correctness.
     *
     * @return answer's correctness
     */
    public int getCorrectness() {
        return correctness;
    }

    /**
     * Setting correctness.
     *
     * @param correctness new answer's correctness
     */
    public void setCorrectness(int correctness) {
        this.correctness = correctness;
    }

    /**
     * Getting question.
     *
     * @return object Question which this answer present
     */
    public Question getQuestion() {
        return question;
    }

    /**
     * Setting question.
     *
     * @param question new question which this answer will present
     */
    public void setQuestion(Question question) {
        this.question = question;
    }
}
