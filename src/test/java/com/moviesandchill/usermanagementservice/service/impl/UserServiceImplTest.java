package com.moviesandchill.usermanagementservice.service.impl;

import com.moviesandchill.usermanagementservice.dto.user.NewUserDto;
import com.moviesandchill.usermanagementservice.dto.user.UserDto;
import com.moviesandchill.usermanagementservice.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class UserServiceImplTest {

    private NewUserDto firstUserDtoExample;
    private NewUserDto secondUserDtoExample;
    private NewUserDto thirdUserDtoExample;

    @Autowired
    private UserService userService;

    @BeforeEach
    public void init() {
        firstUserDtoExample = createNewUserDto("first");
        secondUserDtoExample = createNewUserDto("second");
        thirdUserDtoExample = createNewUserDto("third");
    }

    private NewUserDto createNewUserDto(String username) {
        return NewUserDto.builder()
                .name(username + " name")
                .birthday(LocalDate.now())
                .logoUrl(username + " logo_url")
                .description(username + " description")
                .password(username + " password")
                .build();
    }

    @AfterEach
    public void destruct() {
        userService.deleteAllUsers();
    }

    @Test
    public void testGetAllUsers() {
        userService.addUser(firstUserDtoExample);
        userService.addUser(secondUserDtoExample);

        assertThat(userService.getAllUsers()).hasSize(2);
    }

    @Test
    public void testGetUserById() {
        UserDto newUser = userService.addUser(firstUserDtoExample);

        UserDto foundedUser = userService.getUserById(newUser.getUserId()).orElseThrow();

        assertThat(foundedUser.getName()).isEqualTo(newUser.getName());
    }

    @Test
    public void testAddUser() {
        userService.addUser(firstUserDtoExample);

        assertThat(userService.getAllUsers()).hasSize(1);
    }

    @Test
    public void testDeleteUser() {
        UserDto newFirstUser = userService.addUser(firstUserDtoExample);
        userService.addUser(secondUserDtoExample);

        userService.deleteUser(newFirstUser.getUserId());

        assertThat(userService.getAllUsers()).hasSize(1);
    }

    @Test
    public void testGetAllUserFriends() {
        UserDto newFirstUser = userService.addUser(firstUserDtoExample);
        UserDto newSecondUser = userService.addUser(secondUserDtoExample);
        UserDto newThirdUser = userService.addUser(thirdUserDtoExample);

        userService.addUserFriend(newFirstUser.getUserId(), newSecondUser.getUserId());
        userService.addUserFriend(newFirstUser.getUserId(), newThirdUser.getUserId());

        assertThat(userService.getAllUserFriends(newFirstUser.getUserId())).hasSize(2);
    }

    @Test
    public void testAddUserFriend_FriendAlreadyExist() {
        UserDto newFirstUser = userService.addUser(firstUserDtoExample);
        UserDto newSecondUser = userService.addUser(secondUserDtoExample);

        userService.addUserFriend(newFirstUser.getUserId(), newSecondUser.getUserId());
        userService.addUserFriend(newFirstUser.getUserId(), newSecondUser.getUserId());

        assertThat(userService.getAllUserFriends(newFirstUser.getUserId())).hasSize(1);
    }

    @Test
    public void testCheckPassword() {
        UserDto newFirstUser = userService.addUser(firstUserDtoExample);

        assertThat(userService.checkPassword(newFirstUser.getUserId(), "123")).isFalse();
        assertThat(userService.checkPassword(newFirstUser.getUserId(), "first password")).isTrue();
    }

}