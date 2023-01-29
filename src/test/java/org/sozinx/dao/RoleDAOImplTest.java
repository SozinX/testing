package org.sozinx.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sozinx.service.DataBaseService;
import org.sozinx.service.DataBaseServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

class RoleDAOImplTest {
    private DataBaseService service;

    @BeforeEach
    void setUp() {
        service = DataBaseServiceImpl.getInstance();
    }

    @Test
    void getRoleByIdEqual() {
        assertEquals("Teacher", service.getRoleManager().getRoleById(2).getRole());
    }

    @Test
    void getRoleByIdNull() {
        assertNull(service.getRoleManager().getRoleById(17));
    }
}