package com.vclinic.virtual_clinic_booking_system.repository;

import com.vclinic.virtual_clinic_booking_system.model.user.AppUser;
import com.vclinic.virtual_clinic_booking_system.model.user.enums.AppUserAppointmentType;
import com.vclinic.virtual_clinic_booking_system.model.user.enums.AppUserRole;
import com.vclinic.virtual_clinic_booking_system.model.user.AppointmentType;
import com.vclinic.virtual_clinic_booking_system.model.user.Appointments;
import com.vclinic.virtual_clinic_booking_system.model.user.UserRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class AppointmentRepoTest {

    private final AppointmentRepo underTest;

    @Autowired
    public AppointmentRepoTest(AppointmentRepo underTest) {
        this.underTest = underTest;
    }

    @Test
    void itShouldCheckAppointmentsInGivenDate() throws ParseException {
        //given
        AppUser appUser = new AppUser(
                "Arkar Min Thant",
                "arkarminthant572@gmail.com",
                "password",
                new UserRole(AppUserRole.USER.toString())
        );

        String sDate="2022-08-06";
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(sDate);

        Appointments appointments = new Appointments(
                "+6587667157",
                date,
                LocalTime.of(8, 0, 0),
                "Fever",
                "Fever has started two days ago",
                new AppointmentType(AppUserAppointmentType.PHYSICAL.toString()),
                appUser
        );

        // when
        List<Appointments> expected = underTest.getAppointmentsByDate(date);


        // then
        assertThat(expected.get(0).getAppUser().getUsername())
                .isEqualTo(appointments.getAppUser().getUsername());
    }
}