package org.sozinx.dao;

import org.sozinx.model.Question;
import org.sozinx.model.Test;
import org.sozinx.model.User;

import java.util.List;

public interface TestDAO {
    Test getTestById(long id);

    void addTest(Test test);

    void updateTest(Test test, String[] params);

    boolean addPopularity(Test test);

    void openTest(Test test);

    Test getLastAddedTestByOwner(User owner);

    List<Test> getFilterResult(String name, String subject, String level, String orderColumn, String order, String page);

    double getAllFilterTests(String name, String subject, String level, String orderColumn, String order);

    List<Test> getFilterResultForOwner(String name, String subject, String level, String orderColumn, String order, String owner, String page);

    double getAllFilterTestsForOwner(String name, String subject, String level, String orderColumn, String owner, String order);

    Test getTestByNameAndOwner(String name, long id, long testId);
}

