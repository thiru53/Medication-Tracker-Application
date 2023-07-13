package com.MedicationApplication.controller;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.MedicationApplication.entity.Medication;
import com.MedicationApplication.entity.MedicationCompletion;
import com.MedicationApplication.entity.MedicationNote;

import com.MedicationApplication.repository.MedicationCompletionRepository;
import com.MedicationApplication.repository.MedicationNoteRepository;
import com.MedicationApplication.repository.MedicationRepository;

@RestController
public class UserController {
   
    @Autowired
    private MedicationRepository medicationRepository;
    
    @Autowired
    private MedicationNoteRepository medicationNoteRepository;
    
    @Autowired
    private MedicationCompletionRepository medicationCompletionRepository;
    
    private static final Map<String, Integer> schedules = new HashMap<>();
    static {
        schedules.put("once", 1);
        schedules.put("twice", 2);
        schedules.put("thrice", 3);
    }
   
    @GetMapping("/medication/list")
    public ResponseEntity<?> getMedicationList() {
    	Map<String, String> response = new HashMap<>();
    	List<Medication> medications = medicationRepository.findAll();
    	if(!medications.isEmpty()) {
            return new ResponseEntity<>(medications, HttpStatus.OK);
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
    public ResponseEntity<Map<String, String>> updateMedication(@PathVariable("id") Long medicationId, @RequestBody Medication medication) {
        Map<String, String> response = new HashMap<>();
        try {
            preValidation(medication);
            Medication medicationFromDB = medicationRepository.findById(medicationId).orElseThrow(() -> new EntityNotFoundException("Medication not found for the id : " + medicationId));
            medicationFromDB.setName(medication.getName());
            medicationFromDB.setSchedule(medication.getSchedule());
            medicationFromDB.setTime(medication.getTime());
            medicationFromDB.setNumberOfDoses(medication.getNumberOfDoses());
            medicationFromDB.setStartDate(medication.getStartDate());
            medicationFromDB.setEndDate(medication.getEndDate());

            medicationRepository.save(medicationFromDB);
            response.put("status", "success");
            response.put("message", "Medication updated successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("status", "failure");
            response.put("message", e.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/medication/delete/{id}")
    public ResponseEntity<Map<String, String>> deleteMedication(@PathVariable("id") Long medicationId) {
        Map<String, String> response = new HashMap<>();
        try {
            medicationRepository.deleteById(medicationId);
            response.put("status", "success");
            response.put("message", "Medication deleted successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("status", "failure");
            response.put("message", e.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/medication/notes/add/{medicationId}")
    public ResponseEntity<Map<String, String>> addMedicationNote(@PathVariable("medicationId") Long medicationId, @RequestBody MedicationNote medicationNote) {
        Map<String, String> response = new HashMap<>();
        try {
            Medication medicationFromDB = medicationRepository.findById(medicationId).orElseThrow(() -> new EntityNotFoundException("Medication not found for the id : " + medicationId));
            medicationNote.setMedication(medicationFromDB);
            medicationNoteRepository.save(medicationNote);
            response.put("status", "success");
            response.put("message", "Medication note added successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e){
            response.put("status", "failure");
            response.put("message", e.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
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
    public ResponseEntity<Map<String, Object>> getMedicationNotes(@PathVariable("medicationId") Long medicationId) {
        Map<String, Object> response = new HashMap<>();
        try {
            Medication medicationFromDB = medicationRepository.findById(medicationId).orElseThrow(() -> new EntityNotFoundException("Medication not found for the id : " + medicationId));
            List<MedicationNote> medicationNotes = medicationNoteRepository.getNoteByMedicationId(medicationId);
            response.put("status", "success");
            response.put("note", medicationNotes.stream().map(MedicationNote::getNote).collect(Collectors.toSet()));
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            response.put("status", "failure");
            response.put("message", "No note content found for the medication ID");
        }

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/medication/notes/delete/{medicationId}")
    public ResponseEntity<Map<String, String>> deleteMedicationNoteByMedicationId(@PathVariable("medicationId") Long medicationId) {
        Map<String, String> response = new HashMap<>();
        try{
            List<MedicationNote> medicationNotes = medicationNoteRepository.getNoteByMedicationId(medicationId);
            medicationNoteRepository.deleteAll(medicationNotes);
            response.put("status", "success");
            response.put("message", "Medication note deleted successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            response.put("status", "failure");
            response.put("message", e.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/medication/completion/{medicationId}")
    public ResponseEntity<Map<String, String>> recordMedicationCompletion(
            @PathVariable("medicationId") Long medicationId,
            @RequestBody MedicationCompletion medicationCompletion
            
    ) {
        Map<String, String> response = new HashMap<>();
        try {
            Medication medicationFromDB = medicationRepository.findById(medicationId).orElseThrow(() -> new EntityNotFoundException("Medication not found for the id : " + medicationId));
            boolean completionStatus = isBetweenDates(medicationCompletion.getCompletionDate(), medicationFromDB.getStartDate(), medicationFromDB.getEndDate());

            MedicationCompletion medicationCompletion1 = medicationCompletionRepository.findByCompletionDate(medicationCompletion.getCompletionDate()).orElse(medicationCompletion);
            medicationCompletion1.setMedication(medicationFromDB);
            medicationCompletion1.setCompletionStatus(completionStatus);
            medicationCompletionRepository.save(medicationCompletion1);

            response.put("status", "success");
            response.put("message", "Medication completion recorded successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e){
            response.put("status", "failure");
            response.put("message", e.getMessage());
        }

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/medication/completion/{medicationId}")
    public ResponseEntity<?> getMedicationCompletionDates(@PathVariable("medicationId") Long medicationId) {
        Map<String, String> response = new HashMap<>();
        try {
            List<MedicationCompletion> medicationCompletions = medicationCompletionRepository.getMedicationCompletionByMedicationId(medicationId);
            if(!medicationCompletions.isEmpty()){
                response = medicationCompletions.stream().collect(Collectors.toMap(mediCom -> mediCom.getCompletionDate().toString(), mediCom -> (mediCom.isCompletionStatus() ? "completed":""), (v1, v2) -> v1+v2));
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            response.put("status", "failure");
            response.put("message", "No Medication completion recorded found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }catch (Exception e){
            response.put("status", "failure");
            response.put("message", e.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private void preValidation(Medication medication) {
        if(medicationRepository.existsByName(medication.getName())){
            throw new EntityExistsException("Medication name already exists");
        }
        String[] scheduleTimes = medication.getTime().split(",");
        String scheduleType = medication.getSchedule();
        if(scheduleTimes.length != schedules.get(scheduleType)){
            throw new EntityExistsException(String.format("Invalid number of doses. Expected %d doses for '%s a day' schedule.", schedules.get(scheduleType), scheduleType));
        }
    }

    private boolean isBetweenDates(LocalDate givenDate, LocalDate startDate, LocalDate endDate) {
        return givenDate.isEqual(startDate) || givenDate.isEqual(endDate) || (givenDate.isAfter(startDate) && givenDate.isBefore(endDate));
    }
}
