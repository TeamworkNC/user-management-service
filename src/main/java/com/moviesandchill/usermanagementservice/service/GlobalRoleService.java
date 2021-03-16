package com.moviesandchill.usermanagementservice.service;

import com.moviesandchill.usermanagementservice.dto.globalrole.GlobalRoleDto;
import com.moviesandchill.usermanagementservice.dto.globalrole.NewGlobalRoleDto;
import com.moviesandchill.usermanagementservice.dto.globalrole.UpdateGlobalRoleDto;
import com.moviesandchill.usermanagementservice.exception.globalrole.GlobalRoleNotFoundException;

import java.util.List;

public interface GlobalRoleService {
    List<GlobalRoleDto> getAllGlobalRoles();

    GlobalRoleDto addGlobalRole(NewGlobalRoleDto newGlobalRoleDto);

    void deleteAllGlobalRoles();

    GlobalRoleDto getGlobalRole(long globalRoleId) throws GlobalRoleNotFoundException;

    void updateGlobalRole(long globalRoleId, UpdateGlobalRoleDto updateUserDto) throws GlobalRoleNotFoundException;

    void deleteGlobalRole(long globalRoleId);
}
