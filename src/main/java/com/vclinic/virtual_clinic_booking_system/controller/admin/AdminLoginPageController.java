package com.vclinic.virtual_clinic_booking_system.controller.admin;


import com.vclinic.virtual_clinic_booking_system.service.utils.UserIdentifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminLoginPageController {
    private final UserIdentifier userIdentifier;


    @Autowired
    public AdminLoginPageController(UserIdentifier userIdentifier) {
        this.userIdentifier = userIdentifier;
    }



    @GetMapping("/login")
    public String showAdminLoginPage(){
        if (!userIdentifier.redirect().equals("noLogin"))
            return userIdentifier.redirect();


        return "adminLogin.html";
    }
}
