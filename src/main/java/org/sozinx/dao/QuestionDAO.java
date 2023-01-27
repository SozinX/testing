package org.sozinx.dao;

import org.sozinx.model.Question;
import org.sozinx.model.Test;

import java.util.List;

public interface QuestionDAO {
    Question getQuestionById(long id);

    List<Question> getQuestionByTest(Test test);

    void addQuestion(Question question);

    void updateQuestion(Question question, String[] params);

    void deleteQuestion(Question question);

    Question getQuestionByName(String name, long test, long id);

    Question getLastQuestionByTest(Test test);

}
