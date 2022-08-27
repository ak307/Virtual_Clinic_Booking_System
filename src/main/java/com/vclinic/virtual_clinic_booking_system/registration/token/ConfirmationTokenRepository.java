package com.vclinic.virtual_clinic_booking_system.registration.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {

    Optional<ConfirmationToken> findByToken(String token);


    @Query (
            nativeQuery = true,
            value = "SELECT ct.confirmed_at FROM confirmation_token ct JOIN app_user u USING(app_user_id) WHERE u.app_user_id = ?1"
    )
    String findConfirmAtByAppUserID(Long userID);



    @Query (
            nativeQuery = true,
            value = "SELECT ct.token FROM confirmation_token ct JOIN app_user u USING(app_user_id) WHERE u.app_user_id = ?1"
    )
    String findTokenByAppUserID(Long userID);


    @Query (
            nativeQuery = true,
            value = "SELECT ct.confirmed_at FROM confirmation_token ct JOIN app_user u USING(app_user_id) WHERE u.user_name = ?1"
    )
    String findConfirmedAt(String username);





    @Transactional
    @Modifying
    @Query(
            nativeQuery = true,
            value = "UPDATE confirmation_token SET confirmed_at = NOW() WHERE token = ?1"
    )
    void confirmTokenAt(String token);
}
