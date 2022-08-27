package com.vclinic.virtual_clinic_booking_system.registration.token;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmationTokenRepository;


    public void saveConfirmationToken(ConfirmationToken token){
        confirmationTokenRepository.save(token);
    }

    public Optional<ConfirmationToken> getToken(String token){
        return confirmationTokenRepository.findByToken(token);
    }


    public void setConfirmedAt(String token){
        confirmationTokenRepository.confirmTokenAt(token);
    }


    public String getConfirmAtByUserId(Long userId){
        return confirmationTokenRepository.findConfirmAtByAppUserID(userId);

    }

    public String getTokenByUserId(Long userId){
        return confirmationTokenRepository.findTokenByAppUserID(userId);
    }


    public boolean userIsConfirmToken(String username) {
        String confirmedAt = confirmationTokenRepository.findConfirmedAt(username);
        return confirmedAt != null;
    }

}
