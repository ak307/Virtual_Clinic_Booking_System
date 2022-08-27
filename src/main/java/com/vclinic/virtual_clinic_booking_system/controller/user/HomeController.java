package com.vclinic.virtual_clinic_booking_system.controller.user;

import com.vclinic.virtual_clinic_booking_system.registration.token.ConfirmationTokenService;
import com.vclinic.virtual_clinic_booking_system.service.user.UserAuthChecker;
import com.vclinic.virtual_clinic_booking_system.service.utils.NavBarAuthInfoChangeHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Timer;
import java.util.TimerTask;

@Controller
@RequestMapping("/")
public class HomeController {


    private final NavBarAuthInfoChangeHelper navBarAuthInfoChangeHelper;
    private final ConfirmationTokenService confirmationTokenService;
    private final UserAuthChecker userAuthChecker;



    @Autowired
    public HomeController(NavBarAuthInfoChangeHelper navBarAuthInfoChangeHelper, ConfirmationTokenService confirmationTokenService, UserAuthChecker userAuthChecker) {
        this.navBarAuthInfoChangeHelper = navBarAuthInfoChangeHelper;
        this.confirmationTokenService = confirmationTokenService;
        this.userAuthChecker = userAuthChecker;
    }


    @GetMapping("/home")
    public String showHomePage(Model model){
        String templateName = "index.html";

        return navBarAuthInfoChangeHelper.showNavBarBasedOnUserAuthInfo(model, templateName);
    }








}
