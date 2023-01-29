package org.sozinx.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataBaseServiceImplTest {

    @Test
    void getInstanceNotNull() {
        assertNotNull(DataBaseServiceImpl.getInstance());
    }

    @Test
    void getUserManagerNotNull() {
        assertNotNull(DataBaseServiceImpl.getInstance().getUserManager());
    }

    @Test
    void getTestManagerNotNull() {
        assertNotNull(DataBaseServiceImpl.getInstance().getTestManager());
    }

    @Test
    void getAnswerManagerNotNull() {
        assertNotNull(DataBaseServiceImpl.getInstance().getAnswerManager());
    }

    @Test
    void getBlockManagerNotNull() {
        assertNotNull(DataBaseServiceImpl.getInstance().getBlockManager());
    }

    @Test
    void getLevelManagerNotNull() {
        assertNotNull(DataBaseServiceImpl.getInstance().getLevelManager());
    }

    @Test
    void getLogManagerNotNull() {
        assertNotNull(DataBaseServiceImpl.getInstance().getLogManager());
    }

    @Test
    void getQuestionManagerNotNull() {
        assertNotNull(DataBaseServiceImpl.getInstance().getQuestionManager());
    }

    @Test
    void getResultManagerNotNull() {
        assertNotNull(DataBaseServiceImpl.getInstance().getResultManager());
    }

    @Test
    void getRoleManagerNotNull() {
        assertNotNull(DataBaseServiceImpl.getInstance().getRoleManager());
    }

    @Test
    void getTypeManagerNotNull() {
        assertNotNull(DataBaseServiceImpl.getInstance().getTypeManager());
    }
}