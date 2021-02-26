package com.moviesandchill.usermanagementservice.mapper;

import com.moviesandchill.usermanagementservice.dto.user.NewUserDto;
import com.moviesandchill.usermanagementservice.dto.user.UserDto;
import com.moviesandchill.usermanagementservice.entity.User;
import com.moviesandchill.usermanagementservice.entity.UserPassword;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class UserMapper {
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserMapper(ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public User mapToUser(NewUserDto dto) {
        User user = modelMapper.map(dto, User.class);
        UserPassword userPassword = new UserPassword();

        log.info(String.valueOf(dto.getPassword()));
        String passwordHash = passwordEncoder.encode(dto.getPassword());
        log.info(passwordHash);

        userPassword.setPasswordHash(passwordHash);
        user.setPassword(userPassword);
        userPassword.setUser(user);
        return user;
    }

    public List<UserDto> mapToDto(List<User> users) {
        return modelMapper.map(users, new TypeToken<List<UserDto>>() {
        }.getType());
    }

    public UserDto mapToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }
}
