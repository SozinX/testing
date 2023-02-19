package org.sozinx.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AddTestServiceImplTest {
    private AddTestService service;
    private HttpServletRequest req;

    @BeforeEach
    void setUp() {
        service = AddTestServiceImpl.getInstance();
        req = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        when(req.getSession()).thenReturn(session);
        when(session.getAttribute("id")).thenReturn("1");
    }

    @Test
    void getInstance() {
        assertNotNull(service);
    }

    @Test
    void validationMessageTestNameIsTooShort() {
        assertEquals("Test name is too short", service.validationMessage(req));
    }

    @Test
    void validationMessageTestSubjectIsTooShort() {
        when(req.getParameter("test-name")).thenReturn("Testing test for test");
        assertEquals("Test subject is too short", service.validationMessage(req));
    }

    @Test
    void validationMessageTestTimeIsNotCorrect() {
        when(req.getParameter("test-name")).thenReturn("Testing test for test");
        when(req.getParameter("test-subject")).thenReturn("Testing subject for test");
        assertEquals("The time input field should have only natural numbers", service.validationMessage(req));
    }

    @Test
    void validationMessageIsCorrect() {
        when(req.getParameter("test-name")).thenReturn("Testing test for test");
        when(req.getParameter("test-subject")).thenReturn("Testing subject for test");
        when(req.getParameter("test-time")).thenReturn("12");
        assertNull(service.validationMessage(req));
    }
}