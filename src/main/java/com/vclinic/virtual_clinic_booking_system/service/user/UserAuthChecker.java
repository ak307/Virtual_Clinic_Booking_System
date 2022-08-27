package com.vclinic.virtual_clinic_booking_system.service.user;

import com.vclinic.virtual_clinic_booking_system.registration.RegistrationForm;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserAuthChecker {

    public boolean isLoggedIn(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // if not logged in
        return authentication != null && !(authentication instanceof AnonymousAuthenticationToken);
    }
}
