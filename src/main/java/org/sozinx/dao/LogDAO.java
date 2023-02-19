package org.sozinx.dao;

import org.sozinx.model.*;


import java.util.List;

public interface LogDAO {
    @SuppressWarnings("unused")
    List<Log> getLogByUser(User user);

    @SuppressWarnings("unused")
    List<Log> getLogByTest(Test test);

    void addLog(Log log);

    void deleteLogByQuestion(Question question);

    @SuppressWarnings("unused")
    boolean deleteLogByAnswer(Answer answer);

    @SuppressWarnings("unused")
    boolean deleteLogByUser(User user);

    int getSumOfPoints(Test test, User user);

    int getCountOfZeros(Test test, User user);

}
