package com.vclinic.virtual_clinic_booking_system.repository;

import com.vclinic.virtual_clinic_booking_system.model.user.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByEmail(String email);


    @Transactional
    @Query(
            nativeQuery = true,
            value = "SELECT * FROM app_user WHERE user_name = ?1"
    )
    Optional<AppUser> findAppUserByUserName(String username);


    @Transactional
    @Modifying
    @Query (
            nativeQuery = true,
            value = "UPDATE app_user u SET u.user_name = ?1, u.phone_number = ?2 WHERE u.email = ?3"
    )
    int updateUserInfo(String username, String phone, String email);


    @Transactional
    @Modifying
    @Query(
            value = "UPDATE app_user SET enabled = true WHERE email = ?1",
            nativeQuery = true
    )
    void enableAppUser(String email);



    @Transactional
    @Modifying
    @Query(
            value = "UPDATE app_user SET locked = false WHERE email = ?1",
            nativeQuery = true
    )
    void disableAccountLockedToFalse(String email);
}
