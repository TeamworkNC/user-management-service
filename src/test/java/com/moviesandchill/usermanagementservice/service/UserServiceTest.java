package com.moviesandchill.usermanagementservice.service;

import com.moviesandchill.usermanagementservice.dto.user.NewUserDto;
import com.moviesandchill.usermanagementservice.dto.user.UserDto;
import com.moviesandchill.usermanagementservice.repository.UserRepository;
import lombok.SneakyThrows;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class UserServiceTest {

    @MockBean
    private RestHighLevelClient restHighLevelClient;

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void beforeEach() {
        userRepository.deleteAll();
    }

    @Test
    public void ban() {

    }

    @Test
    public void unban() {

    }

    @SneakyThrows
    @Test
    public void setOnline_setOnline() {
        UserDto userDto = createAndAddUser("first");
        long userId = userDto.getUserId();

        userService.setOnline(userId, true);

        assertThat(userService.getUser(userId).isOnline()).isTrue();
    }

    @SneakyThrows
    @Test
    public void setOnline_setOffline() {
        UserDto userDto = createAndAddUser("first");
        long userId = userDto.getUserId();
        userService.setOnline(userId, true);

        userService.setOnline(userId, false);

        assertThat(userService.getUser(userId).isOnline()).isFalse();
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
