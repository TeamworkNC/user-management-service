package com.moviesandchill.usermanagementservice.dto.friendrequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FriendRequestDto {
    private Long friendRequestId;

    private long userId;

    private long recipientId;
}
