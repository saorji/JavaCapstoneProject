package com.samuelhrm.controllers;

import com.samuelhrm.dtos.response.UserInfoResponse;
import com.samuelhrm.entities.Appointment;
import com.samuelhrm.entities.HrmPatient;
import com.samuelhrm.services.AppointmentService;
import com.samuelhrm.services.DoctorServices;
import com.samuelhrm.services.HrmPatientService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/doctor")
@AllArgsConstructor
public class DoctorController {


    private DoctorServices doctorServices;
 ;
    @GetMapping("/appointments")
    public ResponseEntity<?> getAppointment() {
        List<Appointment> appointments = doctorServices.appointmentListForDoctor();

        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }
}
