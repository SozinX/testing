package org.sozinx.service;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UnblockUserServiceImplTest {

    private UnblockUserService service;
    private HttpServletRequest req;

    @BeforeEach
    void setUp() {
        service = UnblockUserServiceImpl.getInstance();
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
    void inputIsNotBlocked() {
        when(req.getParameter("unblockEmail")).thenReturn("testingtest125323734@gmail.com");
        assertEquals("User is not in block", service.inputIsCorrect(req));
    }
}