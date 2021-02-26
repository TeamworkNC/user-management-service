package com.moviesandchill.usermanagementservice.service.impl;

import com.moviesandchill.usermanagementservice.entity.User;
import com.moviesandchill.usermanagementservice.entity.UserPassword;
import com.moviesandchill.usermanagementservice.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class UserServiceImplTest {

    User firstUser;
    User secondUser;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    private void init() {
        UserPassword firstUserPassword = new UserPassword();
        firstUserPassword.setPasswordHash(passwordEncoder.encode("password"));
        firstUser = User.builder()
                .name("name")
                .birthday(LocalDate.now())
                .logoUrl("logo_url")
                .description("description")
                .registrationDate(LocalDate.now())
                .password(firstUserPassword)
                .build();
        firstUserPassword.setUser(firstUser);

        UserPassword secondUserPassword = new UserPassword();
        secondUserPassword.setPasswordHash(passwordEncoder.encode("password"));
        secondUser = User.builder()
                .name("name")
                .birthday(LocalDate.now())
                .logoUrl("logo_url")
                .description("description")
                .registrationDate(LocalDate.now())
                .password(secondUserPassword)
                .build();
        secondUserPassword.setUser(secondUser);
    }

    @Test
    void test1() {
        assertThat(userService.getAllUsers()).isEmpty();
        userService.addUser(firstUser);
        assertThat(userService.getAllUsers()).hasSize(1);
    }

    @Test
    void test2() {
        assertThat(userService.getAllUsers()).isEmpty();
    }
}