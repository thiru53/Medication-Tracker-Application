package com.MedicationApplication.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.MedicationApplication.CompletionDateWrapper;
import com.MedicationApplication.entity.Medication;
import com.MedicationApplication.entity.MedicationCompletion;
import com.MedicationApplication.entity.MedicationNote;

import com.MedicationApplication.repository.MedicationCompletionRepository;
import com.MedicationApplication.repository.MedicationNoteRepository;
import com.MedicationApplication.repository.MedicationRepository;



import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
public class UserController {
   
    @Autowired
    private MedicationRepository medicationRepository;
    
    @Autowired
    private MedicationNoteRepository medicationNoteRepository;
    
    @Autowired
    private MedicationCompletionRepository medicationCompletionRepository;
    
    
   
    @GetMapping("/medication/list")
    public ResponseEntity<?> getMedicationList() {
    	Map<String, String> response = new HashMap<>();
    	List<Medication> medications = medicationRepository.findAll();
    	if(!medications.isEmpty()) {
            return ResponseEntity.ok(medications);
    	}
        response.put("status", "failure");
        response.put("message", "No medication list found.");

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/medication/add")
    public ResponseEntity<Map<String, String>> addMedication(@RequestBody Medication medication ) {
        Map<String, String> response = new HashMap<>();
        try{
            preValidation(medication);
            medicationRepository.save(medication);
            response.put("status", "success");
            response.put("message", "Medication added successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch(Exception e) {
            response.put("status", "failure");
            response.put("message", e.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/medication/update/{id}")
    public ResponseEntity<Map<String, String>> updateMedication(@PathVariable("id") Long medicationId) {
        Map<String, String> response = new HashMap<>();
        response.put("status", "success/failure");
        response.put("message", "Logic for medication updation");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/medication/delete/{id}")
    public ResponseEntity<Map<String, String>> deleteMedication(@PathVariable("id") Long medicationId) {
        Map<String, String> response = new HashMap<>();
        response.put("status", "success/failure");
        response.put("message", "Logic for deletion of medicine");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/medication/notes/add/{medicationId}")
    public ResponseEntity<Map<String, String>> addMedicationNote(@PathVariable("medicationId") Long medicationId) {
        Map<String, String> response = new HashMap<>();
        response.put("status", "success/failure");
        response.put("message", "Logic for adding medication note");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/medication/notes/update/{medicationId}")
    public ResponseEntity<Map<String, String>> updateMedicationNote(
            @PathVariable("medicationId") Long medicationId
    ) {
        Map<String, String> response = new HashMap<>();
        response.put("status", "success/failure");
        response.put("message", "Logic for updating medication note");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/medication/notes/{medicationId}")
    public ResponseEntity<Map<String, String>> getMedicationNotes(@PathVariable("medicationId") Long medicationId) {
        Map<String, String> response = new HashMap<>();
        response.put("status", "success/failure");
        response.put("message", "Placeholder message for getting medication notes.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/medication/notes/delete/{medicationId}")
    public ResponseEntity<Map<String, String>> deleteMedicationNoteByMedicationId(@PathVariable("medicationId") Long medicationId) {
        Map<String, String> response = new HashMap<>();
        response.put("status", "success/failure");
        response.put("message", "Logic for deleting medication note");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/medication/completion/{medicationId}")
    public ResponseEntity<Map<String, String>> recordMedicationCompletion(
            @PathVariable("success/failure") Long medicationId
            
    ) {
        Map<String, String> response = new HashMap<>();
        response.put("status", "success/failure");
        response.put("message", "Logic for recording medication completion");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/medication/completion/{medicationId}")
    public ResponseEntity<Map<String, String>> getMedicationCompletionDates(@PathVariable("medicationId") Long medicationId) {
        Map<String, String> response = new HashMap<>();
        response.put("status", "success/failure");
        response.put("message", "Logic for displaying medication dates with completion status is missing.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void preValidation(Medication medication) {
        if(medicationRepository.existsByName(medication.getName())){
            throw new EntityExistsException(String.format("Medication with given name : %s is already exists", medication.getName()));
        }
    }

}
