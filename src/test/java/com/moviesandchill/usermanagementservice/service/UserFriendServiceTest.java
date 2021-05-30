package com.moviesandchill.usermanagementservice.service;

import com.moviesandchill.usermanagementservice.dto.user.NewUserDto;
import com.moviesandchill.usermanagementservice.dto.user.UserDto;
import com.moviesandchill.usermanagementservice.repository.UserRepository;
import lombok.SneakyThrows;
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
class UserFriendServiceTest {
    @Autowired
    private UserFriendService userFriendService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void beforeEach() {
        userRepository.deleteAll();
    }


    @Test
    @SneakyThrows
    void deleteFriend() {
        var user1 = createAndAddUser("1");
        var user2 = createAndAddUser("2");
        userFriendService.addFriend(user1.getUserId(), user2.getUserId());
        userFriendService.addFriend(user2.getUserId(), user1.getUserId());

        userFriendService.deleteFriend(user1.getUserId(), user2.getUserId());

        assertThat(userFriendService.getAllFriends(user1.getUserId())).isEmpty();
        assertThat(userFriendService.getAllFriends(user2.getUserId())).isEmpty();
    }

    private UserDto createAndAddUser(String name) {
        var newUserDto = NewUserDto.builder()
                .description(name + " description")
                .logoUrl(name + " logo url")
                .birthday(LocalDate.now())
                .email(name + " email")
                .login(name + " login")
                .password(name + " password")
                .build();
        return userService.addUser(newUserDto);
    }
}
