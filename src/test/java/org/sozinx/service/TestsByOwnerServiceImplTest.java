package org.sozinx.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TestsByOwnerServiceImplTest {
    private TestsByOwnerService service;
    private HttpServletRequest req;

    @BeforeEach
    void setUp() {
        service = TestsByOwnerServiceImpl.getInstance();
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
    void getCountOfPagesByOwner() {
        assertEquals(1, service.getCountOfPagesByOwner(req));
    }

    @Test
    void getTestsByOwner() {
        assertNotNull(service.getTestsByOwner(req));
    }
}