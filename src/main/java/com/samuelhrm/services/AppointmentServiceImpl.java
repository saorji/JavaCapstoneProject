package com.samuelhrm.services;


import com.samuelhrm.dtos.request.ChangeAppointmentStatus;
import com.samuelhrm.dtos.request.CreateAppointmentRequest;
import com.samuelhrm.dtos.response.AppointmentMiniResponse;
import com.samuelhrm.dtos.response.MessageResponse;
import com.samuelhrm.entities.Appointment;
import com.samuelhrm.entities.HrmPatient;
import com.samuelhrm.entities.MedicalRecord;
import com.samuelhrm.entities.Status;
import com.samuelhrm.repository.AppointmentRepository;
import com.samuelhrm.security.AuthService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.*;


@AllArgsConstructor
@Service
public class AppointmentServiceImpl implements AppointmentService {
    private ModelMapper modelMapper;

    private AppointmentRepository appointmentRepository;

    private HrmPatientService patientService;

    private MedicalRecordService medicalRecordService;
    private AuthService authService;



    @Transactional
    @Override
    public MessageResponse create(CreateAppointmentRequest createAppointmentRequest,Long userId){

        HrmPatient hrmPatient = patientService.findById(userId);

        MedicalRecord patientRecord = medicalRecordService.findById(hrmPatient.getMedicalRecord().getId());



        List <Appointment> userMedicalAppList = patientRecord.getAppointment();
        Appointment patientApp = Appointment.builder()
                .description(createAppointmentRequest.getDescription())
                .title(createAppointmentRequest.getTitle())


                .creationDate(LocalDateTime.now())
                .status(Status.PENDING)
                .build();
        Appointment savedAppointment = appointmentRepository.save(patientApp);

        userMedicalAppList.add(savedAppointment);
        patientRecord.setAppointment(userMedicalAppList);


        MedicalRecord savedMedicalRecord = medicalRecordService.save(patientRecord);



        return	MessageResponse.builder()
                .message("Appointment created successfully")
                .successfully("ok")
                .savedEntityintId(savedMedicalRecord.getAppointment().size())
                .build();
    }


    @Override

    public List<AppointmentMiniResponse> findUserAllAppointment(Long userid)	{

        HrmPatient patient1 = patientService.findById(userid);

        MedicalRecord patientRecond = medicalRecordService.findById(patient1.getMedicalRecord().getId());

        List <Appointment> userMedicalAppList =patientRecond.getAppointment();
        List<AppointmentMiniResponse> dtoList = Arrays.asList( modelMapper.map(userMedicalAppList, AppointmentMiniResponse[].class));

        return dtoList;

    }

    @Override
    public List<Appointment> getAllAppointment(){
        List<Appointment> dtoList = appointmentRepository.findAll();
        return dtoList;
    }

    @Override
    public Appointment getAppointmentById(Long id){

        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error: Appointment is not found."));

        return  appointment;


    }
    @Override
    public Appointment SaveAppointment(Appointment appointment) {

        appointmentRepository.save(appointment);

        return  appointment;


    }

    @Override
    public MessageResponse changeAppointmentStatus(ChangeAppointmentStatus changeStatus) {
        Appointment change = appointmentRepository.findById(changeStatus.getAppointmentid())
                .orElseThrow(() -> new RuntimeException("Error: Appointment is not found."));
        change.setStatus(Status.APPROVED);
        Appointment savedChange = appointmentRepository.save(change);

        return MessageResponse.builder().message("Appointment Status Approved")
                .build();
    }

    @Override
    public MessageResponse changeAppointmentStatusBack(ChangeAppointmentStatus changeStatus) {
        Appointment change = appointmentRepository.findById(changeStatus.getAppointmentid())
                .orElseThrow(() -> new RuntimeException("Error: Appointment is not found."));
        change.setStatus(Status.DISAPPROVED);
        Appointment savedChange = appointmentRepository.save(change);

        return MessageResponse.builder().message("Appointment Status Disapproved")
                .build();
    }

}