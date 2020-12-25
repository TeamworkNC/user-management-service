package com.moviesandchill.usermanagmentservice.controllers;


import com.moviesandchill.usermanagmentservice.commands.UserInfoForm;
import com.moviesandchill.usermanagmentservice.converts.UserInfoToUserForm;
import com.moviesandchill.usermanagmentservice.domain.User_info;
import com.moviesandchill.usermanagmentservice.services.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class UserInfoController {
    private UserInfoService userInfoService;

    private UserInfoToUserForm userInfoToUserForm;

    @Autowired
    public void setUserInfoToUserForm(UserInfoToUserForm userInfoToUserForm) {
        this.userInfoToUserForm = userInfoToUserForm;
    }

    @Autowired
    public void setUserInfoService(UserInfoService moviesService) {
        this.userInfoService = moviesService;
    }

    @RequestMapping("/")
    public String redirToList(){
        return "redirect:/user/list";
    }

    @RequestMapping({"/user/list", "/user"})
    public String listUsers(Model model){
        model.addAttribute("userInfo", userInfoService.listAll());
        return "user/list";
    }

    @RequestMapping("/user/show/{id}")
    public String getUser(@PathVariable String id, Model model){
        model.addAttribute("userInfo", userInfoService.getById(Long.valueOf(id)));
        return "user/show";
    }

    @RequestMapping("user/edit/{id}")
    public String edit(@PathVariable String id, Model model){
        User_info user = userInfoService.getById(Long.valueOf(id));
        UserInfoForm userInfoForm = userInfoToUserForm.convert(user);

        model.addAttribute("userInfoForm", userInfoForm);
        return "user/userform";
    }

    @RequestMapping("/user/new")
    public String newMovies(Model model){
        model.addAttribute("userInfoForm", new UserInfoForm());
        return "user/userform";
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String saveOrUpdateUser(@Valid UserInfoForm moviesForm, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "user/userform";
        }
        
        User_info savedUser = userInfoService.saveOrUpdateUserForm(moviesForm);

        return "redirect:/user/show/" + savedUser.getId();
    }

    @RequestMapping("/user/delete/{id}")
    public String delete(@PathVariable String id){
        userInfoService.delete(Long.valueOf(id));
        return "redirect:/user/list";
    }
}
