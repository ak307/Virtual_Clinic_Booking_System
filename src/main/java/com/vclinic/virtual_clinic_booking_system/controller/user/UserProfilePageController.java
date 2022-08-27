package com.vclinic.virtual_clinic_booking_system.controller.user;

import com.vclinic.virtual_clinic_booking_system.model.user.AppUser;
import com.vclinic.virtual_clinic_booking_system.service.user.AppUserService;
import com.vclinic.virtual_clinic_booking_system.model.user.form.UserProfileForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class UserProfilePageController {
    private AppUserService appUserService;

    @Autowired
    public UserProfilePageController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping("/user/profile")
    public String showUserProfile(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUserName = authentication.getName();
        String phoneNo = "";

        AppUser currentUserInfo = appUserService.getUserByUsername(loggedInUserName);

        if (currentUserInfo.getPhoneNumber() == null || currentUserInfo.getPhoneNumber().equals(""))
            phoneNo = "-";

        else phoneNo = currentUserInfo.getPhoneNumber();

        model.addAttribute("user_profile_form", new UserProfileForm(currentUserInfo.getUsername(), currentUserInfo.getEmail(), phoneNo));

        return "user_profile_page.html";
    }


    @PostMapping("/user/updateUser")
    public String saveUserProfileInfo(@ModelAttribute("user_profile_form") @Valid UserProfileForm userProfileForm, BindingResult result,
                                      RedirectAttributes redirectAttributes){
        if (result.hasErrors())
            return "user_profile_page.html";

        AppUser appUser = new AppUser();
        appUser.setUserName(userProfileForm.getUsername());
        appUser.setEmail(userProfileForm.getEmail());
        appUser.setPhoneNumber(userProfileForm.getPhone());

        System.out.println(userProfileForm.getUsername());
        System.out.println(userProfileForm.getEmail());

        int updateResult = appUserService.updateUserInfo(userProfileForm.getUsername(), userProfileForm.getPhone(), userProfileForm.getEmail());

        if (updateResult == 1) {
            redirectAttributes.addFlashAttribute("message", "Update successfully.");
            return "redirect:/user/profile";
        }

        redirectAttributes.addFlashAttribute("message", "Opps! Something was wrong.");
        return "redirect:/user/profile";
    }


}
