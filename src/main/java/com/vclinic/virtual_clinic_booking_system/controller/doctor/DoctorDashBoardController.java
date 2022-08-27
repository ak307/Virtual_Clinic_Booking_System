package com.vclinic.virtual_clinic_booking_system.controller.doctor;


import com.vclinic.virtual_clinic_booking_system.model.user.AppUser;
import com.vclinic.virtual_clinic_booking_system.model.user.Appointments;
import com.vclinic.virtual_clinic_booking_system.model.user.form.DoctorAppointmentsTableForm;
import com.vclinic.virtual_clinic_booking_system.service.doctor.DoctorDashboardService;
import com.vclinic.virtual_clinic_booking_system.service.utils.UserIdentifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/doctor")
public class DoctorDashBoardController {
    private final UserIdentifier userIdentifier;
    private final DoctorDashboardService doctorDashboardService;

    @Autowired
    public DoctorDashBoardController(UserIdentifier userIdentifier, DoctorDashboardService doctorDashboardService) {
        this.userIdentifier = userIdentifier;
        this.doctorDashboardService = doctorDashboardService;
    }

    @GetMapping("/dashboard")
    public ModelAndView showDoctorDashboard(){
        ModelAndView mav = new ModelAndView("doctorDashboard.html");

//        if (!userIdentifier.redirect().equals("noLogin")) {
//            return userIdentifier.redirect();
//        }

        List<Appointments> appointmentsList = doctorDashboardService.getAllAppointment();
        List<AppUser> allAppUserList = doctorDashboardService.getAllAppUser();

        List<AppUser> patientList =
                allAppUserList.stream().filter(appUser ->
                                appUser.getUserRole().getRoleName().equals("USER"))
                        .collect(Collectors.toList());


        List<DoctorAppointmentsTableForm> doctorAppointmentsTableFormList = formatAppointments();


        mav.addObject("docName", doctorAppointmentsTableFormList.get(0).getDoctor());
        mav.addObject("totalUserCount", patientList.size());

        mav.addObject("totalAppointmentsCount", appointmentsList.stream()
                .filter(appointments ->
                appointments
                        .getAppUser()
                        .getUserRole()
                        .getRoleName()
                        .equals("USER"))
                        .count());

        mav.addObject("appointments", doctorAppointmentsTableFormList);


        System.out.println("Patient name: " + appointmentsList.get(0).getAppUser().getUserName());


        return mav;
    }


    private List<DoctorAppointmentsTableForm> formatAppointments(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        List<Appointments> appointments = doctorDashboardService.getAllAppointment();
        List<DoctorAppointmentsTableForm> doctorAppointmentsTableFormArrayList = new ArrayList<>();

        System.out.println("COUNT" + appointments.size());


        for (Appointments appointment : appointments){
            if (appointment.getAppUser().getUserRole().getRoleName().equals("USER")){
                Long id = appointment.getAppointmentId() == null ? 0L : appointment.getAppointmentId();
                String patientName = appointment.getAppUser().getUsername() == null ? "is null" : appointment.getAppUser().getUserName();
                String status = appointment.getAppointmentType().getAppointmentTypeName() == null ? "is null" : appointment.getAppointmentType().getAppointmentTypeName();
                String date = appointment.getDate().toString() == null ? "is null" : appointment.getDate().toString();
                String formattedDate = date.substring(0, date.length() - 10) ;
                String time = appointment.getTime().toString();
                String phone = appointment.getPhoneNumber() == null ? "is null" : appointment.getPhoneNumber();
                String doctor = authentication.getName() == null ? "is null" : authentication.getName();
                String link = (appointment.getVideoAppointmentLink() == null) ? "-" : appointment.getVideoAppointmentLink();
                String desc = (appointment.getDescription().equals("") ? "-" : appointment.getDescription());

                doctorAppointmentsTableFormArrayList.add(
                        new DoctorAppointmentsTableForm(
                                id,
                                patientName,
                                status,
                                formattedDate,
                                time,
                                phone,
                                "Dr." + doctor,
                                link,
                                desc
                        ));
            }
        }



        return doctorAppointmentsTableFormArrayList;
    }


}
