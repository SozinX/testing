package org.sozinx.dao;

import org.sozinx.model.Test;
import org.sozinx.model.User;

import java.util.List;

public interface TestDAO {
    Test getTestById(long id);

    boolean addTest(Test test);

    boolean updateTest(Test test, String[] params);

    boolean addPopularity(Test test);

    List<Test> getFilerResult(String name, String subject, String level, String orderColumn, String order, String page);
    double getAllFilterTests(String name, String subject, String level, String orderColumn, String order);
}

