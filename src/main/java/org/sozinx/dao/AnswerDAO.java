package org.sozinx.dao;

import org.sozinx.model.Answer;
import org.sozinx.model.Question;

import java.util.List;

public interface AnswerDAO {
    Answer getAnswerById(long id);

    List<Answer> getAnswerByQuestion(Question question);

    void addAnswer(Answer answer);

    void updateAnswer(Answer answer, String[] params);

    void deleteAnswer(Answer answer);

    void deleteAnswerByQuestion(Question question);
}
