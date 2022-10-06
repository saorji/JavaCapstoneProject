package com.samuelhrm.dtos.response;

import com.samuelhrm.entities.Diagnosis;
import com.samuelhrm.entities.Doctor;
import com.samuelhrm.entities.HrmPatient;
import com.samuelhrm.entities.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppointmentMiniResponse {

    private Long id;

    private String title;

    private String description;

    private Diagnosis diagnosis;


    private Status status;




    private LocalDateTime creationDate;


    private LocalDateTime updateDate;








}
