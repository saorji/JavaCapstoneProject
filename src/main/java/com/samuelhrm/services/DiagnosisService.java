package com.samuelhrm.services;

import com.samuelhrm.dtos.request.CreateDiagnosisRequest;
import com.samuelhrm.dtos.request.DiagnosisRequest;
import com.samuelhrm.dtos.response.AppointmentMiniResponse;
import com.samuelhrm.dtos.response.MessageResponse;
import com.samuelhrm.dtos.response.UserInfoResponse;
import com.samuelhrm.entities.Diagnosis;


public interface DiagnosisService {


    public AppointmentMiniResponse createDiagnosisResult(CreateDiagnosisRequest request, Long id);
    public UserInfoResponse getDiagnosis(Long id);

}
