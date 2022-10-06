package com.samuelhrm.repository;


import com.samuelhrm.entities.Appointment;
import com.samuelhrm.entities.HrmPatient;
import com.samuelhrm.entities.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long>
{
    Optional<Appointment> findByTitle(String title);

//    List<Appointment> getAllBy

    Optional<Appointment> findById(Long id);

    Boolean existsByTitle(String title);

//    @Query("SELECT Appointment FROM Appointment post WHERE Appointment.patient_id = :title")
//    List<Appointment> findByPatient_id(HrmPatient title);





//	@Query("SELECT post FROM Appointment appointment ORDER BY appointment.pkPost DESC")
//	List<Appointment> findAll();
//
//	@Query("SELECT post FROM Appointment post WHERE appointment.author = :author ORDER BY post.pkPost DESC")
//	List<Appointment> findAllAuthorPosts( author);
}