package com.vclinic.virtual_clinic_booking_system.model.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentType {
    @Id
    @SequenceGenerator(
            name = "appointment_type_sequence",
            sequenceName = "appointment_type_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "appointment_type_sequence"
    )
    private int appointmentTypeId;
    private String appointmentTypeName;


    public AppointmentType(String appointmentTypeName) {
        this.appointmentTypeName = appointmentTypeName;
    }
}
