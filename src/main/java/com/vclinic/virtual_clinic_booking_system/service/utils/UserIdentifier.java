package com.vclinic.virtual_clinic_booking_system.service.utils;

import com.vclinic.virtual_clinic_booking_system.service.user.UserAuthChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserIdentifier {
    private final UserAuthChecker userAuthChecker;


    @Autowired
    public UserIdentifier(UserAuthChecker userAuthChecker) {
        this.userAuthChecker = userAuthChecker;
    }

    public String redirect(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        boolean isUser = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("USER"));

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ADMIN"));

        boolean isDoctor = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("DOCTOR"));



        if (userAuthChecker.isLoggedIn() && isAdmin) {
            return "redirect:/admin/dashboard";
        }
        else if (userAuthChecker.isLoggedIn() && isUser){
            return "redirect:/home";
        }
        else if (userAuthChecker.isLoggedIn() && isDoctor){
            return "redirect:/doctor/dashboard";
        }
        else {
            return "noLogin";
        }
    }
}
