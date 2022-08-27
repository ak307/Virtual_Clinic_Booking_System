package com.vclinic.virtual_clinic_booking_system.repository;

import com.vclinic.virtual_clinic_booking_system.model.user.Appointments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointments, Long> {

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM appointments a WHERE a.date = ?1"
    )
    List<Appointments> getAppointmentsByDate(Date date);

}
