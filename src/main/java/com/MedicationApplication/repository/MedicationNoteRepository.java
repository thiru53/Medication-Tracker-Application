package com.MedicationApplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MedicationApplication.entity.Medication;
import com.MedicationApplication.entity.MedicationNote;

@Repository
public interface MedicationNoteRepository extends JpaRepository<MedicationNote, Long> {

	 // Add custom query methods if required

    List<MedicationNote> getNoteByMedicationId(Long medicationId);

}
