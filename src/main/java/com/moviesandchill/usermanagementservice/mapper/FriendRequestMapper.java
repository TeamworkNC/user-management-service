package com.moviesandchill.usermanagementservice.mapper;


import com.moviesandchill.usermanagementservice.dto.friendrequest.FriendRequestDto;
import com.moviesandchill.usermanagementservice.dto.friendrequest.NewFriendRequestDto;
import com.moviesandchill.usermanagementservice.entity.FriendRequest;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface FriendRequestMapper {
    FriendRequest mapToEntity(NewFriendRequestDto dto);

    List<FriendRequestDto> mapToDto(List<FriendRequest> entities);

    FriendRequestDto mapToDto(FriendRequest entity);
}
