package com.vclinic.virtual_clinic_booking_system.controller.user;

import com.vclinic.virtual_clinic_booking_system.registration.RegistrationForm;
import com.vclinic.virtual_clinic_booking_system.service.user.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;


@Controller
@AllArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;


    @GetMapping("/register")
    public String showSignUpPage(Model model){
        model.addAttribute("registerForm", new RegistrationForm());
        return "register.html";
    }

    @PostMapping( "/register")
    public String registerUser(@ModelAttribute("registerForm") @Valid RegistrationForm registrationForm,
                               BindingResult result, RedirectAttributes redirectAttributes){
        if (result.hasErrors()) {
            return "register.html";
        }


        if (!registrationForm.getPassword().equals(registrationForm.getConfirmPassword())){
            redirectAttributes.addFlashAttribute("passwordError", "Two password fields must be the same.");
            return "redirect:/register";
        }

        return registrationService.register(registrationForm);
    }


    @GetMapping("/register/confirm")
    private ModelAndView confirm(@RequestParam("token") String token) {
        String url = registrationService.confirmToken(token);
        return new ModelAndView(url);
    }




}
