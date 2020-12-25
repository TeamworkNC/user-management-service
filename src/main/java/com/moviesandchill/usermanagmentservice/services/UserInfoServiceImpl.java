package com.moviesandchill.usermanagmentservice.services;

import com.moviesandchill.usermanagmentservice.commands.UserInfoForm;
import com.moviesandchill.usermanagmentservice.converts.UserFormToUserInfo;
import com.moviesandchill.usermanagmentservice.domain.User_info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.moviesandchill.usermanagmentservice.repositories.UserInfoRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    private UserInfoRepository userinfoRepository;
    private UserFormToUserInfo userFormToUserInfo;

    @Autowired
    public UserInfoServiceImpl(UserInfoRepository userRepository, UserFormToUserInfo userFormToUserInfo) {
        this.userinfoRepository = userRepository;
        this.userFormToUserInfo = userFormToUserInfo;
    }

    @Override
    public List<User_info> listAll() {
        List<User_info> userInfo = new ArrayList<>();
        userinfoRepository.findAll().forEach(userInfo::add);
        return userInfo;
    }

    @Override
    public User_info getById(Long id) {
        return userinfoRepository.findById(id).orElse(null);
    }

    @Override
    public User_info saveOrUpdate(User_info userInfo) {
        userinfoRepository.save(userInfo);
        return userInfo;
    }

    @Override
    public void delete(Long id) {
        userinfoRepository.deleteById(id);

    }

    @Override
    public User_info saveOrUpdateUserForm(UserInfoForm userInfoForm) {
        User_info savedUser = saveOrUpdate(userFormToUserInfo.convert(userInfoForm));

        System.out.println("Saved user Id: " + savedUser.getId());
        return savedUser;
    }
}
