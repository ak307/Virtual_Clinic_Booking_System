package com.vclinic.virtual_clinic_booking_system.model.user.form;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class LoginRequestForm  {
    private String email;
    private String password;

    public LoginRequestForm() {
    }

    public LoginRequestForm(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
