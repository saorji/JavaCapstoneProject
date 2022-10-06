package com.samuelhrm.services;


import com.samuelhrm.entities.Appointment;
import com.samuelhrm.entities.Doctor;
import com.samuelhrm.repository.DoctorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;



public interface DoctorServices {



    public List<Appointment> appointmentListForDoctor();
    public Doctor getDoctor(Long id);

    public Doctor saveDoctor(Doctor doctor);
}
