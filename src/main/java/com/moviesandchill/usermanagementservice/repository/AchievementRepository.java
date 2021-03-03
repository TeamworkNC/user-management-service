package com.moviesandchill.usermanagementservice.repository;

import com.moviesandchill.usermanagementservice.entity.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AchievementRepository extends JpaRepository<Achievement, Long> {
}
