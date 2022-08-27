package com.vclinic.virtual_clinic_booking_system.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TokenConfirmedThankYouController {

    @GetMapping("/confirmedToken")
    public String showTokenConfirmedThankYouPage(){
        return "token_confirmed_thank_you_page.html";
    }




}
