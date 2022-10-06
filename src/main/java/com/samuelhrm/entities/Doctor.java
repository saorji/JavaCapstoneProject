package com.samuelhrm.entities;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Doctor extends  HrmUser{

    private String Speciality;
    @OneToMany
    @JoinColumn(name = "appointment_id")
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    private List<Appointment> appointment;




    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "doctor_roles",
            joinColumns = @JoinColumn(name = "doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @ToString.Exclude
    private Set<Role> roles = new HashSet<>();

    public List<Appointment> getAppointmentAttended() {
        return appointment;
    }


    public void setAppointmentAttended(List<Appointment> appointmentAttended) {
        this.appointment = appointmentAttended;


    }

    public String getSpeciality() {
        return Speciality;
    }

    public void setSpeciality(String speciality) {
        Speciality = speciality;
    }
}
