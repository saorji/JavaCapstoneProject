package com.samuelhrm.services;

import com.samuelhrm.entities.Appointment;
import com.samuelhrm.entities.Doctor;
import com.samuelhrm.repository.DoctorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DoctorServiceImpl implements DoctorServices{
    private AppointmentService appointmentService;

    private DoctorRepository doctorRepository;


    @Override
    public List<Appointment> appointmentListForDoctor(){
        List<Appointment> appointments = appointmentService.getAllAppointment();
        return appointments;
    }

    @Override
    public Doctor getDoctor(Long id) {
        Doctor founddoctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error: Doctor is not found."));
        return  founddoctor;
    }

    @Override
    public Doctor saveDoctor(Doctor doctor) {
        Doctor savedDoctor = doctorRepository.save(doctor);

        return  savedDoctor;
    }

}
