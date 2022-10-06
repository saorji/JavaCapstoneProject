package com.samuelhrm.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.samuelhrm.entities.HrmPatient;
import com.samuelhrm.repository.HrmPatientRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  HrmPatientRepository hrmPatientRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    HrmPatient hrmPatient = hrmPatientRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("HrmPatient Not Found with username: " + username));

    return UserDetailsImpl.build(hrmPatient);
  }

}
