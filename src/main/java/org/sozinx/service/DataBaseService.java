package org.sozinx.service;

import org.sozinx.dao.*;

public interface DataBaseService {
    UserDAO getUserManager();

    TestDAO getTestManager();

    AnswerDAO getAnswerManager();

    BlockDAO getBlockManager();

    LevelDAO getLevelManager();

    LogDAO getLogManager();

    QuestionDAO getQuestionManager();

    ResultDAO getResultManager();

    RoleDAO getRoleManager();

    TypeDAO getTypeManager();
}
