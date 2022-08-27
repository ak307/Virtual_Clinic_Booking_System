package com.vclinic.virtual_clinic_booking_system.controller.user;

import com.vclinic.virtual_clinic_booking_system.service.user.LoginService;
import com.vclinic.virtual_clinic_booking_system.service.utils.UserIdentifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class LoginController {

    private LoginService loginService;

    private UserIdentifier userIdentifier;

    public LoginController() {
    }

    @Autowired
    public LoginController(LoginService loginService, UserIdentifier userIdentifier) {
        this.loginService = loginService;
        this.userIdentifier = userIdentifier;
    }





    @GetMapping("/login")
    public String showLogInPage(){
        if (!userIdentifier.redirect().equals("noLogin"))
            return userIdentifier.redirect();
        return "login.html";
    }





}
