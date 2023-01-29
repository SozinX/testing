package org.sozinx.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sozinx.service.DataBaseService;
import org.sozinx.service.DataBaseServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

class TypeDAOImplTest {
    private DataBaseService service;

    @BeforeEach
    void setUp() {
        service = DataBaseServiceImpl.getInstance();
    }

    @Test
    void getTypeByIdEquals() {
        assertEquals("One answer", service.getTypeManager().getTypeById(1).getType());
    }

    @Test
    void getTypeByIdNull() {
        assertNull(service.getTypeManager().getTypeById(5));
    }
}