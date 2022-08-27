package com.vclinic.virtual_clinic_booking_system.appuser;

import com.vclinic.virtual_clinic_booking_system.model.user.AppUser;
import com.vclinic.virtual_clinic_booking_system.model.user.enums.AppUserRole;
import com.vclinic.virtual_clinic_booking_system.model.user.UserRole;
import com.vclinic.virtual_clinic_booking_system.repository.AppUserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class AppUserRepositoryTest {
    private AppUserRepository appUserRepository;

    @Autowired
    public AppUserRepositoryTest(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }


    @Test
    void itShouldFindUserByEmail() {
        //given
        String email = "testUser@gmail.com";

        AppUser appUser = new AppUser(
                "Test User",
                "testUser@gmail.com",
                "password",
                new UserRole(AppUserRole.USER.toString())
        );
        appUserRepository.save(appUser);

        //when
        Optional<AppUser> user = appUserRepository.findByEmail(email);
        Boolean exists = user.isPresent();

        //then
        assertThat(exists).isTrue();
    }

    @Test
    void enableAppUser() {

    }

    @Test
    void disableAccountLockedToFalse() {
    }
}