package com.vclinic.virtual_clinic_booking_system.registration;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class EmailValidator implements Predicate<String> {


    @Override
    public boolean test(String s) {
//        to validate email here(for now assume true)

        return true;
    }
}
