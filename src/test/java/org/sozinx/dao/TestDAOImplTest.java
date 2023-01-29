package org.sozinx.dao;

import org.junit.jupiter.api.*;
import org.sozinx.model.User;
import org.sozinx.service.DataBaseService;
import org.sozinx.service.DataBaseServiceImpl;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestDAOImplTest {
    private DataBaseService service;
    private User userForTest;
    private org.sozinx.model.Test testTest;

    @BeforeEach
    void setUp() {
        service = DataBaseServiceImpl.getInstance();
        userForTest = service.getUserManager().getUserByEmail("testingtest125323734@gmail.com");
        testTest = new org.sozinx.model.Test(0, "Test test", "Test subject", LocalDate.now().toString(), 2,
                44, 0, userForTest, service.getLevelManager().getLevelById(2));
    }

    @Test
    @Order(1)
    void addTest() {
        assertTrue(service.getTestManager().addTest(testTest));
    }

    @Test
    @Order(2)
    void getLastAddedTestByOwner() {
        assertNotNull(service.getTestManager().getLastAddedTestByOwner(userForTest));
    }


    @Test
    @Order(3)
    void addPopularity() {
        org.sozinx.model.Test checkingTest = service.getTestManager().getLastAddedTestByOwner(userForTest);
        assertTrue(service.getTestManager().addPopularity(checkingTest));
        assertEquals(checkingTest.getFinished() + 1, service.getTestManager().getLastAddedTestByOwner(userForTest).getFinished());
    }


    @Test
    @Order(4)
    void getTestById() {
        org.sozinx.model.Test checkingTest = service.getTestManager().getLastAddedTestByOwner(userForTest);
        assertEquals(checkingTest.getName(), service.getTestManager().getTestById(checkingTest.getId()).getName());
    }

    @Test
    @Order(5)
    void openTest() {
        org.sozinx.model.Test checkingTest = service.getTestManager().getLastAddedTestByOwner(userForTest);
        assertTrue(service.getTestManager().openTest(checkingTest));
        assertNotEquals(checkingTest.getIsClose(), service.getTestManager().getLastAddedTestByOwner(userForTest).getIsClose());
    }

    @Test
    @Order(6)
    void updateTest() {
        org.sozinx.model.Test checkingTest = service.getTestManager().getLastAddedTestByOwner(userForTest);
        assertTrue(service.getTestManager().updateTest(checkingTest, new String[]{
                "Changed test name", "Changed subject", "1", "97", String.valueOf(checkingTest.getLevel().getId())
        }));
        checkingTest = service.getTestManager().getLastAddedTestByOwner(userForTest);
        assertEquals("Changed test name", checkingTest.getName());
        assertEquals("Changed subject", checkingTest.getSubject());
        assertEquals(97, checkingTest.getTime());
    }
}