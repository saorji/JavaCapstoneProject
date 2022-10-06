package com.samuelhrm.services;

import com.samuelhrm.dtos.response.UserInfoResponse;
import com.samuelhrm.entities.HrmPatient;


import java.util.List;
import java.util.Optional;

public interface HrmPatientService {

    public List<HrmPatient> findAllPatient();
    public List<UserInfoResponse> findAll();


    public HrmPatient findById(Long id);



    public void delete(Long id) ;



    public void save( HrmPatient patient);


    public Optional<HrmPatient> findByUsername(String un);

    public UserInfoResponse findUser(HrmPatient patient);



//    public List<HrmPatient> findByKeyword(String keyword);

}
