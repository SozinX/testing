package org.sozinx.dao;

import org.junit.jupiter.api.*;
import org.sozinx.model.User;
import org.sozinx.service.DataBaseService;
import org.sozinx.service.DataBaseServiceImpl;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserDAOImplTest {
    private DataBaseService service;
    private User testUser;

    @BeforeEach
    void setUp() {
        service = DataBaseServiceImpl.getInstance();
        testUser = new User(0, "Test User", "testuser13086532133@gmail.com", "11111111",
                LocalDate.now().toString(), service.getRoleManager().getRoleById(2));
    }

    @Test
    @Order(1)
    void addUser() {
        assertTrue(service.getUserManager().addUser(testUser));
    }

    @Test
    @Order(2)
    void getUserByEmail() {
        User checkTestUser = service.getUserManager().getUserByEmail("testuser13086532133@gmail.com");
        assertEquals("Test User", checkTestUser.getName());
        assertNull(service.getUserManager().getUserByEmail("testuser133212312312332213126423@gmail.com"));
    }

    @Test
    @Order(3)
    void getUserById() {
        User checkTestUser = service.getUserManager().getUserByEmail("testuser13086532133@gmail.com");
        assertEquals(checkTestUser.getEmail(), service.getUserManager().getUserById(checkTestUser.getId()).getEmail());
    }

    @Test
    @Order(4)
    void updateUser() {
        User checkTestUser = service.getUserManager().getUserByEmail("testuser13086532133@gmail.com");
        assertTrue(service.getUserManager().updateUser(checkTestUser, new String[]{checkTestUser.getName(), checkTestUser.getEmail(),
                "3", checkTestUser.getPassword()}));
    }

    @Test
    @Order(5)
    void deleteUser() {
        User checkTestUser = service.getUserManager().getUserByEmail("testuser13086532133@gmail.com");
        assertTrue(service.getUserManager().deleteUser(checkTestUser));
    }


}