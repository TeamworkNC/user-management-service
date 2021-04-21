package com.moviesandchill.usermanagementservice.service.impl;

import com.moviesandchill.usermanagementservice.dto.messrequest.MessRequestDto;
import com.moviesandchill.usermanagementservice.dto.user.UserDto;
import com.moviesandchill.usermanagementservice.exception.messrequest.MessRequestNotFoundException;
import com.moviesandchill.usermanagementservice.exception.user.UserNotFoundException;
import com.moviesandchill.usermanagementservice.repository.MessRequestRepository;
import com.moviesandchill.usermanagementservice.repository.UserRepository;
import com.moviesandchill.usermanagementservice.service.UserMessRequestService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserMessRequestServiceImp implements UserMessRequestService {

    private final UserRepository userRepository;
    private final MessRequestRepository messRequestRepository;

    public UserMessRequestServiceImp(UserRepository userRepository, MessRequestRepository messRequestRepository) {
        this.userRepository = userRepository;
        this.messRequestRepository = messRequestRepository;
    }

    @Override
    public UserDto getUserByMessRequest(long messRequestId) {
        return null;
    }

    @Override
    public UserDto getFriendByMessRequest(long messRequestId) {
        return null;
    }

    @Override
    public List<MessRequestDto> getAllMessRequests(long userId) throws UserNotFoundException {
        return null;
    }

    @Override
    public void addMessRequest(long userId, long friendId, long messRequestId) throws UserNotFoundException {

    }

    @Override
    public void deleteAllMessRequests(long userId) throws UserNotFoundException {

    }

    @Override
    public void deleteMessRequest(long userId, long messRequestId) throws UserNotFoundException, MessRequestNotFoundException {

    }
}
