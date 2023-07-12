package com.MedicationApplication.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "medication_note")
public class MedicationNote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "note")
    private String note;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medication_id")
    private Medication medication;

   
    // Constructor, getters, and setters
    // ...

    
}

