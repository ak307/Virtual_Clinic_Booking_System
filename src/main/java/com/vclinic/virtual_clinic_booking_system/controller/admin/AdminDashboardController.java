package com.vclinic.virtual_clinic_booking_system.controller.admin;

import com.vclinic.virtual_clinic_booking_system.model.user.AppUser;
import com.vclinic.virtual_clinic_booking_system.service.user.AppUserService;
import com.vclinic.virtual_clinic_booking_system.model.user.Appointments;
import com.vclinic.virtual_clinic_booking_system.model.user.form.UserProfileForm;
import com.vclinic.virtual_clinic_booking_system.service.doctor.DoctorDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminDashboardController {

    private final DoctorDashboardService doctorDashboardService;
    private final AppUserService appUserService;


    @Autowired
    public AdminDashboardController(DoctorDashboardService doctorDashboardService, AppUserService appUserService) {
        this.doctorDashboardService = doctorDashboardService;
        this.appUserService = appUserService;
    }

    @GetMapping("/dashboard")
    public ModelAndView showAdminDashboard(){
        ModelAndView mav = new ModelAndView("adminDashboard.html");

        List<Appointments> appointmentsList = doctorDashboardService.getAllAppointment();
        List<AppUser> allAppUserList = doctorDashboardService.getAllAppUser();

        List<AppUser> patientList =
                allAppUserList.stream().filter(appUser ->
                                appUser.getUserRole().getRoleName().equals("USER"))
                        .collect(Collectors.toList());



        mav.addObject("adminName", getAdminName());
        mav.addObject("totalUserCount", patientList.size());
        mav.addObject("totalAppointmentsCount", appointmentsList.size());
        mav.addObject("doctors", getDoctorList());
        mav.addObject("users", getUserList());

        return mav;
    }


    private String getAdminName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }


    @GetMapping("/updateDoctor")
    public String updateDoctor(@RequestParam Long doctorId, Model model){
        UserProfileForm doctor = getDoctorList().stream()
                .filter(user -> Objects.equals(user.getId(), doctorId))
                .collect(Collectors.toList()).get(0);


        model.addAttribute("user", doctor);
        return "update_user_or_doctor.html";
    }


    @GetMapping("/updateUser")
    public String updateUser(@RequestParam Long userId, Model model){
        UserProfileForm userForm = getUserList().stream()
                .filter(user -> Objects.equals(user.getId(), userId))
                .collect(Collectors.toList()).get(0);

        model.addAttribute("user", userForm);
        return "update_user_or_doctor.html";
    }


    @PostMapping("updateUser")
    public String updateUser(@ModelAttribute @Valid UserProfileForm userProfileForm, BindingResult result){
        if (result.hasErrors()){
            return "update_user_or_doctor.html";
        }

        AppUser appUser = appUserService.getUserById(userProfileForm.getId());
        appUser.setUserName(userProfileForm.getUsername());
        appUser.setPhoneNumber(userProfileForm.getPhone());

        appUserService.updateUser(appUser);

        return "redirect:/admin/dashboard";
    }




    @GetMapping("/deleteUser")
    public String deleteEmployee(@RequestParam Long userId){
        appUserService.deleteById(userId);
        return "redirect:/admin/dashboard";
    }



    private List<UserProfileForm> getDoctorList(){
        List<UserProfileForm> userProfileFormList = new ArrayList<>();
        List<AppUser> allAppUserList = doctorDashboardService.getAllAppUser();

        List<AppUser> doctorList = allAppUserList.stream().filter( appUser ->
                        appUser.getUserRole().getRoleName().equals("DOCTOR"))
                .collect(Collectors.toList());


        for (AppUser doc : doctorList) {
            userProfileFormList.add(
                    new UserProfileForm(
                            doc.getAppUserId(),
                            doc.getUsername(),
                            doc.getEmail(),
                            (doc.getPhoneNumber() == null || doc.getPhoneNumber().equals("")) ? "-" : doc.getPhoneNumber()

                    )
            );
        }

        return userProfileFormList;
    }


    private List<UserProfileForm> getUserList(){
        List<UserProfileForm> userProfileFormList = new ArrayList<>();
        List<AppUser> allAppUserList = doctorDashboardService.getAllAppUser();

        List<AppUser> userList = allAppUserList.stream().filter( appUser ->
                        appUser.getUserRole().getRoleName().equals("USER"))
                .collect(Collectors.toList());


        for (AppUser user : userList) {
            userProfileFormList.add(
                    new UserProfileForm(
                            user.getAppUserId(),
                            user.getUsername(),
                            user.getEmail(),
                            (user.getPhoneNumber() == null || user.getPhoneNumber().equals("")) ? "-" : user.getPhoneNumber()
                    )
            );
        }

        return userProfileFormList;
    }



}
