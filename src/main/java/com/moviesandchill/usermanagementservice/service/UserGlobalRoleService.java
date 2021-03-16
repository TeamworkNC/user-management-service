package com.moviesandchill.usermanagementservice.service;

import com.moviesandchill.usermanagementservice.dto.globalrole.GlobalRoleDto;
import com.moviesandchill.usermanagementservice.exception.globalrole.GlobalRoleNotFoundException;
import com.moviesandchill.usermanagementservice.exception.user.UserNotFoundException;

import java.util.List;

public interface UserGlobalRoleService {
    List<GlobalRoleDto> getAllGlobalRoles(long userId) throws UserNotFoundException;

    void addGlobalRole(long userId, long globalRoleId) throws UserNotFoundException, GlobalRoleNotFoundException;

    void deleteAllGlobalRoles(long userId) throws UserNotFoundException;

    void deleteGlobalRole(long userId, long globalRoleId) throws UserNotFoundException, GlobalRoleNotFoundException;
}
