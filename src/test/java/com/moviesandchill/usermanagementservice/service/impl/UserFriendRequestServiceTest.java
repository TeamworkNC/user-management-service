package com.moviesandchill.usermanagementservice.service.impl;

import com.moviesandchill.usermanagementservice.dto.friendrequest.NewFriendRequestDto;
import com.moviesandchill.usermanagementservice.dto.user.NewUserDto;
import com.moviesandchill.usermanagementservice.dto.user.UserDto;
import com.moviesandchill.usermanagementservice.repository.FriendRequestRepository;
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
class UserFriendRequestServiceTest {
    @Autowired
    private UserFriendRequestService userFriendRequestService;
    @Autowired
    private FriendRequestService friendRequestService;
    @Autowired
    private UserFriendService userFriendService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FriendRequestRepository friendRequestRepository;

    @BeforeEach
    public void beforeEach() {
        friendRequestRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void getAllFriendRequests() {
        var userDto1 = createAndAddUser("user1");
        var userDto2 = createAndAddUser("user2");
        var userDto3 = createAndAddUser("user3");
        friendRequestService.addFriendRequest(new NewFriendRequestDto(userDto1.getUserId(), userDto2.getUserId()));
        friendRequestService.addFriendRequest(new NewFriendRequestDto(userDto2.getUserId(), userDto1.getUserId()));
        friendRequestService.addFriendRequest(new NewFriendRequestDto(userDto3.getUserId(), userDto1.getUserId()));

        var friendRequests = userFriendRequestService.getAllFriendRequests(userDto1.getUserId());

        assertThat(friendRequests).hasSize(2);
    }

    @SneakyThrows
    @Test
    void acceptFriendRequest() {
        var userDto1 = createAndAddUser("user1");
        var userDto2 = createAndAddUser("user2");
        var friendRequest1 = friendRequestService
                .addFriendRequest(new NewFriendRequestDto(userDto1.getUserId(), userDto2.getUserId()));
        var friendRequest2 = friendRequestService
                .addFriendRequest(new NewFriendRequestDto(userDto2.getUserId(), userDto1.getUserId()));

        userFriendRequestService.acceptFriendRequest(userDto1.getUserId(), friendRequest2.getFriendRequestId());

        var firstUserFriendRequests = userFriendRequestService.getAllFriendRequests(userDto1.getUserId());
        assertThat(firstUserFriendRequests).isEmpty();
        var secondUserFriendRequests = userFriendRequestService.getAllFriendRequests(userDto2.getUserId());
        assertThat(secondUserFriendRequests).isEmpty();
        assertThat(userFriendService.getAllFriends(userDto1.getUserId())).hasSize(1);
        assertThat(userFriendService.getAllFriends(userDto2.getUserId())).hasSize(1);
    }

    @SneakyThrows
    @Test
    void declineFriendRequest() {
        var userDto1 = createAndAddUser("user1");
        var userDto2 = createAndAddUser("user2");
        var friendRequest1 = friendRequestService
                .addFriendRequest(new NewFriendRequestDto(userDto1.getUserId(), userDto2.getUserId()));
        var friendRequest2 = friendRequestService
                .addFriendRequest(new NewFriendRequestDto(userDto2.getUserId(), userDto1.getUserId()));

        userFriendRequestService.declineFriendRequest(userDto1.getUserId(), friendRequest2.getFriendRequestId());

        var firstUserFriendRequests = userFriendRequestService.getAllFriendRequests(userDto1.getUserId());
        assertThat(firstUserFriendRequests).isEmpty();
        var secondUserFriendRequests = userFriendRequestService.getAllFriendRequests(userDto2.getUserId());
        assertThat(secondUserFriendRequests).hasSize(1);
        assertThat(userFriendService.getAllFriends(userDto1.getUserId())).isEmpty();
        assertThat(userFriendService.getAllFriends(userDto2.getUserId())).isEmpty();
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
