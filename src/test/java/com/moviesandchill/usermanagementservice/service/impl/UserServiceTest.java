package com.moviesandchill.usermanagementservice.service.impl;

import org.junit.jupiter.api.AfterEach;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class UserServiceTest {

    @AfterEach
    public void destruct() {
        //clean db
    }
}
