package com.samuelhrm.services;

import com.samuelhrm.dtos.response.UserInfoResponse;
import com.samuelhrm.entities.ERole;
import com.samuelhrm.entities.HrmPatient;
import com.samuelhrm.entities.Role;
import com.samuelhrm.repository.HrmPatientRepository;
import com.samuelhrm.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
    public class HrmPatientServiceImpl implements HrmPatientService {


        private RoleRepository roleRepository;

        private ModelMapper modelMapper;

        private HrmPatientRepository hrmPatientRepository;


       @Override

        public List<UserInfoResponse> findAll(){


               List<HrmPatient> userList =  hrmPatientRepository.findAll();

           List<UserInfoResponse> dtoList =  Arrays.asList(modelMapper.map(userList, UserInfoResponse[].class));


               return dtoList;
           }


        public List<HrmPatient> findAllPatient(){
           Role role = roleRepository.findByName(ERole.ROLE_DOCTOR)
                   .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        return hrmPatientRepository.findByRoles(role);


        }

        @Override
        public HrmPatient findById(Long id) {
            return hrmPatientRepository.findById(id).orElse(null);
        }

       @Override
        public void delete(Long id) {
            hrmPatientRepository.deleteById(id);
        }

       @Override
        public void save(HrmPatient patient) {
            hrmPatientRepository.save(patient);
        }

        @Override
        public Optional<HrmPatient> findByUsername(String un) {
            return hrmPatientRepository.findByUsername(un);
        }


        @Override
        public UserInfoResponse findUser(HrmPatient patient) {
        HrmPatient foundUser = hrmPatientRepository.findById(patient.getId()).orElse(null);
        UserInfoResponse responseDto= new UserInfoResponse();

        modelMapper.map(responseDto, foundUser);

        return responseDto;
        }




    }


