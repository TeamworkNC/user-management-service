package com.moviesandchill.usermanagementservice.controller;

import com.moviesandchill.usermanagementservice.dto.globalrole.GlobalRoleDto;
import com.moviesandchill.usermanagementservice.exception.globalrole.GlobalRoleNotFoundException;
import com.moviesandchill.usermanagementservice.exception.user.UserNotFoundException;
import com.moviesandchill.usermanagementservice.service.impl.UserGlobalRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(
        path = "api/v1/users/{userId}/global-roles",
        produces = "application/json"
)
@Slf4j
public class UserGlobalRoleController {

    private final UserGlobalRoleService userGlobalRoleService;

    public UserGlobalRoleController(UserGlobalRoleService globalRoleService) {
        this.userGlobalRoleService = globalRoleService;
    }

    @GetMapping
    public List<GlobalRoleDto> getAllGlobalRoles(@PathVariable long userId) throws UserNotFoundException {
        return userGlobalRoleService.getAllGlobalRoles(userId);
    }

    @PostMapping
    public void addGlobalRole(@PathVariable long userId, @RequestBody long globalRoleId) throws UserNotFoundException, GlobalRoleNotFoundException {
        userGlobalRoleService.addGlobalRole(userId, globalRoleId);
    }

    @DeleteMapping
    public void deleteAllGlobalRoles(@PathVariable long userId) throws UserNotFoundException {
        userGlobalRoleService.deleteAllGlobalRoles(userId);
    }

    @DeleteMapping("/{globalRoleId}")
    public void deleteGlobalRole(@PathVariable long userId, @PathVariable long globalRoleId) throws UserNotFoundException, GlobalRoleNotFoundException {
        userGlobalRoleService.deleteGlobalRole(userId, globalRoleId);
    }
}
