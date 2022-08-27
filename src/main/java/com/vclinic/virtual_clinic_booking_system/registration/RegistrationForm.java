package com.vclinic.virtual_clinic_booking_system.registration;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ToString
@EqualsAndHashCode
public class RegistrationForm {
    @NotBlank
    private String userName;
    @NotBlank
    @Email(message = "Not a valid Email Address")
    private String email;
    @NotBlank
    @Length(min = 6, max = 15)
    private String password;

    @NotBlank
    @Length(min = 6, max = 15)
    @NotNull(message = "Password must be the same")
    private String confirmPassword;

    public RegistrationForm() {

    }


    public RegistrationForm(String userName, String email,
                            String password, String confirmPassword) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    private void checkPassword() {
        if(!this.password.equals(confirmPassword)){
            this.confirmPassword = null;
        }
    }
}
