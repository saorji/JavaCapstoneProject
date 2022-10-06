package com.samuelhrm.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiagnosisMiniResponse {
    private Long id;
    private String treatmentType;
    private String drugDescription;

}
