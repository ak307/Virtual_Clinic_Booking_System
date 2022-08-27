package com.vclinic.virtual_clinic_booking_system.controller.user;

import com.vclinic.virtual_clinic_booking_system.service.utils.NavBarAuthInfoChangeHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OurDoctorsController {
    private NavBarAuthInfoChangeHelper navBarAuthInfoChangeHelper;

    @Autowired
    public OurDoctorsController(NavBarAuthInfoChangeHelper navBarAuthInfoChangeHelper) {
        this.navBarAuthInfoChangeHelper = navBarAuthInfoChangeHelper;
    }

    @GetMapping("/ourDoctor")
    public String showOurDoctorsPage(Model model){
        String templateName = "ourDoctor.html";
        return navBarAuthInfoChangeHelper.showNavBarBasedOnUserAuthInfo(model, templateName);
    }

}
