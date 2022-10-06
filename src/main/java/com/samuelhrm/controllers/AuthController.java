package com.samuelhrm.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.samuelhrm.exceptions.ApplicationException;
import com.samuelhrm.security.AuthService;
import com.samuelhrm.repository.RoleRepository;
import com.samuelhrm.security.jwt.JwtUtils;
import com.samuelhrm.security.services.UserDetailsImpl;
import com.samuelhrm.services.HrmPatientService;
import com.samuelhrm.services.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.samuelhrm.dtos.request.LoginRequest;
import com.samuelhrm.dtos.request.SignupRequest;
import com.samuelhrm.dtos.response.UserInfoResponse;
import com.samuelhrm.dtos.response.MessageResponse;
import com.samuelhrm.repository.HrmPatientRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  HrmPatientRepository hrmPatientRepository;

  @Autowired
  HrmPatientService hrmPatientService;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;
  @Autowired
  AuthService authService;

  @Autowired
  JwtUtils jwtUtils;
  @Autowired
  MedicalRecordService medicalRecordService;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    UserDetailsImpl userDetails = authService.longInUser(loginRequest);
    ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);
    List<String> roles = userDetails.getAuthorities().stream()
            .map(item -> item.getAuthority())
            .collect(Collectors.toList());

    userDetails.setMessage("ok");
    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
            .body(new UserInfoResponse(userDetails.getId(),
                    userDetails.getUsername(),
                    userDetails.getEmail(),

                    userDetails.getMessage(),

                    roles));
  }

  @PostMapping("/patient/signup")
  public ResponseEntity<?> registerPatient(@RequestBody SignupRequest signUpRequest) {

      try{
          MessageResponse response = authService.registerNewuserPatient(signUpRequest);
          return new ResponseEntity<>(response, HttpStatus.OK);
      }
      catch (ApplicationException e){
          return new ResponseEntity<> (new MessageResponse("wrong email Address"),HttpStatus.BAD_REQUEST);
      }
  }

  @PostMapping("/doctor/signup")
  public ResponseEntity<?> registerDoctor(@RequestBody SignupRequest signUpRequest) {


 try{
      MessageResponse response = authService.registerNewuserDoctor(signUpRequest);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (ApplicationException e){
            return new ResponseEntity<> (new MessageResponse("wrong email Address"),HttpStatus.BAD_REQUEST);
        }

  }

  @GetMapping("/user")
  public ResponseEntity<?> home(@AuthenticationPrincipal UserDetailsImpl user) {

        UserInfoResponse response = authService.getUserLoginInfo(user);
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE).body(response);

  }

    @PostMapping("/signout")
  public ResponseEntity<?> logoutUser() {
    ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
    MessageResponse response = new MessageResponse("You've been signed out!","logout");
    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
            .body(response);
  }
}
