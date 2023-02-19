package org.sozinx.service;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SignUpServiceImplTest {
    private SignUpService service;
    private HttpServletRequest req;

    @BeforeEach
    void setUp() {
        service = SignUpServiceImpl.getInstance();
        req = mock(HttpServletRequest.class);
    }

    @Test
    void getInstance() {
        assertNotNull(service);
    }

    @Test
    void validationMessageNameIsTooShort() {
        assertEquals("Name is too short", service.validationMessage(req));
    }

    @Test
    void validationMessageEmailIsNotCorrect() {
        when(req.getParameter("name")).thenReturn("Test");
        assertEquals("Enter a correct email", service.validationMessage(req));
    }

    @Test
    void validationMessagePasswordsIsNotCorrect() {
        when(req.getParameter("name")).thenReturn("Test");
        when(req.getParameter("email")).thenReturn("123qwe@dsa.ds");
        assertEquals("Password is too weak", service.validationMessage(req));
    }
}