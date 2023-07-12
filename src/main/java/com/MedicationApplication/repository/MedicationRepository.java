package com.MedicationApplication.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MedicationApplication.entity.Medication;
import org.springframework.data.jpa.repository.Query;


public interface MedicationRepository extends JpaRepository<Medication, Long> {
    // Add custom query methods if required

    boolean existsByName(String name);

    List<Medication> findByIdAndStartDateBeforeAndEndDateAfter(Long id, LocalDate startDate, LocalDate endDate);

    default List<Medication> findByIdStartDateBeforeAndEndDateAfter(Long id, LocalDate completionDate) {
        return findByIdAndStartDateBeforeAndEndDateAfter(id,completionDate, completionDate);
    }
}

