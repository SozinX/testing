package org.sozinx.dao;

import org.sozinx.model.*;


import java.util.List;

public interface LogDAO {
    List<Log> getLogByUser(User user);

    List<Log> getLogByTest(Test test);

    void addLog(Log log);

    boolean deleteLogByQuestion(Question question);

    boolean deleteLogByAnswer(Answer answer);

    boolean deleteLogByUser(User user);

    int getSumOfPoints(Test test, User user);

    int getCountOfZeros(Test test, User user);

}
