package org.sozinx.service;

import org.sozinx.dao.*;

/**
 * Service is using to create instance in which it allows to use all DAO in project.
 *
 * @author Ostap Petruniak
 * @see UserDAO
 * @see TestDAO
 * @see AnswerDAO
 * @see QuestionDAO
 * @see LogDAO
 * @see ResultDAO
 * @see BlockDAO
 * @see LevelDAO
 * @see RoleDAO
 * @see TypeDAO
 * @since 1.0
 */
public interface DataBaseService {
    /**
     * Getting User DAO.
     *
     * @return object UserDAO
     */
    UserDAO getUserManager();

    /**
     * Getting Test DAO.
     *
     * @return object TestDAO
     */
    TestDAO getTestManager();

    /**
     * Getting Answer DAO.
     *
     * @return object AnswerDAO
     */
    AnswerDAO getAnswerManager();

    /**
     * Getting Block DAO.
     *
     * @return object BlockDAO
     */
    BlockDAO getBlockManager();

    /**
     * Getting Level DAO.
     *
     * @return object LevelDAO
     */
    LevelDAO getLevelManager();

    /**
     * Getting Log DAO.
     *
     * @return object LogDAO
     */
    LogDAO getLogManager();

    /**
     * Getting Question DAO.
     *
     * @return object QuestionDAO
     */
    QuestionDAO getQuestionManager();

    /**
     * Getting Result DAO.
     *
     * @return object ResultDAO
     */
    ResultDAO getResultManager();

    /**
     * Getting Role DAO.
     *
     * @return object RoleDAO
     */
    RoleDAO getRoleManager();

    /**
     * Getting Type DAO.
     *
     * @return object TypeDAO
     */
    TypeDAO getTypeManager();
}
