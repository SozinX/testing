package org.sozinx.dao;

import org.sozinx.model.Answer;
import org.sozinx.model.Question;
import org.sozinx.model.Test;

import java.util.List;

public interface AnswerDAO {
    Answer getAnswerById(long id);

    List<Answer> getAnswerByQuestion(Question question);

    boolean addAnswer(Answer answer);

    boolean updateAnswer(Answer answer, String[] params);

    boolean deleteAnswer(Answer answer);

    boolean deleteAnswerByQuestion(Question question);

    int getCountOfAnswers(Test test);
}
