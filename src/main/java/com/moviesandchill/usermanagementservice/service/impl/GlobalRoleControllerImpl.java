package com.moviesandchill.usermanagementservice.service.impl;

import com.moviesandchill.usermanagementservice.dto.globalrole.GlobalRoleDto;
import com.moviesandchill.usermanagementservice.dto.globalrole.NewGlobalRoleDto;
import com.moviesandchill.usermanagementservice.dto.globalrole.UpdateGlobalRoleDto;
import com.moviesandchill.usermanagementservice.entity.GlobalRole;
import com.moviesandchill.usermanagementservice.exception.globalrole.GlobalRoleNotFoundException;
import com.moviesandchill.usermanagementservice.mapper.GlobalRoleMapper;
import com.moviesandchill.usermanagementservice.repository.GlobalRoleRepository;
import com.moviesandchill.usermanagementservice.service.GlobalRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
public class GlobalRoleControllerImpl implements GlobalRoleService {
    private final GlobalRoleRepository globalRoleRepository;
    private final GlobalRoleMapper globalRoleMapper;

    public GlobalRoleControllerImpl(GlobalRoleRepository globalRoleRepository, GlobalRoleMapper globalRoleMapper) {
        this.globalRoleRepository = globalRoleRepository;
        this.globalRoleMapper = globalRoleMapper;
    }

    @Override
    public List<GlobalRoleDto> getAllGlobalRoles() {
        var globalRoles = new ArrayList<>(globalRoleRepository.findAll());
        return globalRoleMapper.mapToDto(globalRoles);
    }

    @Override
    public GlobalRoleDto addGlobalRole(NewGlobalRoleDto newGlobalRoleDto) {
        GlobalRole globalRole = globalRoleMapper.mapToEntity(newGlobalRoleDto);
        globalRole = globalRoleRepository.save(globalRole);
        return globalRoleMapper.mapToDto(globalRole);
    }

    @Override
    public void deleteAllGlobalRoles() {
        globalRoleRepository.deleteAll();
    }

    @Override
    public GlobalRoleDto getGlobalRole(long globalRoleId) throws GlobalRoleNotFoundException {
        var globalRole = findGlobalRoleById(globalRoleId);
        return globalRoleMapper.mapToDto(globalRole);
    }

    @Override
    public void updateGlobalRole(long globalRoleId, UpdateGlobalRoleDto updateUserDto) throws GlobalRoleNotFoundException {
        GlobalRole globalRole = findGlobalRoleById(globalRoleId);
        globalRoleMapper.updateEntity(globalRole, updateUserDto);
    }

    @Override
    public void deleteGlobalRole(long globalRoleId) {
        globalRoleRepository.deleteById(globalRoleId);
    }

    private GlobalRole findGlobalRoleById(long globalRoleId) throws GlobalRoleNotFoundException {
        return globalRoleRepository.findById(globalRoleId).orElseThrow(GlobalRoleNotFoundException::new);
    }
}
