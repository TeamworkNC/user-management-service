package com.moviesandchill.usermanagmentservice.converts;

import org.springframework.core.convert.converter.Converter;
import com.moviesandchill.usermanagmentservice.commands.UserInfoForm;
import com.moviesandchill.usermanagmentservice.domain.User_info;
import org.springframework.stereotype.Component;

@Component
public class UserInfoToUserForm implements Converter<User_info, UserInfoForm> {
    @Override
    public UserInfoForm convert(User_info userinfo) {
        UserInfoForm userinfoForm = new UserInfoForm();
        userinfoForm.setId(userinfo.getId());
        userinfoForm.setFirst_name(userinfo.getFirstName());
        userinfoForm.setLast_name(userinfo.getLastName());
        return userinfoForm;
    }
}
