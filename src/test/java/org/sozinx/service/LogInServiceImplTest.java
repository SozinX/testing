package org.sozinx.service;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LogInServiceImplTest {
    private LogInService service;
    private HttpServletRequest req;

    @BeforeEach
    void setUp() {
        service = LogInServiceImpl.getInstance();
        req = mock(HttpServletRequest.class);
    }

    @Test
    void getInstance() {
        assertNotNull(service);
    }

    @Test
    void inputIsCorrectEmailIsAbsent() {
        assertEquals("User with this email doesn't exist", service.inputIsCorrect(req));
    }

    @Test
    void inputIsCorrectPasswordIsNotCorrect() {
        when(req.getParameter("email")).thenReturn("testingtest125323734@gmail.com");
        assertEquals("Password is not correct", service.inputIsCorrect(req));
    }

    @Test
    void inputIsCorrect() {
        when(req.getParameter("email")).thenReturn("testingtest125323734@gmail.com");
        when(req.getParameter("password")).thenReturn("1232131");
    }
}