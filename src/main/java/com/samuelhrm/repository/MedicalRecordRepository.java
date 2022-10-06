package com.samuelhrm.repository;

import com.samuelhrm.entities.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord,Long> {
    @Override
    Optional<MedicalRecord> findById(Long aLong);
    Optional<MedicalRecord> findMedicalRecordByPatientId(Long aLong);
}
