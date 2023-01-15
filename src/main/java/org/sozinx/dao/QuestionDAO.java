package org.sozinx.dao;

import org.sozinx.model.Question;
import org.sozinx.model.Test;

import java.util.List;

public interface QuestionDAO {
    Question getQuestionById(long id);

    List<Question> getQuestionByTest(Test test);

    boolean addQuestion(Question question);

    boolean updateQuestion(Question question, String[] params);

    boolean deleteQuestion(Question question);

}
