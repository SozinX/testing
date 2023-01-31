package org.sozinx.dao;

import org.junit.jupiter.api.*;
import org.sozinx.model.Answer;
import org.sozinx.model.Question;
import org.sozinx.service.DataBaseService;
import org.sozinx.service.DataBaseServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AnswerDAOImplTest {
    private DataBaseService service;
    private Question questionForAnswer;
    private List<Answer> testAnswer;

    @BeforeEach
    void setUp() {
        testAnswer = new ArrayList<>();
        service = DataBaseServiceImpl.getInstance();
        questionForAnswer = service.getQuestionManager().getQuestionById(1);
        testAnswer.add(new Answer(0, "test1answer", 0, questionForAnswer));
        testAnswer.add(new Answer(0, "test2answer", 1, questionForAnswer));
        testAnswer.add(new Answer(0, "test3answer", 0, questionForAnswer));
        testAnswer.add(new Answer(0, "test4answer", 0, questionForAnswer));
        testAnswer.add(new Answer(0, "test5answer", 0, questionForAnswer));
    }

    @Test
    @Order(1)
    void addAnswer() {
        testAnswer.forEach(answer -> assertTrue(service.getAnswerManager().addAnswer(answer)));
    }

    @Test
    @Order(2)
    void getAnswerById() {
        Answer checkAnswer = service.getAnswerManager().getAnswerByQuestion(questionForAnswer).get(2);
        assertEquals(checkAnswer.getAnswer(), service.getAnswerManager().getAnswerById(checkAnswer.getId()).getAnswer());
    }

    @Test
    @Order(3)
    void getAnswerByQuestion() {
        assertEquals(5, service.getAnswerManager().getAnswerByQuestion(questionForAnswer).size());
    }


    @Test
    @Order(4)
    void updateAnswer() {
        Answer checkAnswer = service.getAnswerManager().getAnswerByQuestion(questionForAnswer).get(3);
        assertTrue(service.getAnswerManager().updateAnswer(checkAnswer, new String[]{"changedForTest", "0"}));
        assertNotEquals("test4answer", service.getAnswerManager().getAnswerById(checkAnswer.getId()).getAnswer());
    }

    @Test
    @Order(5)
    void deleteAnswer() {
        Answer checkAnswer = service.getAnswerManager().getAnswerByQuestion(questionForAnswer).get(0);
        assertTrue(service.getAnswerManager().deleteAnswer(checkAnswer));
        assertNull(service.getAnswerManager().getAnswerById(checkAnswer.getId()));
    }

    @Test
    @Order(6)
    void deleteAnswerByQuestion() {
        assertTrue(service.getAnswerManager().deleteAnswerByQuestion(questionForAnswer));
        assertEquals(0, service.getAnswerManager().getAnswerByQuestion(questionForAnswer).size());
    }
}