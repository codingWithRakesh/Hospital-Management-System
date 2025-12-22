package com.undefiend.hospitalManagement.repository;

import com.undefiend.hospitalManagement.entity.Patient;
import com.undefiend.hospitalManagement.dto.BloodGroupCountResponseEntity;
import com.undefiend.hospitalManagement.entity.type.BloodGroupType;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {

    Patient findByName(String name);

    List<Patient> findByBirthDateOrEmail(LocalDate birthDate, String email);

    List<Patient> findByBirthDateBetween(LocalDate start, LocalDate end);

    List<Patient> findByNameContainingOrderByIdDesc(String query);

    @Query("SELECT p FROM Patient p WHERE p.bloodGroupType = ?1")
    List<Patient> findByBloodGroup(@Param("bloodGroup")BloodGroupType bloodGroupType);

    @Query("SELECT p FROM Patient p WHERE p.birthDate > :birthDate")
    List<Patient> findByBornAfterDate(@Param("birthDate") LocalDate birthDate);

    @Query("SELECT p.bloodGroupType, Count(p) FROM Patient p GROUP BY p.bloodGroupType")
    List<Object[]> counteachBloodGroupType();

    //problem
    @Query("SELECT new com.undefiend.hospitalManagement.dto.BloodGroupCountResponseEntity(p.bloodGroupType," + " Count(p)) FROM Patient p GROUP BY p.bloodGroupType")
    List<BloodGroupCountResponseEntity> counteachBloodGroupTypeDto();

    //problem
    @Query(value = "SELECT * FROM patient", nativeQuery = true)
    Page<Patient> findAllPatients(Pageable pageable);


    @Transactional
    @Modifying
    @Query("UPDATE Patient p SET p.name = :name WHERE p.id = :id")
    int updateNameWithId(@Param("name") String name, @Param("id") Long id);
}
