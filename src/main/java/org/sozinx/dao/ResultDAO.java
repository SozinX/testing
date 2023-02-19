package org.sozinx.dao;

import org.sozinx.model.Result;
import org.sozinx.model.Test;
import org.sozinx.model.User;

import java.util.List;

public interface ResultDAO {
    @SuppressWarnings("unused")
    List<Result> getResultByUser(User user);

    @SuppressWarnings("unused")
    List<Result> getResultByTest(Test test);

    Result getResultByUserAndTest(User user, Test test);

    void addResult(Result result);

    @SuppressWarnings("unused")
    boolean deleteResult(Result result);

    @SuppressWarnings("unused")
    boolean updateResult(Result result, int points);

    @SuppressWarnings("unused")
    boolean deleteResultByUser(User user);
}
