package org.sozinx.service;

import org.sozinx.dao.*;

public class DataBaseServiceImpl implements DataBaseService{
    private static DataBaseServiceImpl service;
    private final UserDAO userManager;
    private final TestDAO testManager;
    private final AnswerDAO answerManager;
    private final BlockDAO blockManager;
    private final LevelDAO levelManager;
    private final LogDAO logManager;
    private final QuestionDAO questionManager;
    private final ResultDAO resultManager;
    private final RoleDAO roleManager;
    private final TypeDAO typeManager;

    private DataBaseServiceImpl() {
        userManager = new UserDAOImpl();
        testManager = new TestDAOImpl();
        answerManager = new AnswerDAOImpl();
        blockManager = new BlockDAOImpl();
        levelManager = new LevelDAOImpl();
        logManager = new LogDAOImpl();
        questionManager = new QuestionDAOImpl();
        resultManager = new ResultDAOImpl();
        roleManager = new RoleDAOImpl();
        typeManager = new TypeDAOImpl();
    }

    public static synchronized DataBaseServiceImpl getInstance() {
        if (service == null) {
            return new DataBaseServiceImpl();
        }
        return service;
    }

    public UserDAO getUserManager() {
        return userManager;
    }

    public TestDAO getTestManager() {
        return testManager;
    }

    public AnswerDAO getAnswerManager() {
        return answerManager;
    }

    public BlockDAO getBlockManager() {
        return blockManager;
    }

    public LevelDAO getLevelManager() {
        return levelManager;
    }

    public LogDAO getLogManager() {
        return logManager;
    }

    public QuestionDAO getQuestionManager() {
        return questionManager;
    }

    public ResultDAO getResultManager() {
        return resultManager;
    }

    public RoleDAO getRoleManager() {
        return roleManager;
    }

    public TypeDAO getTypeManager() {
        return typeManager;
    }
}
