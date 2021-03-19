package com.moviesandchill.usermanagementservice.repository;

import com.moviesandchill.usermanagementservice.entity.GlobalRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GlobalRoleRepository extends JpaRepository<GlobalRole, Long> {
    Optional<GlobalRole> findByName(String name);
}
