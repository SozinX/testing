package org.sozinx.service;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EditQuestionServiceImplTest {
    private EditQuestionService service;
    private HttpServletRequest req;

    @BeforeEach
    void setUp() {
        service = EditQuestionServiceImpl.getInstance();
        req = mock(HttpServletRequest.class);
        when(req.getRequestURI()).thenReturn("/1");
        when(req.getParameter("iterations")).thenReturn("5");
        when(req.getParameter("questionId")).thenReturn("1");
    }

    @Test
    void getInstance() {
        assertNotNull(service);
    }

    @Test
    void getTestIdFromUri() {
        assertEquals("1", service.getTestIdFromUri(req));
    }

    @Test
    void getAllQuestions() {
        assertEquals(1, service.getAllQuestions(req).size());
    }

    @Test
    void getCountOfPages() {
        assertEquals(1, service.getCountOfPages(req));
    }

    @Test
    void getQuestion() {
        assertEquals(1, service.getQuestion(req).getId());
    }

    @Test
    void getAnswers() {
        assertEquals(0, service.getAnswers(service.getQuestion(req)).size());
    }

    @Test
    void validationMessageQuestionIsTooShort() {
        assertEquals("Question is too short", service.validationMessage(req));
    }

    @Test
    void validationMessageNotCorrectOneAnswerType() {
        when(req.getParameter("questionText")).thenReturn("Test");
        when(req.getParameter("questionType")).thenReturn("1");
        for (int i = 1; i < 6; i++) {
            when(req.getParameter("qt" + i)).thenReturn("1");
        }
        assertEquals("In question with one correct answer you need to set only one correct answer", service.validationMessage(req));
    }

    @Test
    void validationMessageNotCorrectManyAnswersType() {
        when(req.getParameter("questionText")).thenReturn("Test");
        when(req.getParameter("questionType")).thenReturn("2");
        for (int i = 1; i < 6; i++) {
            when(req.getParameter("qt" + i)).thenReturn("0");
        }
        assertEquals("In question with many correct answers you need to set two and more correct answers", service.validationMessage(req));
    }

    @Test
    void validationMessageAnswersAreToShort() {
        when(req.getParameter("questionText")).thenReturn("Test");
        for (int i = 1; i < 6; i++) {
            when(req.getParameter("qt" + i)).thenReturn("1");
        }
        for (int i = 1; i < 6; i++) {
            when(req.getParameter("a" + i)).thenReturn("");
        }
        assertEquals("Some of your answers is too short", service.validationMessage(req));
    }

    @Test
    void validationMessageIsCorrect() {
        when(req.getParameter("questionText")).thenReturn("Test");
        for (int i = 1; i < 6; i++) {
            when(req.getParameter("a" + i)).thenReturn(String.valueOf(i));
        }
        assertNull(service.validationMessage(req));
    }
}