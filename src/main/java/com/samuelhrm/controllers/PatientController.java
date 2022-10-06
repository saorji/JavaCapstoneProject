package com.samuelhrm.controllers;

import com.samuelhrm.dtos.response.UserInfoResponse;
import com.samuelhrm.entities.HrmPatient;
import com.samuelhrm.services.HrmPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/patient")
public class PatientController {

    @Autowired
    private HrmPatientService hrmPatientService;
    @GetMapping("/users")
    public ResponseEntity<?> getUser() {
        List<UserInfoResponse> userList = hrmPatientService.findAll();

        return ResponseEntity.ok(userList);
    }
}
