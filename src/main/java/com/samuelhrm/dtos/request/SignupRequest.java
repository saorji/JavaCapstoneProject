package com.samuelhrm.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String signupusername;

    @NotBlank
    @Size(max = 50)
    @Email
    private String signupemail;



    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    private String firstName;
    private String lastName;

}
