package com.moviesandchill.usermanagementservice.service;

import com.moviesandchill.usermanagementservice.dto.user.UserDto;
import com.moviesandchill.usermanagementservice.entity.User;
import com.moviesandchill.usermanagementservice.exception.user.UserNotFoundException;
import com.moviesandchill.usermanagementservice.mapper.UserMapper;
import com.moviesandchill.usermanagementservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
public class UserFriendService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserFriendService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserDto> getAllFriends(long userId) throws UserNotFoundException {
        User user = findUserById(userId);
        List<User> friends = new ArrayList<>(user.getFriends());
        return userMapper.mapToDto(friends);
    }

    public void addFriend(long userId, long friendId) throws UserNotFoundException {
        User user = findUserById(userId);
        User friend = findUserById(friendId);
        user.getFriends().add(friend);
    }

    public void deleteAllFriends(long userId) throws UserNotFoundException {
        User user = findUserById(userId);
        user.getFriends().clear();
    }

    public void deleteFriend(long userId, long friendId) throws UserNotFoundException {
        User user = findUserById(userId);
        User friend = findUserById(friendId);
        user.getFriends().remove(friend);
    }

    private User findUserById(long userId) throws UserNotFoundException {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }
}
