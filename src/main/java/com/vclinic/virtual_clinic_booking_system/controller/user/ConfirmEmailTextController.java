package com.vclinic.virtual_clinic_booking_system.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ConfirmEmailTextController {

    @GetMapping("/confirmEmailText")
    public String showConfirmEmailText(){
        return "confirmEmailText.html";
    }






}
