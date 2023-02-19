package org.sozinx.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TestingServiceImplTest {
    private TestingService service;
    private HttpServletRequest req;

    @BeforeEach
    void setUp() {
        service = TestingServiceImpl.getInstance();
        req = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        when(req.getRequestURI()).thenReturn("/1");
        when(req.getSession()).thenReturn(session);
        when(session.getAttribute("testId")).thenReturn("1");
    }

    @Test
    void getInstance() {
        assertNotNull(service);
    }

    @Test
    void getCountOfPages() {
        assertEquals(1, service.getCountOfPages(req));
    }

    @Test
    void getTestIdFromUri() {
        assertEquals("1", service.getTestIdFromUri(req));
    }
}