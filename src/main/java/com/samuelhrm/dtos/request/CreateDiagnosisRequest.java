package com.samuelhrm.dtos.request;

import com.samuelhrm.entities.Treatment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateDiagnosisRequest {
    private String treatmentType;
    private String drugDescription;
    private Long  appointmentid;
}
