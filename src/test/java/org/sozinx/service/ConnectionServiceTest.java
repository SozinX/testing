package org.sozinx.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConnectionServiceTest {

    @Test
    void getConnectionNotNull() {
        assertNotNull(ConnectionService.getConnection());
    }

}