package com.moviesandchill.usermanagementservice.service.impl;

import com.moviesandchill.usermanagementservice.dto.staff.StaffDto;
import com.moviesandchill.usermanagementservice.entity.Staff;
import com.moviesandchill.usermanagementservice.entity.User;
import com.moviesandchill.usermanagementservice.exception.staff.StaffNotFoundException;
import com.moviesandchill.usermanagementservice.exception.user.UserNotFoundException;
import com.moviesandchill.usermanagementservice.mapper.StaffMapper;
import com.moviesandchill.usermanagementservice.repository.StaffRepository;
import com.moviesandchill.usermanagementservice.repository.UserRepository;
import com.moviesandchill.usermanagementservice.service.UserFavoriteStaffService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
public class UserFavoriteStaffServiceImpl implements UserFavoriteStaffService {
    private final UserRepository userRepository;
    private final StaffRepository staffRepository;
    private final StaffMapper staffMapper;

    public UserFavoriteStaffServiceImpl(UserRepository userRepository, StaffRepository staffRepository, StaffMapper staffMapper) {
        this.userRepository = userRepository;
        this.staffRepository = staffRepository;
        this.staffMapper = staffMapper;
    }

    @Override
    public List<StaffDto> getAllFavoriteStaffs(long userId) throws UserNotFoundException {
        User user = findUserById(userId);
        var staffs = new ArrayList<>(user.getFavoriteStaffs());
        return staffMapper.mapToDto(staffs);
    }

    @Override
    public void addFavoriteStaff(long userId, long staffId) throws UserNotFoundException {
        User user = findUserById(userId);
        Staff staff = staffMapper.mapToEntity(staffId);
        staff = staffRepository.save(staff);
        user.getFavoriteStaffs().add(staff);
    }

    @Override
    public void deleteAllFavoriteStaffs(long userId) throws UserNotFoundException {
        User user = findUserById(userId);
        user.getFavoriteStaffs().clear();
    }

    @Override
    public void deleteFavoriteStaff(long userId, long staffId) throws UserNotFoundException, StaffNotFoundException {
        User user = findUserById(userId);
        Staff staff = findStaffById(staffId);
        user.getFavoriteStaffs().remove(staff);
    }

    private User findUserById(long userId) throws UserNotFoundException {
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    private Staff findStaffById(long staffId) throws StaffNotFoundException {
        return staffRepository.findById(staffId).orElseThrow(StaffNotFoundException::new);
    }
}
