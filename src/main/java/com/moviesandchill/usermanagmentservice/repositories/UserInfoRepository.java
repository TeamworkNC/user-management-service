package com.moviesandchill.usermanagmentservice.repositories;

import com.moviesandchill.usermanagmentservice.domain.User_info;
import org.springframework.data.repository.CrudRepository;

public interface UserInfoRepository extends CrudRepository<User_info, Long> {
}
