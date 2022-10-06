package com.samuelhrm.dtos.response;

import com.samuelhrm.entities.MedicalRecord;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Builder
@Data
@AllArgsConstructor
public class UserInfoResponse {

    private Long id;
    private String username;
    private String email;
    private List<String> roles;

    private String message;
    private Long diagnosisid;
    private String diagnosistreatmentType;
    private String diagnosisdrugDescription;

    public UserInfoResponse () {
    }



    public UserInfoResponse(Long id, String username, String email, String message, List<String> roles, Long record) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
        this.message = message;



    }
    public UserInfoResponse(Long id, String username, String email, String message, List<String> roles) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
        this.message = message;



    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getRoles() {
        return roles;
    }
}
