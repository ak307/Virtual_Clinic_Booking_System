package com.vclinic.virtual_clinic_booking_system.controller.user;

import com.vclinic.virtual_clinic_booking_system.model.user.form.ContactUsForm;
import com.vclinic.virtual_clinic_booking_system.service.user.ContactUsService;
import com.vclinic.virtual_clinic_booking_system.service.utils.NavBarAuthInfoChangeHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class ContactUsController {
    private NavBarAuthInfoChangeHelper navBarAuthInfoChangeHelper;
    private ContactUsService contactUsService;

    @Autowired
    public ContactUsController(NavBarAuthInfoChangeHelper navBarAuthInfoChangeHelper, ContactUsService contactUsService) {
        this.navBarAuthInfoChangeHelper = navBarAuthInfoChangeHelper;
        this.contactUsService = contactUsService;
    }


    @GetMapping("contactUs")
    public String showContactUsPage(Model model){
        String templateName = "contactUs.html";
        model.addAttribute("contactForm", new ContactUsForm());
        return navBarAuthInfoChangeHelper.showNavBarBasedOnUserAuthInfo(model, templateName);
    }

    @PostMapping("contactUs/send")
    public String registerUser(@ModelAttribute("contactForm") @Valid ContactUsForm contactUsForm,
                               BindingResult result, RedirectAttributes redirectAttributes){

        if (result.hasErrors()) {
            return "contactUs.html";
        }

        String res = contactUsService.saveMessage(contactUsForm.getEmail(), contactUsForm.getName());

        if (res.equals("successful")){
            redirectAttributes.addFlashAttribute("success", "Message sent successfully.");
            return "redirect:/contactUs";
        }


        return "contactUs.html";
    }

}
