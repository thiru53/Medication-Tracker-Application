package com.MedicationApplication;

import java.time.LocalDate;

public class CompletionDateWrapper {
    private LocalDate completionDate;

    public CompletionDateWrapper() {
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(LocalDate completionDate) {
        this.completionDate = completionDate;
    }
}
