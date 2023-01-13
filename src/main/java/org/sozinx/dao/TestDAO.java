package org.sozinx.dao;

import org.sozinx.model.Test;

import java.util.List;

public interface TestDAO {
    Test getTestById(long id);
    Test getTestByName(String name);
    boolean addTest(Test test);
    boolean deleteTest(Test test);
    boolean updateUser(Test test);
    List<Test> getTestByName();
    List<Test> getTestBySubject();
    List<Test> getTestByNameASC();
    List<Test> getTestByNameDESC();
    List<Test> getTestBySubjectASC();
    List<Test> getTestBySubjectDESC();
    List<Test> getTestByPopularityASC();
    List<Test> getTestByPopularityDESC();
    List<Test> getTestByDateASC();
    List<Test> getTestByDateDESC();
    List<Test> getTestBySubject(String subject);
}

