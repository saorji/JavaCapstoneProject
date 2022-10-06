package com.samuelhrm.dtos.request;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.samuelhrm.entities.Diagnosis;
import com.samuelhrm.entities.Doctor;
import com.samuelhrm.entities.HrmPatient;
import com.samuelhrm.entities.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAppointmentRequest {

    private String description;

    private String title;



}
