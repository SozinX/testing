package org.sozinx.service;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SettingsServiceImplTest {
    private SettingsService service;
    private HttpServletRequest req;

    @BeforeEach
    void setUp() {
        service = SettingsServiceImpl.getInstance();
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
        when(req.getParameter("set-name")).thenReturn("Test");
        assertEquals("Enter a correct email", service.validationMessage(req));
    }

    @Test
    void validationMessagePasswordsIsNotCorrect() {
        when(req.getParameter("set-name")).thenReturn("Test");
        when(req.getParameter("set-email")).thenReturn("123qwe@dsa.ds");
        assertEquals("Password is too weak", service.validationMessage(req));
    }

    @Test
    void validationMessagePasswordsDoNotMatch() {
        when(req.getParameter("set-name")).thenReturn("Test");
        when(req.getParameter("set-email")).thenReturn("123qwe@dsa.ds");
        when(req.getParameter("new-password")).thenReturn("123qweQWE@");
        when(req.getParameter("confirm-password")).thenReturn("321qweQWE@");
        assertEquals("Passwords do not match", service.validationMessage(req));
    }
}