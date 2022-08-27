package com.vclinic.virtual_clinic_booking_system.service.user;

import com.vclinic.virtual_clinic_booking_system.model.user.AppUser;
import com.vclinic.virtual_clinic_booking_system.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private AppUserRepository appUserRepository;

    @Autowired
    public LoginService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public Boolean checkLoginCredentials(String email, String password){
        String encodedLoginPassword = bCryptPasswordEncoder.encode(password);

        if (appUserRepository.findByEmail(email).isPresent()){
            AppUser appUser = appUserRepository.findByEmail(email).get();

            return appUser.getEmail().equals(email) &&
                    appUser.getPassword().equals(encodedLoginPassword) &&
                    appUser.getEnabled();

        }

        return false;
    }



}
