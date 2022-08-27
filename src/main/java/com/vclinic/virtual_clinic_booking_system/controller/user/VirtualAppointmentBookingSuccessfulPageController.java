package com.vclinic.virtual_clinic_booking_system.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/virtualAppointment")
public class VirtualAppointmentBookingSuccessfulPageController {


    @GetMapping("/bookingSuccessful")
    public String showBookingSuccessPage(){
        return "virtual_appointment_booking_successful.html";
    }
}
