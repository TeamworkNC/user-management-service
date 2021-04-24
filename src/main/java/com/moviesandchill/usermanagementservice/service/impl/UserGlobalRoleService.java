package com.moviesandchill.usermanagementservice.service.impl;

import com.moviesandchill.usermanagementservice.dto.globalrole.GlobalRoleDto;
import com.moviesandchill.usermanagementservice.entity.GlobalRole;
import com.moviesandchill.usermanagementservice.entity.User;
import com.moviesandchill.usermanagementservice.exception.globalrole.GlobalRoleNotFoundException;
import com.moviesandchill.usermanagementservice.exception.user.UserNotFoundException;
import com.moviesandchill.usermanagementservice.mapper.GlobalRoleMapper;
import com.moviesandchill.usermanagementservice.repository.GlobalRoleRepository;
import com.moviesandchill.usermanagementservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
public class UserGlobalRoleService {

    private final UserRepository userRepository;
    private final GlobalRoleRepository globalRoleRepository;
    private final GlobalRoleMapper globalRoleMapper;

    public UserGlobalRoleService(UserRepository userRepository, GlobalRoleRepository globalRoleRepository, GlobalRoleMapper globalRoleMapper) {
        this.userRepository = userRepository;
        this.globalRoleRepository = globalRoleRepository;
        this.globalRoleMapper = globalRoleMapper;
    }

    public List<GlobalRoleDto> getAllGlobalRoles(long userId) throws UserNotFoundException {
        User user = findUserById(userId);
        List<GlobalRole> globalRoles = new ArrayList<>(user.getGlobalRoles());
        return globalRoleMapper.mapToDto(globalRoles);
    }

    public void addGlobalRole(long userId, long globalRoleId) throws UserNotFoundException, GlobalRoleNotFoundException {
        User user = findUserById(userId);
        GlobalRole globalRole = findGlobalRoleById(globalRoleId);
        user.getGlobalRoles().add(globalRole);
    }

    public void deleteAllGlobalRoles(long userId) throws UserNotFoundException {
        User user = findUserById(userId);
        user.getGlobalRoles().clear();
    }

    public void deleteGlobalRole(long userId, long globalRoleId) throws UserNotFoundException, GlobalRoleNotFoundException {
        User user = findUserById(userId);
        GlobalRole globalRole = findGlobalRoleById(globalRoleId);
        user.getGlobalRoles().remove(globalRole);
    }

    private User findUserById(long userId) throws UserNotFoundException {
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    private GlobalRole findGlobalRoleById(long globalRoleId) throws GlobalRoleNotFoundException {
        return globalRoleRepository.findById(globalRoleId).orElseThrow(GlobalRoleNotFoundException::new);
    }
}
