package com.moviesandchill.usermanagementservice.repository;

import com.moviesandchill.usermanagementservice.entity.MessRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessRequestRepository extends JpaRepository<MessRequest, Long> {
}
