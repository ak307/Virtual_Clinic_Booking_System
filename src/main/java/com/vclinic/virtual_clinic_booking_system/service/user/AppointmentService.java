package com.vclinic.virtual_clinic_booking_system.service.user;

import com.vclinic.virtual_clinic_booking_system.model.user.Appointments;
import com.vclinic.virtual_clinic_booking_system.repository.AppointmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AppointmentService {
    private final AppointmentRepo appointmentRepo;

    @Autowired
    public AppointmentService(AppointmentRepo appointmentRepo) {
        this.appointmentRepo = appointmentRepo;
    }

    public void saveAppointment(Appointments appointments){
        appointmentRepo.save(appointments);
    }


    public List<Appointments> getAppointmentsByDate(Date date){
        return appointmentRepo.getAppointmentsByDate(date);
    }


    public List<Appointments> getAllAppointments() {
        return appointmentRepo.findAll();
    }


    public List<String> getAvailableTimeSlots(Date chooseDate, String appointmentType) {
        List<String> availableSlotList = new ArrayList<>();
        List<String> selectedTimeSlots = new ArrayList<>();
        String[] timeSlots = {
                "08:00 AM", "09:00 AM", "10:00 AM",
                "11:00 AM", "01:00 PM", "02:00 PM",
                "03:00 PM", "04:00 PM"
        };


        List<Appointments> appointments = getAppointmentsByDate(chooseDate);
        for(Appointments appointment : appointments){
            if (appointment.getAppointmentType().getAppointmentTypeName().equals(appointmentType))
                selectedTimeSlots.add(dateFormatConverter24To12Hours(appointment.getTime().toString() + ":00"));
        }

        for (String slot : timeSlots){
            if (!selectedTimeSlots.contains(slot)){
                availableSlotList.add(slot);
            }
        }

        return availableSlotList;
    }


    public String dateFormatConverter12To24Hour(String dateStr){
        switch (dateStr){
            case "08:00 AM":
                return "08:00:00";
            case  "09:00 AM":
                return "09:00:00";
            case "10:00 AM":
                return "10:00:00";
            case  "11:00 AM":
                return "11:00:00";
            case "01:00 PM":
                return "13:00:00";
            case  "02:00 PM":
                return "14:00:00";
            case "03:00 PM":
                return "15:00:00";
            case  "04:00 PM":
                return "16:00:00";
        }
        return null;
    }

    private String dateFormatConverter24To12Hours(String dateStr){
        switch (dateStr){
            case "08:00:00":
                return "08:00 AM";
            case  "09:00:00":
                return "09:00 AM";
            case "10:00:00":
                return "10:00 AM";
            case  "11:00:00":
                return "11:00 AM";
            case "13:00:00":
                return "01:00 PM";
            case  "14:00:00":
                return "02:00 PM";
            case "15:00:00":
                return "03:00 PM";
            case  "16:00:00":
                return "04:00 PM";
        }
        return null;
    }








}
