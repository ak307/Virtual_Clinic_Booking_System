package com.vclinic.virtual_clinic_booking_system.model.user.form;

import lombok.*;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DoctorAppointmentsTableForm {
    private Long id;
    private String patientName;
    private String status;
    private String date;
    private String time;
    private String phone;
    private String doctor;
    private String link;
    private String desc;
}
