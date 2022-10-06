package com.samuelhrm.security;

import com.samuelhrm.dtos.response.MessageResponse;
import com.samuelhrm.dtos.response.UserInfoResponse;
import com.samuelhrm.entities.*;
import com.samuelhrm.dtos.request.LoginRequest;
import com.samuelhrm.dtos.request.SignupRequest;
import com.samuelhrm.exceptions.ApplicationException;
import com.samuelhrm.repository.DoctorRepository;
import com.samuelhrm.repository.RoleRepository;
import com.samuelhrm.repository.HrmPatientRepository;
import com.samuelhrm.security.jwt.JwtUtils;
import com.samuelhrm.security.services.UserDetailsImpl;
import com.samuelhrm.services.HrmPatientService;
import com.samuelhrm.services.MedicalRecordService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthService {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    HrmPatientRepository hrmPatientRepository;
    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    MedicalRecordService medicalRecordService;

    @Autowired
    HrmPatientService hrmPatientService;


    private ModelMapper modelMapper;

    public UserDetailsImpl longInUser(LoginRequest loginRequest) {


        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        UserDetailsImpl userDetails = getUserDetailinContext(authentication);

//        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);


        return userDetails;


    }

    private UserDetailsImpl getUserDetailinContext(Authentication authentication) {

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return userDetails;
    }

    public UserInfoResponse getUserLoginInfo(UserDetailsImpl user) {
        UserDetailsImpl userDetails = user;

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        HrmPatient user2 = hrmPatientService.findById(userDetails.getId());

        userDetails.setMessage("ok");
       return UserInfoResponse.builder().id(userDetails.getId())
               .username(userDetails.getUsername())
               .email(userDetails.getEmail())
               .message(userDetails.getMessage())

                               .roles(roles)
               .build();


    }

//    public String registerNewuserPatient(SignupRequest signUpRequest) {
//        if (hrmPatientRepository.existsByUsername(signUpRequest.getUsername())) {
//            return "Error: Username is already taken!";
//        }
//
//        if (hrmPatientRepository.existsByEmail(signUpRequest.getEmail())) {
//            return "Error: Email is already in use!";
//        }
//
//        // Create new hrmPatient's account
//       HrmPatient hrmPatient = new HrmPatient(signUpRequest.getUsername(),
//                signUpRequest.getEmail(),
//                encoder.encode(signUpRequest.getPassword()),
//                signUpRequest.getFirstName(),
//                signUpRequest.getLastName());
//
//        Set<String> strRoles = new HashSet<>();
//        strRoles.add("pat");
//        Set<Role> roles = new HashSet<>();
//
//        if (strRoles == null) {
//            Role userRole = roleRepository.findByName(ERole.ROLE_PATIENT)
//                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//            roles.add(userRole);
//        } else {
//            strRoles.forEach(role -> {
//                switch (role) {
//                    case "admin":
//                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        roles.add(adminRole);
//
//                        break;
//                    case "doc":
//                        Role modRole = roleRepository.findByName(ERole.ROLE_DOCTOR)
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        roles.add(modRole);
//
//                        break;
//                    case "pat":
//                        Role userRole = roleRepository.findByName(ERole.ROLE_PATIENT)
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        roles.add(userRole);
//                }
//            });
//        }
//
//        hrmPatient.setRoles(roles);
//        hrmPatientRepository.save(hrmPatient);
//        return "HrmPatient registered successfully!";
//    }
public MessageResponse registerNewuserPatient(SignupRequest signUpRequest) throws ApplicationException {
    if (hrmPatientRepository.existsByUsername(signUpRequest.getSignupusername())) {
        throw new ApplicationException(String.format("username already taken"));


    }

    if (hrmPatientRepository.existsByEmail(signUpRequest.getSignupemail())) {
        throw new ApplicationException(String.format("email already taken"));
    }

    if (!signUpRequest.getSignupemail().matches("^(.+)@(.+)$")){
        throw new ApplicationException(String.format("wrong email address"));
    }

    // Create new hrmPatient's account
    HrmPatient hrmPatient = new HrmPatient(signUpRequest.getSignupusername(),
            signUpRequest.getSignupemail(),
            encoder.encode(signUpRequest.getPassword()),
            signUpRequest.getFirstName(),
            signUpRequest.getLastName());
    Set<Role> roles = new HashSet<>();
    Role userRole = roleRepository.findByName(ERole.ROLE_PATIENT)
            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
    roles.add(userRole);
    hrmPatient.setRoles(roles);
    HrmPatient user =hrmPatientRepository.save(hrmPatient);
//    Doctor doctor = new Doctor();
//    modelMapper.map(user,doctor);
//    doctorRepository.save(doctor);
   MedicalRecord usermedRecord = medicalRecordService.createrecord(user.getId());
    Long id =usermedRecord.getId();
    user.setMedicalRecord(usermedRecord);
    HrmPatient savedUserWithRecord =hrmPatientRepository.save(user);
    return new MessageResponse("An Health Record has be setup for you ","Patient Account setup successfully!");

}

    public MessageResponse registerNewuserDoctor(SignupRequest signUpRequest) throws ApplicationException {
        if (hrmPatientRepository.existsByUsername(signUpRequest.getSignupusername())) {
            throw new ApplicationException(String.format("username already taken"));


        }

        if (hrmPatientRepository.existsByEmail(signUpRequest.getSignupemail())) {
            throw new ApplicationException(String.format("email already taken"));
        }

        if (!signUpRequest.getSignupemail().matches("^(.+)@(.+)$")){
            throw new ApplicationException(String.format("wrong email address"));
        }


        HrmPatient hrmPatient = new HrmPatient(signUpRequest.getSignupusername(),
                signUpRequest.getSignupemail(),
                encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getFirstName(),
                signUpRequest.getLastName());
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName(ERole.ROLE_DOCTOR)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(userRole);
        hrmPatient.setRoles(roles);
        HrmPatient user =hrmPatientRepository.save(hrmPatient);
        Doctor doctor = new Doctor();
        modelMapper.map(user,doctor);
        doctorRepository.save(doctor) ;

        return new MessageResponse("An Doctor Admin Page now set up for you","Doctor Account setup successfully");
    }

}