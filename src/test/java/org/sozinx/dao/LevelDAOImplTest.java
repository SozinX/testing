package org.sozinx.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sozinx.service.DataBaseService;
import org.sozinx.service.DataBaseServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

class LevelDAOImplTest {
    private DataBaseService service;

    @BeforeEach
    void setUp() {
        service = DataBaseServiceImpl.getInstance();
    }

    @Test
    void getLevelByIdEqual() {
        assertEquals("Hard", service.getLevelManager().getLevelById(3).getLevel());
    }

    @Test
    void getLevelByIdNull() {
        assertNull(service.getLevelManager().getLevelById(19));
    }
}