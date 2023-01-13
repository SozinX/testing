package org.sozinx.dao;

import org.sozinx.model.Test;

import java.util.List;

public interface TestDAO {
    Test getTestById(long id);
    boolean addTest(Test test);
    boolean updateTest(Test test);
    boolean addPopularity(Test test);
    List<Test> getTestByNameASC();
    List<Test> getTestByNameDESC();
    List<Test> getTestBySubjectASC();
    List<Test> getTestBySubjectDESC();
    List<Test> getTestByPopularityASC();
    List<Test> getTestByPopularityDESC();
    List<Test> getTestByDateASC();
    List<Test> getTestByDateDESC();
    List<Test> getTestByLevelASC();
    List<Test> getTestByLevelDESC();
    List<Test> getTestByElectNameOrderByNameASC(String name);
    List<Test> getTestByElectNameOrderByNameDESC(String name);
    List<Test> getTestByElectNameOrderBySubjectASC(String name);
    List<Test> getTestByElectNameOrderBySubjectDESC(String name);
    List<Test> getTestByElectNameOrderByPopularityASC(String name);
    List<Test> getTestByElectNameOrderByPopularityDESC(String name);
    List<Test> getTestByElectNameOrderByDateASC(String name);
    List<Test> getTestByElectNameOrderByDateDESC(String name);
    List<Test> getTestByElectNameOrderByLevelASC(String name);
    List<Test> getTestByElectNameOrderByLevelDESC(String name);
    List<Test> getTestByElectSubjectOrderByNameASC(String subject);
    List<Test> getTestByElectSubjectOrderByNameDESC(String subject);
    List<Test> getTestByElectSubjectOrderBySubjectASC(String subject);
    List<Test> getTestByElectSubjectOrderBySubjectDESC(String subject);
    List<Test> getTestByElectSubjectOrderByPopularityASC(String subject);
    List<Test> getTestByElectSubjectOrderByPopularityDESC(String subject);
    List<Test> getTestByElectSubjectOrderByDateASC(String subject);
    List<Test> getTestByElectSubjectOrderByDateDESC(String subject);
    List<Test> getTestByElectSubjectOrderByLevelASC(String subject);
    List<Test> getTestByElectSubjectOrderByLevelDESC(String subject);
    List<Test> getTestByElectLevelOrderByNameASC(int level);
    List<Test> getTestByElectLevelOrderByNameDESC(int level);
    List<Test> getTestByElectLevelOrderBySubjectASC(int level);
    List<Test> getTestByElectLevelOrderBySubjectDESC(int level);
    List<Test> getTestByElectLevelOrderByPopularityASC(int level);
    List<Test> getTestByElectLevelOrderByPopularityDESC(int level);
    List<Test> getTestByElectLevelOrderByDateASC(int level);
    List<Test> getTestByElectLevelOrderByDateDESC(int level);
    List<Test> getTestByElectNameAndSubjectOrderByNameASC(String name, String subject);
    List<Test> getTestByElectNameAndSubjectOrderByNameDESC(String name, String subject);
    List<Test> getTestByElectNameAndSubjectOrderBySubjectASC(String name, String subject);
    List<Test> getTestByElectNameAndSubjectOrderBySubjectDESC(String name, String subject);
    List<Test> getTestByElectNameAndSubjectOrderByPopularityASC(String name, String subject);
    List<Test> getTestByElectNameAndSubjectOrderByPopularityDESC(String name, String subject);
    List<Test> getTestByElectNameAndSubjectOrderByDateASC(String name, String subject);
    List<Test> getTestByElectNameAndSubjectOrderByDateDESC(String name, String subject);
    List<Test> getTestByElectNameAndSubjectOrderByLevelASC(String name, String subject);
    List<Test> getTestByElectNameAndSubjectOrderByLevelDESC(String name, String subject);
    List<Test> getTestByElectNameAndLevelOrderByNameASC(String name, int level);
    List<Test> getTestByElectNameAndLevelOrderByNameDESC(String name, int level);
    List<Test> getTestByElectNameAndLevelOrderBySubjectASC(String name, int level);
    List<Test> getTestByElectNameAndLevelOrderBySubjectDESC(String name, int level);
    List<Test> getTestByElectNameAndLevelOrderByPopularityASC(String name, int level);
    List<Test> getTestByElectNameAndLevelOrderByPopularityDESC(String name, int level);
    List<Test> getTestByElectNameAndLevelOrderByDateASC(String name, int level);
    List<Test> getTestByElectNameAndLevelOrderByDateDESC(String name, int level);
    List<Test> getTestByElectSubjectAndLevelOrderByNameASC(String subject, int level);
    List<Test> getTestByElectSubjectAndLevelOrderByNameDESC(String subject, int level);
    List<Test> getTestByElectSubjectAndLevelOrderBySubjectASC(String subject, int level);
    List<Test> getTestByElectSubjectAndLevelOrderBySubjectDESC(String subject, int level);
    List<Test> getTestByElectSubjectAndLevelOrderByPopularityASC(String subject, int level);
    List<Test> getTestByElectSubjectAndLevelOrderByPopularityDESC(String subject, int level);
    List<Test> getTestByElectSubjectAndLevelOrderByDateASC(String subject, int level);
    List<Test> getTestByElectSubjectAndLevelOrderByDateDESC(String subject, int level);
    List<Test> getTestByElectNameAndSubjectAndLevelOrderByNameASC(String name, String subject, int level);
    List<Test> getTestByElectNameAndSubjectAndLevelOrderByNameDESC(String name, String subject, int level);
    List<Test> getTestByElectNameAndSubjectAndLevelOrderBySubjectASC(String name, String subject, int level);
    List<Test> getTestByElectNameAndSubjectAndLevelOrderBySubjectDESC(String name, String subject, int level);
    List<Test> getTestByElectNameAndSubjectAndLevelOrderByPopularityASC(String name, String subject, int level);
    List<Test> getTestByElectNameAndSubjectAndLevelOrderByPopularityDESC(String name, String subject, int level);
    List<Test> getTestByElectNameAndSubjectAndLevelOrderByDateASC(String name, String subject, int level);
    List<Test> getTestByElectNameAndSubjectAndLevelOrderByDateDESC(String name, String subject, int level);
}

