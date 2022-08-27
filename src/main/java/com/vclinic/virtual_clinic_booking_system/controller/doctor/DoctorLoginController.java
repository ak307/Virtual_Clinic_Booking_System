package com.vclinic.virtual_clinic_booking_system.controller.doctor;


import com.vclinic.virtual_clinic_booking_system.service.utils.UserIdentifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/doctor")
public class DoctorLoginController {
    private UserIdentifier userIdentifier;


    @Autowired
    public DoctorLoginController(UserIdentifier userIdentifier) {
        this.userIdentifier = userIdentifier;
    }

    @GetMapping("/login")
    public String showDoctorLoginPage(){
        if (!userIdentifier.redirect().equals("noLogin"))
            return userIdentifier.redirect();

        return "doctorLogin.html";
    }
}
