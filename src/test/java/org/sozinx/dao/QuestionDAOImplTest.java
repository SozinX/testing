package org.sozinx.dao;

import org.junit.jupiter.api.*;
import org.sozinx.model.Question;
import org.sozinx.service.DataBaseService;
import org.sozinx.service.DataBaseServiceImpl;


import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class QuestionDAOImplTest {
    private DataBaseService service;
    private org.sozinx.model.Test testForQuestion;
    private Question testQuestion;

    @BeforeEach
    void setUp() {
        service = DataBaseServiceImpl.getInstance();
        testForQuestion = service.getTestManager().getTestById(1);
        testQuestion = new Question(0, "Test question", testForQuestion, service.getTypeManager().getTypeById(1));
    }

    @Test
    @Order(5)
    void getQuestionById() {
        Question checkQuestion = service.getQuestionManager().getLastQuestionByTest(testForQuestion);
        assertEquals(checkQuestion.getQuestion(), service.getQuestionManager().getQuestionById(checkQuestion.getId()).getQuestion());
    }

    @Test
    @Order(4)
    void getQuestionByName() {
        Question checkQuestion = service.getQuestionManager().getLastQuestionByTest(testForQuestion);
        assertNull(service.getQuestionManager().getQuestionByName("Test question", testForQuestion.getId(), checkQuestion.getId()));
    }

    @Test
    @Order(3)
    void getQuestionByTest() {
        assertNotNull(service.getQuestionManager().getQuestionByTest(testForQuestion));
    }

    @Test
    @Order(2)
    void getLastQuestionByTest() {
        assertNotNull(service.getQuestionManager().getLastQuestionByTest(testForQuestion));
    }

    @Test
    @Order(1)
    void addQuestion() {
        assertTrue(service.getQuestionManager().addQuestion(testQuestion));
    }

    @Test
    @Order(6)
    void updateQuestion() {
        Question checkQuestion = service.getQuestionManager().getLastQuestionByTest(testForQuestion);
        assertTrue(service.getQuestionManager().updateQuestion(checkQuestion, new String[]{"Changed question", "2"}));
    }

    @Test
    @Order(7)
    void deleteQuestion() {
        assertTrue(service.getQuestionManager().deleteQuestion(service.getQuestionManager().getLastQuestionByTest(testForQuestion)));
    }
}