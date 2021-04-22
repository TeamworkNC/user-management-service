package com.moviesandchill.usermanagementservice.repository;

import com.moviesandchill.usermanagementservice.entity.FriendRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {
    List<FriendRequest> findAllByRecipientId(long friendId);

    void deleteAllByUserIdAndRecipientId(long userId, long recipientId);
}

