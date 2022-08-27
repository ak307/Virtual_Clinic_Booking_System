package com.vclinic.virtual_clinic_booking_system.model.user;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class UserRole {

    @Id
    @SequenceGenerator(
            name = "user_role_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_role_sequence"
    )
    private int roleId;
    private String roleName;

    public UserRole(String roleName) {
        this.roleName = roleName;
    }
}
