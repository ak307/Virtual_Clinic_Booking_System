package com.vclinic.virtual_clinic_booking_system.service.utils;

import com.vclinic.virtual_clinic_booking_system.service.user.UserAuthChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class NavBarAuthInfoChangeHelper {
    private final UserAuthChecker userAuthChecker;
    private UserIdentifier userIdentifier;



    @Autowired
    public NavBarAuthInfoChangeHelper(UserAuthChecker userAuthChecker, UserIdentifier userIdentifier) {
        this.userAuthChecker = userAuthChecker;
        this.userIdentifier = userIdentifier;
    }



    public String showNavBarBasedOnUserAuthInfo(Model model, String pageTemplate){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        boolean isUser = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("USER"));

        if (userAuthChecker.isLoggedIn() && isUser){
            System.out.println("USER IS LOGGED IN");

            if (!(authentication instanceof AnonymousAuthenticationToken)) {
                String currentUserName = authentication.getName();

                model.addAttribute("userLogInAuth", currentUserName);
            }

            return pageTemplate;
        }
        else {
            if (!userIdentifier.redirect().equals("noLogin")) {
                return userIdentifier.redirect();
            }
        }

        model.addAttribute("userLogInAuth", null);

        return pageTemplate;
    }
}
