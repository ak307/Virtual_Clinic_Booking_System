package com.vclinic.virtual_clinic_booking_system.service.doctor;

import com.vclinic.virtual_clinic_booking_system.model.user.AppUser;
import com.vclinic.virtual_clinic_booking_system.service.user.AppUserService;
import com.vclinic.virtual_clinic_booking_system.model.user.Appointments;
import com.vclinic.virtual_clinic_booking_system.service.user.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorDashboardService {

    private AppUserService appUserService;
    private AppointmentService appointmentService;


    @Autowired
    public DoctorDashboardService(AppUserService appUserService, AppointmentService appointmentService) {
        this.appUserService = appUserService;
        this.appointmentService = appointmentService;
    }


    public List<Appointments> getAllAppointment(){
        return appointmentService.getAllAppointments();
    }


    public List<AppUser> getAllAppUser(){
        return appUserService.getAllUser();
    }


}
