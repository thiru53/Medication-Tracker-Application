package com.MedicationApplication.entity;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;


@Data
@Entity
@Table(name="medication")
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String schedule;

    @NonNull
    private String time;

    @NonNull
    private int numberOfDoses;

    @NonNull
    private LocalDate startDate;

    @NonNull
    private LocalDate endDate;
    
    @OneToMany(mappedBy = "medication", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MedicationNote> notes = new ArrayList<>();
    
    
    @OneToMany(mappedBy = "medication", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MedicationCompletion> completions;

   

    //getter , setter. constructor
    
}