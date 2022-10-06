package com.samuelhrm.controllers;


import com.samuelhrm.dtos.request.CreateMedicalRecordtRequest;
import com.samuelhrm.dtos.response.MessageResponse;
import com.samuelhrm.entities.MedicalRecord;
import com.samuelhrm.services.MedicalRecordService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController
@RequestMapping("/api/medicalrecordservice")
public class MedicalRecordController {
    private MedicalRecordService medicalRecordSrvice;

    @PostMapping("/create")
    public ResponseEntity<?> createMedicalRecord(@RequestBody CreateMedicalRecordtRequest request) throws Exception {
       MedicalRecord record = medicalRecordSrvice.createrecord(request.getUserId());
       MessageResponse response = new MessageResponse("savedrecord Successfully","ok", request.getUserId());
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }



}