package com.samuelhrm.repository;

import java.util.List;
import java.util.Optional;

import com.samuelhrm.entities.HrmPatient;
import com.samuelhrm.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HrmPatientRepository extends JpaRepository<HrmPatient, Long> {
  Optional<HrmPatient> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);

  List<HrmPatient> findByRoles(Role role);


}
