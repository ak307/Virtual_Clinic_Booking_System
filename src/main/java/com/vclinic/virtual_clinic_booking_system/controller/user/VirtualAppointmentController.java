package com.vclinic.virtual_clinic_booking_system.controller.user;

import com.vclinic.virtual_clinic_booking_system.service.utils.NavBarAuthInfoChangeHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VirtualAppointmentController {
    private NavBarAuthInfoChangeHelper navBarAuthInfoChangeHelper;

    @Autowired
    public VirtualAppointmentController(NavBarAuthInfoChangeHelper navBarAuthInfoChangeHelper) {
        this.navBarAuthInfoChangeHelper = navBarAuthInfoChangeHelper;
    }

    @GetMapping("virtualAppointment")
    public String showVirtualAppointmentPage(Model model){


        String templateName = "virtual_appointment.html";
        return navBarAuthInfoChangeHelper.showNavBarBasedOnUserAuthInfo(model, templateName);
    }
}
