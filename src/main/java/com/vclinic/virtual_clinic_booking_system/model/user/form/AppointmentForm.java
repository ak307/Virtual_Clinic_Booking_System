package com.vclinic.virtual_clinic_booking_system.model.user.form;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;


@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
public class AppointmentForm {
    @NotBlank
    public String fullName;

    @NotBlank
    public String phoneNumber;

    @NotBlank
    @Email(message = "Not a valid Email Address")
    public String email;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    public Date date;

    @NotBlank
    public String time;

    public String purpose;

    public String description;

    public AppointmentForm(String fullName, String phoneNumber, String email, Date date, String purpose, String description) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.date = date;
        this.purpose = purpose;
        this.description = description;
    }


    public AppointmentForm(String fullName, String phoneNumber, String email, Date date, String description) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.date = date;
        this.description = description;
    }
}
