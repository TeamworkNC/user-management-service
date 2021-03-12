package com.moviesandchill.usermanagementservice.service.impl;

import com.moviesandchill.usermanagementservice.dto.achievement.AchievementDto;
import com.moviesandchill.usermanagementservice.dto.achievement.NewAchievementDto;
import com.moviesandchill.usermanagementservice.dto.login.LoginRequestDto;
import com.moviesandchill.usermanagementservice.dto.password.UpdatePasswordDto;
import com.moviesandchill.usermanagementservice.dto.user.NewUserDto;
import com.moviesandchill.usermanagementservice.dto.user.UserDto;
import com.moviesandchill.usermanagementservice.exception.achievement.AchievementNotFoundException;
import com.moviesandchill.usermanagementservice.exception.user.UserNotFoundException;
import com.moviesandchill.usermanagementservice.service.AchievementService;
import com.moviesandchill.usermanagementservice.service.UserService;
import org.junit.jupiter.api.AfterEach;
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

    private final NewUserDto firstUserDtoExample = createNewUserDto("first");
    private final NewUserDto secondUserDtoExample = createNewUserDto("second");
    private final NewUserDto thirdUserDtoExample = createNewUserDto("third");

    private final NewAchievementDto firstNewAchievementDtoExample = createNewAchievementDto("first");
    private final NewAchievementDto secondNewAchievementDtoExample = createNewAchievementDto("second");


    @Autowired
    private UserService userService;

    @Autowired
    private AchievementService achievementService;


    private NewUserDto createNewUserDto(String username) {
        return NewUserDto.builder()
                .login(username + " login")
                .firstName(username + " first_name")
                .lastName(username + " last_name")
                .birthday(LocalDate.now())
                .logoUrl(username + " logo_url")
                .description(username + " description")
                .password(username + " password")
                .build();
    }

    private NewAchievementDto createNewAchievementDto(String name) {
        return NewAchievementDto.builder()
                .name(name + " name")
                .description(name + " description")
                .logoUrl(name + " logoUrl")
                .build();
    }

    @AfterEach
    public void destruct() {
        userService.deleteAllUsers();
        achievementService.deleteAllAchievements();
    }

    @Test
    public void testGetAllUsers() {
        userService.addUser(firstUserDtoExample);
        userService.addUser(secondUserDtoExample);

        assertThat(userService.getAllUsers()).hasSize(2);
    }

    @Test
    public void testGetUserById_userExist() throws UserNotFoundException {
        UserDto newUser = userService.addUser(firstUserDtoExample);

        UserDto foundedUser = userService.getUserById(newUser.getUserId());

        assertThat(foundedUser).isEqualTo(newUser);
    }

    @Test
    public void testGetUserById_userNotExist() {
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
    public void getAllUserAchievements() throws UserNotFoundException, AchievementNotFoundException {
        UserDto newFirstUser = userService.addUser(firstUserDtoExample);
        AchievementDto newFirstAchievement = achievementService.addAchievement(firstNewAchievementDtoExample);
        AchievementDto newSecondAchievement = achievementService.addAchievement(secondNewAchievementDtoExample);

        userService.addUserAchievement(newFirstUser.getUserId(), newFirstAchievement.getAchievementId());
        userService.addUserAchievement(newFirstUser.getUserId(), newSecondAchievement.getAchievementId());

        assertThat(userService.getAllUserAchievements(newFirstUser.getUserId())).hasSize(2);
    }

    @Test
    void testDeleteUserAchievement() throws UserNotFoundException, AchievementNotFoundException {
        UserDto newFirstUser = userService.addUser(firstUserDtoExample);
        AchievementDto newFirstAchievement = achievementService.addAchievement(firstNewAchievementDtoExample);
        AchievementDto newSecondAchievement = achievementService.addAchievement(secondNewAchievementDtoExample);
        userService.addUserAchievement(newFirstUser.getUserId(), newFirstAchievement.getAchievementId());
        userService.addUserAchievement(newFirstUser.getUserId(), newSecondAchievement.getAchievementId());

        userService.deleteUserAchievement(newFirstUser.getUserId(), newFirstAchievement.getAchievementId());

        assertThat(userService.getAllUserAchievements(newFirstUser.getUserId())).hasSize(1);
    }

    @Test
    void testUpdateUserPassword_UserNotExist() {
        UserDto newFirstUser = userService.addUser(firstUserDtoExample);
        UpdatePasswordDto updatePasswordDto = UpdatePasswordDto.builder()
                .oldPassword("first user password")
                .newPassword("strong password")
                .build();

        assertThatThrownBy(() -> {
            userService.updateUserPassword(9999, updatePasswordDto);
        }).isInstanceOf(UserNotFoundException.class);
    }

    @Test
    void testUpdateUserPassword_CorrectOldPassword() throws UserNotFoundException {
        UserDto newFirstUser = userService.addUser(firstUserDtoExample);
        UpdatePasswordDto updatePasswordDto = UpdatePasswordDto.builder()
                .oldPassword("first password")
                .newPassword("strong password")
                .build();

        assertThat(userService.updateUserPassword(newFirstUser.getUserId(), updatePasswordDto)).isTrue();
    }

    @Test
    void testUpdateUserPassword_IncorrectOldPassword() throws UserNotFoundException {
        UserDto newFirstUser = userService.addUser(firstUserDtoExample);
        UpdatePasswordDto updatePasswordDto = UpdatePasswordDto.builder()
                .oldPassword("wrong password")
                .newPassword("strong password")
                .build();

        assertThat(userService.updateUserPassword(newFirstUser.getUserId(), updatePasswordDto)).isFalse();
    }

    @Test
    public void testLogin() {
        UserDto newFirstUser = userService.addUser(firstUserDtoExample);
        LoginRequestDto goodLoginRequest = LoginRequestDto.builder()
                .login(firstUserDtoExample.getLogin())
                .password(firstUserDtoExample.getPassword())
                .build();
        LoginRequestDto badLoginRequest1 = LoginRequestDto.builder()
                .login(firstUserDtoExample.getLogin())
                .password("adjsapdhiuopahd")
                .build();
        LoginRequestDto badLoginRequest2 = LoginRequestDto.builder()
                .login("asdasdasdwqad")
                .password(firstUserDtoExample.getPassword())
                .build();


        assertThat(userService.login(goodLoginRequest)).get().isEqualTo(newFirstUser);
        assertThat(userService.login(badLoginRequest1)).isEmpty();
        assertThat(userService.login(badLoginRequest2)).isEmpty();
    }

}
