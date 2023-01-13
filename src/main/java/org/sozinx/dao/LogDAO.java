package org.sozinx.dao;

import org.sozinx.model.Log;
import org.sozinx.model.Test;
import org.sozinx.model.User;


import java.util.List;

public interface LogDAO {
    List<Log> getLogByUser(User user);
    List<Log> getLogByTest(Test test);
    boolean addLog(Log log);

}
