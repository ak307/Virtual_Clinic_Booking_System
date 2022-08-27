package com.vclinic.virtual_clinic_booking_system.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/physicalAppointment")
public class PhysicalAppointmentBookingSuccessfulPageController {

    @GetMapping("/bookingSuccessful")
    public String showSuccessfulPage() {
        return "physical_appointment_booking_successful.html";
    }
}
