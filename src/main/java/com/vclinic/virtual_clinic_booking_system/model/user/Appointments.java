package com.vclinic.virtual_clinic_booking_system.model.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.time.LocalTime;
import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Appointments {

    @Id
    @SequenceGenerator(
            name = "appointment_sequence",
            sequenceName = "appointment_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "appointment_sequence"
    )
    private Long appointmentId;
    private String phoneNumber;

    @Column(name = "date", nullable = false, updatable = false)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date date;

    @Column(name = "time", nullable = false, updatable = false)
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime time;

    @Column(nullable = false)
    private String purposeOfAppointment;

    private String description;

    private String videoAppointmentLink;


    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(
            name = "appointment_type_id",
            referencedColumnName = "appointmentTypeId"
    )
    private AppointmentType appointmentType;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "app_user_id",
            referencedColumnName = "appUserId"
    )
    private AppUser appUser;


    public Appointments(String phoneNumber, Date date, LocalTime time,
                        String purposeOfAppointment, String description,
                        AppointmentType appointmentType, AppUser appUser) {
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.time = time;
        this.purposeOfAppointment = purposeOfAppointment;
        this.description = description;
        this.appointmentType = appointmentType;
        this.appUser = appUser;
    }


    public Appointments(String phoneNumber, Date date, LocalTime time,
                        String purposeOfAppointment, String description,
                        String videoAppointmentLink, AppointmentType appointmentType,
                        AppUser appUser) {
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.time = time;
        this.purposeOfAppointment = purposeOfAppointment;
        this.description = description;
        this.videoAppointmentLink = videoAppointmentLink;
        this.appointmentType = appointmentType;
        this.appUser = appUser;
    }
}
