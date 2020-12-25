package com.moviesandchill.usermanagmentservice.converts;

import com.moviesandchill.usermanagmentservice.commands.UserInfoForm;
import com.moviesandchill.usermanagmentservice.domain.User_info;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class UserFormToUserInfo implements Converter<UserInfoForm, User_info> {
    @Override
    public User_info convert(UserInfoForm moviesForm) {
        User_info user = new User_info();
        if (moviesForm.getId() != null  && !StringUtils.isEmpty(moviesForm.getId())) {
            user.setId(new Long(moviesForm.getId()));
        }
        user.setLastName(moviesForm.getLast_name());
        user.setFirstName(moviesForm.getFirst_name());
        return user;
    }
}
