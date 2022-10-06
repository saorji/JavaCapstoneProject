package com.samuelhrm.services;

import com.samuelhrm.dtos.request.GetAppointRequest;
import com.samuelhrm.entities.HrmPatient;
import com.samuelhrm.entities.MedicalRecord;
import com.samuelhrm.repository.MedicalRecordRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MedicalRecordServiceImpl implements MedicalRecordService{

    private MedicalRecordRepository medicalRecordRepository;

    private HrmPatientService patientService;



    @Override
    public MedicalRecord findById(Long id) {

        MedicalRecord md = medicalRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("medical record not findById."));
        return md;


    }
    @Override
    public MedicalRecord findMedicalRecordByPatientId(GetAppointRequest userId) {
        MedicalRecord md = medicalRecordRepository.findMedicalRecordByPatientId(userId.getUserId())
                .orElseThrow(() -> new RuntimeException("medical record not findById."));
        return md;
    }

    @Override
    public MedicalRecord createrecord(Long userId){
        HrmPatient patient1 =patientService.findById(userId);
        MedicalRecord hrmRecord = new MedicalRecord();
        MedicalRecord savedrecord =save(hrmRecord);
        patient1.setMedicalRecord(savedrecord);

        patientService.save(patient1);

        return  savedrecord;
    }

    @Override
    public MedicalRecord save(MedicalRecord  md) {
        return medicalRecordRepository.save(md);
    }

}
