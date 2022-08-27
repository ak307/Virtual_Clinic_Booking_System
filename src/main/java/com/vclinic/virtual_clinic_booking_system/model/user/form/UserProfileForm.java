package com.vclinic.virtual_clinic_booking_system.model.user.form;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.awt.geom.Area;

@ToString
@EqualsAndHashCode
public class UserProfileForm {

    private Long id;
    @NotBlank
    private String username;

    @NotBlank
    @Email(message = "Not a valid Email Address")
    private String email;

    @NotNull
    private String phone;


    public UserProfileForm() {
    }


    public UserProfileForm(String username, String email, String phone) {
        this.username = username;
        this.email = email;
        this.phone = phone;
    }

    public UserProfileForm(Long id, String username, String email, String phone) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


}
