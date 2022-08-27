package com.vclinic.virtual_clinic_booking_system.service.user;

import com.vclinic.virtual_clinic_booking_system.model.user.AppUser;
import com.vclinic.virtual_clinic_booking_system.model.user.enums.AppUserRole;
import com.vclinic.virtual_clinic_booking_system.registration.EmailValidator;
import com.vclinic.virtual_clinic_booking_system.registration.RegistrationForm;
import com.vclinic.virtual_clinic_booking_system.repository.AppUserRepository;
import com.vclinic.virtual_clinic_booking_system.service.user.AppUserService;
import com.vclinic.virtual_clinic_booking_system.model.user.UserRole;
import com.vclinic.virtual_clinic_booking_system.service.email.EmailSender;
import com.vclinic.virtual_clinic_booking_system.registration.token.ConfirmationToken;
import com.vclinic.virtual_clinic_booking_system.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationService {
    private final EmailValidator emailValidator;
    private final AppUserService appUserService;

    private final ConfirmationTokenService confirmationTokenService;
    private final AppUserRepository appUserRepository;


    public String register(RegistrationForm request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if (!isValidEmail)
            throw new IllegalStateException("Email is not valid.");


        return appUserService.signUpUser(
                        new AppUser(
                                request.getUserName(),
                                request.getEmail(),
                                request.getPassword(),
                                new UserRole(AppUserRole.USER.toString())));

 }

    public String registerDoctor(RegistrationForm request){
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if (!isValidEmail)
            throw new IllegalStateException("Email is not valid.");

        if (appUserService.getUserByEmail(request.getEmail()) != null){
            return "User is already created.";
        }

        appUserService.signUpUser(
                new AppUser(
                        request.getUserName(),
                        request.getEmail(),
                        request.getPassword(),
                        new UserRole(AppUserRole.DOCTOR.toString())
                )
        );

        return "successful";
    }

    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("Token not found"));


        if (confirmationToken.getConfirmedAt() != null)
            return "Email is already confirmed";


        LocalDateTime expiredAt = confirmationToken.getExpiredAt();

        if (expiredAt.isBefore(LocalDateTime.now()))
            return "Token expired.";

        confirmationTokenService.setConfirmedAt(token);
        appUserService.enableAppUserAndDisableLockToFalse(confirmationToken.getAppUser().getEmail());

//        return "redirect:/login";
        return "redirect:/confirmedToken";
    }

}
