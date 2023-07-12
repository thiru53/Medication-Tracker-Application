package com.MedicationApplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MedicationApplication.entity.Medication;


public interface MedicationRepository extends JpaRepository<Medication, Long> {
    // Add custom query methods if required

    boolean existsByName(String name);
	 
	
}

