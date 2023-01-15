package org.sozinx.dao;

import org.sozinx.model.*;


import java.util.List;

public interface LogDAO {
    List<Log> getLogByUser(User user);

    List<Log> getLogByTest(Test test);

    boolean addLog(Log log);

    boolean deleteLogByQuestion(Question question);

    boolean deleteLogByAnswer(Answer answer);

    boolean deleteLogByUser(User user);


}
