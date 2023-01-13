package org.sozinx.dao;

import org.sozinx.model.Result;
import org.sozinx.model.Test;
import org.sozinx.model.User;

import java.util.List;

public interface ResultDAO {
    List<Result> getResultByUser(User user);
    List<Result> getResultByTest(Test test);
    boolean addResult(Result result);
    boolean deleteResult(Result result);
    boolean updateResult(Result result);
}
