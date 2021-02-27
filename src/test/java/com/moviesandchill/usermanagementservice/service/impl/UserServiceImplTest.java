package com.moviesandchill.usermanagementservice.service.impl;

import com.moviesandchill.usermanagementservice.entity.User;
import com.moviesandchill.usermanagementservice.entity.UserPassword;
import com.moviesandchill.usermanagementservice.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class UserServiceImplTest {

    private User firstUserExample;
    private User secondUserExample;
    private User thirdUserExample;

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void init() {
        firstUserExample = createUserExample("first");
        secondUserExample = createUserExample("second");
        thirdUserExample = createUserExample("third");
    }

    private User createUserExample(String username) {
        UserPassword userPassword = new UserPassword();
        userPassword.setPasswordHash(passwordEncoder.encode(username + " password"));
        User user = User.builder()
                .name(username + " name")
                .birthday(LocalDate.now())
                .logoUrl(username + " logo_url")
                .description(username + " description")
                .registrationDate(LocalDate.now())
                .password(userPassword)
                .build();
        userPassword.setUser(user);
        return user;
    }

    @AfterEach
    public void destruct() {
        userService.deleteAllUsers();
    }

    @Test
    public void testGetAllUsers() {
        userService.addUser(firstUserExample);
        userService.addUser(secondUserExample);

        assertThat(userService.getAllUsers()).hasSize(2);
    }

    @Test
    public void testGetUserById() {
        User newUser = userService.addUser(firstUserExample);

        User foundedUser = userService.getUserById(newUser.getUserId()).orElseThrow();

        assertThat(foundedUser.getName()).isEqualTo(newUser.getName());
    }

    @Test
    public void testAddUser() {
        userService.addUser(firstUserExample);

        assertThat(userService.getAllUsers()).hasSize(1);
    }

    @Test
    public void testDeleteUser() {
        User newFirstUser = userService.addUser(firstUserExample);
        userService.addUser(secondUserExample);

        userService.deleteUser(newFirstUser.getUserId());

        assertThat(userService.getAllUsers()).hasSize(1);
    }

    @Test
    public void testGetAllUserFriends() {
        User newFirstUser = userService.addUser(firstUserExample);
        User newSecondUser = userService.addUser(secondUserExample);
        User newThirdUser = userService.addUser(thirdUserExample);

        userService.addUserFriend(newFirstUser.getUserId(), newSecondUser.getUserId());
        userService.addUserFriend(newFirstUser.getUserId(), newThirdUser.getUserId());

        assertThat(userService.getAllUserFriends(newFirstUser.getUserId())).hasSize(2);
    }

    @Test
    public void testAddUserFriend_FriendAlreadyExist() {
        User newFirstUser = userService.addUser(firstUserExample);
        User newSecondUser = userService.addUser(secondUserExample);

        userService.addUserFriend(newFirstUser.getUserId(), newSecondUser.getUserId());
        userService.addUserFriend(newFirstUser.getUserId(), newSecondUser.getUserId());

        assertThat(userService.getAllUserFriends(newFirstUser.getUserId())).hasSize(1);
    }

    @Test
    public void testCheckPassword() {
        User newFirstUser = userService.addUser(firstUserExample);

        assertThat(userService.checkPassword(newFirstUser.getUserId(), "123")).isFalse();
        assertThat(userService.checkPassword(newFirstUser.getUserId(), "first password")).isTrue();
    }

}