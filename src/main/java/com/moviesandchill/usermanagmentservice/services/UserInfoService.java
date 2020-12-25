package com.moviesandchill.usermanagmentservice.services;

import com.moviesandchill.usermanagmentservice.commands.UserInfoForm;
import com.moviesandchill.usermanagmentservice.domain.User_info;

import java.util.List;

public interface UserInfoService {

    List<User_info> listAll();

    User_info getById(Long _id);

    User_info saveOrUpdate(User_info userInfo);

    void delete(Long id);

    User_info saveOrUpdateUserForm(UserInfoForm moviesForm);
}
