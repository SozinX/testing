package org.sozinx.dao;

import org.sozinx.model.Answer;
import org.sozinx.model.Question;

import java.util.List;

public interface AnswerDAO {
    Answer getAnswerById(long id);
    List<Answer> getAnswerByQuestion(Question question);
    boolean addAnswer(Answer question);
    boolean updateAnswer(Answer question);
    boolean deleteAnswer(Answer question);
}
