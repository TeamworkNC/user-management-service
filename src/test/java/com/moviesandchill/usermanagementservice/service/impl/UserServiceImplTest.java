package com.moviesandchill.usermanagementservice.service.impl;

import com.moviesandchill.usermanagementservice.dto.login.LoginRequestDto;
import com.moviesandchill.usermanagementservice.dto.user.NewUserDto;
import com.moviesandchill.usermanagementservice.dto.user.UserDto;
import com.moviesandchill.usermanagementservice.exception.user.UserNotFoundException;
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
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
    public void testGetUserById__userExist() throws UserNotFoundException {
        UserDto newUser = userService.addUser(firstUserDtoExample);

        UserDto foundedUser = userService.getUserById(newUser.getUserId());

        assertThat(foundedUser.getName()).isEqualTo(newUser.getName());
    }

    @Test
    public void testGetUserById__userNotExist() {
        userService.addUser(firstUserDtoExample);

        assertThatThrownBy(
                () -> userService.getUserById(Integer.MAX_VALUE))
                .isInstanceOf(UserNotFoundException.class);
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
    public void testGetAllUserFriends() throws UserNotFoundException {
        UserDto newFirstUser = userService.addUser(firstUserDtoExample);
        UserDto newSecondUser = userService.addUser(secondUserDtoExample);
        UserDto newThirdUser = userService.addUser(thirdUserDtoExample);

        userService.addUserFriend(newFirstUser.getUserId(), newSecondUser.getUserId());
        userService.addUserFriend(newFirstUser.getUserId(), newThirdUser.getUserId());

        assertThat(userService.getAllUserFriends(newFirstUser.getUserId())).hasSize(2);
    }

    @Test
    public void testAddUserFriend_FriendAlreadyExist() throws UserNotFoundException {
        UserDto newFirstUser = userService.addUser(firstUserDtoExample);
        UserDto newSecondUser = userService.addUser(secondUserDtoExample);

        userService.addUserFriend(newFirstUser.getUserId(), newSecondUser.getUserId());
        userService.addUserFriend(newFirstUser.getUserId(), newSecondUser.getUserId());

        assertThat(userService.getAllUserFriends(newFirstUser.getUserId())).hasSize(1);
    }

    @Test
    public void testLogin() {
        UserDto newFirstUser = userService.addUser(firstUserDtoExample);
        LoginRequestDto goodLoginRequest = LoginRequestDto.builder()
                .name(firstUserDtoExample.getName())
                .password(firstUserDtoExample.getPassword())
                .build();
        LoginRequestDto badLoginRequest1 = LoginRequestDto.builder()
                .name(firstUserDtoExample.getName())
                .password("adjsapdhiuopahd")
                .build();
        LoginRequestDto badLoginRequest2 = LoginRequestDto.builder()
                .name("asdasdasdwqad")
                .password(firstUserDtoExample.getPassword())
                .build();


        assertThat(userService.login(goodLoginRequest)).get().isEqualTo(newFirstUser);
        assertThat(userService.login(badLoginRequest1)).isEmpty();
        assertThat(userService.login(badLoginRequest2)).isEmpty();
    }

}