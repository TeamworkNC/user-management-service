package com.moviesandchill.usermanagementservice.service;

import com.moviesandchill.usermanagementservice.dto.friendrequest.FriendRequestDto;
import com.moviesandchill.usermanagementservice.mapper.FriendRequestMapper;
import com.moviesandchill.usermanagementservice.repository.FriendRequestRepository;
import com.moviesandchill.usermanagementservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Slf4j
public class UserFriendRequestService {

    private FriendRequestRepository friendRequestRepository;
    private UserRepository userRepository;
    private FriendRequestMapper friendRequestMapper;

    public List<FriendRequestDto> getAllFriendRequests(long userId) {
        var friendRequests = friendRequestRepository.findAllByRecipientId(userId);
        return friendRequestMapper.mapToDto(friendRequests);
    }

    public void acceptFriendRequest(long userId, long friendRequestId) {
        var friendRequest = friendRequestRepository.findById(friendRequestId)
                .orElseThrow();

        long firstUserId = friendRequest.getUserId();
        long secondUserId = friendRequest.getRecipientId();

        var firstUser = userRepository.findById(firstUserId)
                .orElseThrow();
        var secondUser = userRepository.findById(secondUserId)
                .orElseThrow();

        firstUser.getFriends().add(secondUser);
        secondUser.getFriends().add(firstUser);

        friendRequestRepository.deleteAllByUserIdAndRecipientId(firstUserId, secondUserId);
        friendRequestRepository.deleteAllByUserIdAndRecipientId(secondUserId, firstUserId);
    }

    public void declineFriendRequest(long userId, long friendRequestId) {
        friendRequestRepository.deleteById(friendRequestId);
    }

    @Autowired
    public void setFriendRequestRepository(FriendRequestRepository friendRequestRepository) {
        this.friendRequestRepository = friendRequestRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setFriendRequestMapper(FriendRequestMapper friendRequestMapper) {
        this.friendRequestMapper = friendRequestMapper;
    }
}

