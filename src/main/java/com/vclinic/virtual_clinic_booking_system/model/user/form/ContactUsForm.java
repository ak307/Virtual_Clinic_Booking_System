package com.vclinic.virtual_clinic_booking_system.model.user.form;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class ContactUsForm {
    @NotBlank
    private String name;

    @NotBlank
    @Email(message = "Not a valid Email Address")
    private String email;
    @NotBlank
    private String message;


    public ContactUsForm(String name, String email, String message) {
        this.name = name;
        this.email = email;
        this.message = message;
    }

    public ContactUsForm() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
