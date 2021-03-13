package com.moviesandchill.usermanagementservice.service;

import com.moviesandchill.usermanagementservice.dto.staff.StaffDto;
import com.moviesandchill.usermanagementservice.exception.staff.StaffNotFoundException;
import com.moviesandchill.usermanagementservice.exception.user.UserNotFoundException;

import java.util.List;

public interface UserFavoriteStaffService {
    List<StaffDto> getAllFavoriteStaffs(long userId) throws UserNotFoundException;

    void addFavoriteStaff(long userId, long staffId) throws UserNotFoundException;

    void deleteAllFavoriteStaffs(long userId) throws UserNotFoundException;

    void deleteFavoriteStaff(long userId, long staffId) throws UserNotFoundException, StaffNotFoundException;
}
