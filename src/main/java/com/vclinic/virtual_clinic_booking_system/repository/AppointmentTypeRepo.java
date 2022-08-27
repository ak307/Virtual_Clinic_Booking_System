package com.vclinic.virtual_clinic_booking_system.repository;

import com.vclinic.virtual_clinic_booking_system.model.user.AppointmentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentTypeRepo extends JpaRepository<AppointmentType, Long> {

}
