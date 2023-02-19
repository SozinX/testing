package org.sozinx.service;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ViewTestServiceImplTest {
    private ViewTestService service;
    private HttpServletRequest req;

    @BeforeEach
    void setUp() {
        service = ViewTestServiceImpl.getInstance();
        req = mock(HttpServletRequest.class);
        when(req.getRequestURI()).thenReturn("/1");
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
    void getTestTime() {
        assertEquals(10, service.getTestTime(Long.parseLong(service.getTestIdFromUri(req))));
    }
}