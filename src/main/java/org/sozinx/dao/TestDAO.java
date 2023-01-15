package org.sozinx.dao;

import org.sozinx.model.Test;
import org.sozinx.model.User;

import java.util.List;

public interface TestDAO {
    Test getTestById(long id);

    boolean addTest(Test test);

    boolean updateTest(Test test, String[] params);

    boolean addPopularity(Test test);

    /*List<Test> getTestByUserAndNameASC();
    List<Test> getTestByUserAndNameDESC();
    List<Test> getTestByUserAndSubjectASC();
    List<Test> getTestByUserAndSubjectDESC();
    List<Test> getTestByUserAndPopularityASC();
    List<Test> getTestByUserAndPopularityDESC();
    List<Test> getTestByUserAndDateASC();
    List<Test> getTestByUserAndDateDESC();
    List<Test> getTestByUserAndLevelASC();
    List<Test> getTestByUserAndLevelDESC();
    List<Test> getTestByUserAndElectNameOrderByNameASC(String name);
    List<Test> getTestByUserAndElectNameOrderByNameDESC(String name);
    List<Test> getTestByUserAndElectNameOrderBySubjectASC(String name);
    List<Test> getTestByUserAndElectNameOrderBySubjectDESC(String name);
    List<Test> getTestByUserAndElectNameOrderByPopularityASC(String name);
    List<Test> getTestByUserAndElectNameOrderByPopularityDESC(String name);
    List<Test> getTestByUserAndElectNameOrderByDateASC(String name);
    List<Test> getTestByUserAndElectNameOrderByDateDESC(String name);
    List<Test> getTestByUserAndElectNameOrderByLevelASC(String name);
    List<Test> getTestByUserAndElectNameOrderByLevelDESC(String name);
    List<Test> getTestByUserAndElectSubjectOrderByNameASC(String subject);
    List<Test> getTestByUserAndElectSubjectOrderByNameDESC(String subject);
    List<Test> getTestByUserAndElectSubjectOrderBySubjectASC(String subject);
    List<Test> getTestByUserAndElectSubjectOrderBySubjectDESC(String subject);
    List<Test> getTestByUserAndElectSubjectOrderByPopularityASC(String subject);
    List<Test> getTestByUserAndElectSubjectOrderByPopularityDESC(String subject);
    List<Test> getTestByUserAndElectSubjectOrderByDateASC(String subject);
    List<Test> getTestByUserAndElectSubjectOrderByDateDESC(String subject);
    List<Test> getTestByUserAndElectSubjectOrderByLevelASC(String subject);
    List<Test> getTestByUserAndElectSubjectOrderByLevelDESC(String subject);
    List<Test> getTestByUserAndElectLevelOrderByNameASC(int level);
    List<Test> getTestByUserAndElectLevelOrderByNameDESC(int level);
    List<Test> getTestByUserAndElectLevelOrderBySubjectASC(int level);
    List<Test> getTestByUserAndElectLevelOrderBySubjectDESC(int level);
    List<Test> getTestByUserAndElectLevelOrderByPopularityASC(int level);
    List<Test> getTestByUserAndElectLevelOrderByPopularityDESC(int level);
    List<Test> getTestByUserAndElectLevelOrderByDateASC(int level);
    List<Test> getTestByUserAndElectLevelOrderByDateDESC(int level);
    List<Test> getTestByUserAndElectNameAndSubjectOrderByNameASC(String name, String subject);
    List<Test> getTestByUserAndElectNameAndSubjectOrderByNameDESC(String name, String subject);
    List<Test> getTestByUserAndElectNameAndSubjectOrderBySubjectASC(String name, String subject);
    List<Test> getTestByUserAndElectNameAndSubjectOrderBySubjectDESC(String name, String subject);
    List<Test> getTestByUserAndElectNameAndSubjectOrderByPopularityASC(String name, String subject);
    List<Test> getTestByUserAndElectNameAndSubjectOrderByPopularityDESC(String name, String subject);
    List<Test> getTestByUserAndElectNameAndSubjectOrderByDateASC(String name, String subject);
    List<Test> getTestByUserAndElectNameAndSubjectOrderByDateDESC(String name, String subject);
    List<Test> getTestByUserAndElectNameAndSubjectOrderByLevelASC(String name, String subject);
    List<Test> getTestByUserAndElectNameAndSubjectOrderByLevelDESC(String name, String subject);
    List<Test> getTestByUserAndElectNameAndLevelOrderByNameASC(String name, int level);
    List<Test> getTestByUserAndElectNameAndLevelOrderByNameDESC(String name, int level);
    */
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

