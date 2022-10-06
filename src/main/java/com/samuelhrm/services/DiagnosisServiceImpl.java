package com.samuelhrm.services;

import com.samuelhrm.dtos.request.CreateDiagnosisRequest;
import com.samuelhrm.dtos.request.DiagnosisRequest;
import com.samuelhrm.dtos.response.AppointmentMiniResponse;
import com.samuelhrm.dtos.response.DiagnosisResponse;
import com.samuelhrm.dtos.response.MessageResponse;
import com.samuelhrm.dtos.response.UserInfoResponse;
import com.samuelhrm.entities.Appointment;
import com.samuelhrm.entities.Diagnosis;
import com.samuelhrm.entities.Doctor;
import com.samuelhrm.entities.Status;
import com.samuelhrm.repository.DiagnosisRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.tools.Diagnostic;

@Service
@AllArgsConstructor
public class DiagnosisServiceImpl implements DiagnosisService{

   private DiagnosisRepository diagnosisRepository;
   private AppointmentService appointmentService;

   private DoctorServices doctorServices;



      @Override
    public AppointmentMiniResponse createDiagnosisResult(CreateDiagnosisRequest request, Long id) {
         Appointment appointmentToAddDiagnosis = appointmentService.getAppointmentById(request.getAppointmentid());
         Diagnosis diagnostic = new Diagnosis();
         diagnostic.setTreatmentType(request.getTreatmentType());
         diagnostic.setDrugDescription(request.getDrugDescription());
         Diagnosis savedDiagnostic = diagnosisRepository.save(diagnostic);
          appointmentToAddDiagnosis.setDiagnosis(savedDiagnostic);
          appointmentToAddDiagnosis.setStatus(Status.COMPLETED);
          Appointment savedAppointment = appointmentService.SaveAppointment(appointmentToAddDiagnosis);
//          Doctor foundDoctor = doctorServices.getDoctor(id);
//          Doctor savedDoctor = doctorServices.saveDoctor(foundDoctor);


//          Appointment patientAppointment = appointmentService.getAppointmentById(request.getAppointmentid());



//          Appointment savedAppointment = appointmentService.SaveAppointment(patientAppointment);

         return    AppointmentMiniResponse.builder()
                    .diagnosis(savedAppointment.getDiagnosis())
                    .description(savedAppointment.getDescription())
                    .title(savedAppointment.getTitle())
                    .updateDate(savedAppointment.getUpdateDate())
                    .status(savedAppointment.getStatus())
                    .id(savedAppointment.getId())
                    .build();


    }

    @Override
    public UserInfoResponse getDiagnosis(Long id){
         Appointment foundAppoiment = appointmentService.getAppointmentById(id);


       Diagnosis foundDiagnosisFromDb = diagnosisRepository.findById(foundAppoiment.getDiagnosis().getId())
               .orElseThrow(() -> new RuntimeException("Error: Diagnosis is not found."));

        return  UserInfoResponse.builder().id(foundAppoiment.getId())
                .diagnosisdrugDescription(foundDiagnosisFromDb.getDrugDescription())
                .diagnosistreatmentType(foundDiagnosisFromDb.getTreatmentType())
                .diagnosisid(foundDiagnosisFromDb.getId())
                .build();


    }
}
