package com.samuelhrm.controllers;

import com.samuelhrm.dtos.request.CreateDiagnosisRequest;
import com.samuelhrm.dtos.request.DiagnosisRequest;
import com.samuelhrm.dtos.response.AppointmentMiniResponse;
import com.samuelhrm.dtos.response.MessageResponse;
import com.samuelhrm.dtos.response.UserInfoResponse;
import com.samuelhrm.entities.Appointment;
import com.samuelhrm.entities.Diagnosis;
import com.samuelhrm.entities.HrmPatient;
import com.samuelhrm.security.services.UserDetailsImpl;
import com.samuelhrm.services.DiagnosisService;
import com.samuelhrm.services.HrmPatientService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/diagnosis")
@AllArgsConstructor
public class DiagnosisController {
private DiagnosisService diagnosisService;


    @PostMapping("/create")
    public ResponseEntity<?> createAnewResult(@AuthenticationPrincipal UserDetailsImpl user, @RequestBody CreateDiagnosisRequest request) {
        AppointmentMiniResponse editAppointment = diagnosisService.createDiagnosisResult(request, user.getId());
        return new ResponseEntity<>(editAppointment, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {

       UserInfoResponse response = diagnosisService.getDiagnosis(id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    }