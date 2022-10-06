package com.samuelhrm.services;

import com.samuelhrm.dtos.request.GetAppointRequest;
import com.samuelhrm.entities.HrmPatient;
import com.samuelhrm.entities.MedicalRecord;
import com.samuelhrm.repository.MedicalRecordRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


public interface MedicalRecordService {



    public MedicalRecord findById(Long id);

    public MedicalRecord findMedicalRecordByPatientId(GetAppointRequest userId);
    public MedicalRecord createrecord(Long userId);
    public MedicalRecord save(MedicalRecord  md) ;
}
