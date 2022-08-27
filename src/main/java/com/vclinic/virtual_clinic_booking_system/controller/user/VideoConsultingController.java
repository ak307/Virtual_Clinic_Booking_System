package com.vclinic.virtual_clinic_booking_system.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class VideoConsultingController {

    @GetMapping("/videoConsult/*")
    public String showVideoConsultingController(Model model, HttpServletRequest request){
        String currentUrl = request.getRequestURI();
        String videoRoom = currentUrl.substring(currentUrl.length() - 5);

        if (!videoRoom.equals("le.js"))
            model.addAttribute("room", videoRoom);

        return "video_consulting_page.html";
    }

}
