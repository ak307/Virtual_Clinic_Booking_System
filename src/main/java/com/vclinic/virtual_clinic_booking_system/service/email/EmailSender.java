package com.vclinic.virtual_clinic_booking_system.service.email;

import org.springframework.stereotype.Service;

public interface EmailSender {

    void send(String to, String email, String subject);
}
