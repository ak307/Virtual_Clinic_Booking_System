package com.vclinic.virtual_clinic_booking_system.controller.admin;

import com.vclinic.virtual_clinic_booking_system.registration.RegistrationForm;
import com.vclinic.virtual_clinic_booking_system.service.user.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminCreateDoctorController {
    private final RegistrationService registrationService;

    @Autowired
    public AdminCreateDoctorController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping("/createDoctor")
    public String showCreateDoctorPage(Model model){
        model.addAttribute("registerForm", new RegistrationForm());

        return "admin_create_doctor.html";
    }


    @PostMapping( "/register")
    public String registerUser(@ModelAttribute("registerForm") @Valid RegistrationForm registrationForm,
                               BindingResult result, RedirectAttributes redirectAttributes){

        if (result.hasErrors()) {
            return "admin_create_doctor.html";
        }

        System.out.println("email ->" + registrationForm.getEmail());
        System.out.println("name ->" + registrationForm.getUserName());
        System.out.println("pass ->" + registrationForm.getPassword());
        System.out.println("confirm-pass ->" + registrationForm.getConfirmPassword());


        if (!registrationForm.getPassword().equals(registrationForm.getConfirmPassword())){
            redirectAttributes.addFlashAttribute("passwordError", "Two password fields must be the same.");
            return "redirect:/admin/createDoctor";
        }

        String returnValue =  registrationService.registerDoctor(registrationForm);

        if (returnValue.equals("successful")){
            redirectAttributes.addFlashAttribute("addSuccessful", "Doctor is added successfully.");
            System.out.println();
        }
        else {
            redirectAttributes.addFlashAttribute("unSuccessful", "This data is already added.");
        }
        return "redirect:/admin/createDoctor";
    }
}
