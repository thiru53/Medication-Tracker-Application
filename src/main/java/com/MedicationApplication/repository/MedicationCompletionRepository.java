package com.MedicationApplication.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.MedicationApplication.entity.MedicationNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MedicationApplication.entity.Medication;
import com.MedicationApplication.entity.MedicationCompletion;

@Repository
public interface MedicationCompletionRepository extends JpaRepository<MedicationCompletion, Long> {
	 // Add custom query methods if required
     List<MedicationCompletion> getMedicationCompletionByMedicationId(Long medicationId);
}

