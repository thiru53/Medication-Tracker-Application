# Medication-Tracker-Application
The purpose of this project is to enhance your programming and logical skills by developing the backend API logic for a medication tracking system known as MedTrackr. MedTrackr aims to provide personalized medication tracking for improved health management

# Step by Step Process of Live Project Execution

# Task 1 : Manage and Access your Medications
The objective of this task is to enhance the MedTrackr system by implementing functionalities that allow users to effectively manage and access their medications.

Access medication lis endpoint:
The "/medication/list" GET endpoint will be developed to enable users to view their medication list .

The JSON response should be in the following format:

    [
        {
            "id": 12,
            "name": "Medication Name1",
            "schedule": "once a day",
            "time": "8:00 AM",
            "numberOfDoses": 3,
            "startDate": "2023-07-01",
            "endDate": "2023-07-31"
        }
    ]

Adding medication endpoint:
The "/medication/add" endpoint is a POST endpoint where users can input relevant medication information. The required fields include the medication's name, schedule, time, total number of doses, start date, and end date of medication intake.

The endpoint should adhere to the following request JSON format:
    
    {
        "name": "Medication Name",
        "schedule": "twice",
        "time": "8:00 AM,8:00PM",
        "numberOfDoses": 3,
        "startDate": "2023-07-01",
        "endDate": "2023-07-31"
    }

The JSON response for the above should be in the following format:

    {
        "message": "Medication added successfully.",
        "status": "success"
    }

# Task 2 : Enhancing Data Editing(Update and Deletion)
The aim of this task is to enhance the MedTrackr system by implementing functionalities that allow users to update and delete medication details. Let's break down the requirements step by step:

Update Medication Endpoint:
The "/medication/update/{id}" endpoint is a POST endpoint that enables users to modify the information of a specific medication identified by its ID.
The endpoint should adhere to the following request JSON format:

    {
      "name": "Updated Medication Name",
      "schedule": "Updated Schedule",
      "time": "Updated Time",
      "numberOfDoses": 4,
      "startDate": "2023-08-01",
      "endDate": "2023-08-31"
    }

The JSON response for the above should be in the following format:

    {
        "message": "Medication updated successfully.",
        "status": "success"
    }

The entered data will undergo validation to ensure completeness and accuracy. It will be checked if the medication ID exists in the database.Special attention will be given to the uniqueness of the medication name to avoid duplicate entries,schedule which should be 'once' for once a day , twice for twice a day and thrice for thrice a day and also shedule entered should be matching the times added if the schedule is once then time list should have only one time etc. If any validation errors occur, appropriate error messages will be displayed.
Upon successful updating, the modified medication information will be reflected in the database.
The system will provide a response indicating the success or failure of the update process.

Medication Deletion Endpoint:
The "/medication/delete/{id}" POST endpint is responsible for deleting a specific medication from the database, identified by its ID.
The JSON response for the above should be in the following format:

    {
        "message": "Medication deleted successfully.",
        "status": "success"
    }

Users will be directed to this endpoint to delete the details of a specific medication.
The system will validate if the medication ID exists in the database. If the medication is found, it will be permanently deleted from the database.
The system will respond with a message indicating the success or failure of the deletion process.
Appropriate error messages will be displayed if any errors occur during the deletion process.

By successfully completing this task, you will showcase your ability to implement update and delete functionalities, enabling users to seamlessly edit medication data, perform input validation, and effectively manage medication records within the MedTrackr system.

The entered data in '/medication/add'will undergo thorough validation to ensure accuracy and completeness. Special attention will be given to the uniqueness of the medication name to avoid duplicate entries,schedule which should be 'once' for once a day , twice for twice a day and thrice for thrice a day and also shedule entered should be matching the times added if the schedule is once then time list should have only one time etc.
Upon successful validation, the medication information will be securely stored in the "medication" table in the database.
system will respond with a message indicating the success or failure of the insertion process.

After successfully adding a medication, users can access their medication details through the "/medication/list" endpoint.

This endpoint will display the user's medication list, providing an easy-to-navigate and comprehend overview of their medications.

In case of any errors during the insertion process, appropriate error messages will be displayed to guide the user.

By successfully completing this task, you will demonstrate your proficiency in implementing functionalities for medication management, enabling users to conveniently view, add, and access their medication details. 

# Task 3 : Record Medication Intake
The objective of this task is to enable users to record their medication intake for a specific medication using the '/medication/completion/{id}' endpoint. Let's break down the requirements step by step:

Medication Intake Recording Endpoint:
The '/medication/completion/{id}' endpoint is a POST endpoint where users can provide the date for which they want to record the medication intake.
The endpoint should adhere to the following request JSON format:

    {
        "completionDate": "2023-07-07"
    }

The JSON response for the above should be in the following format:
    
    {
        "message": "Medication completion recorded successfully.",
        "status": "success"
    }

The system will validate the input date to ensure its accuracy and relevance. The date will be checked against the medication's start and end dates to ensure it falls within the specified range and also that if the date is future date then setting completion status is not allowed.
Upon successful validation, the system will update the medication intake information in the database to indicate that the medication was taken on the provided date.
A response will be sent to the user indicating the success or failure of the medication intake recording process.

Medication Completion Status Retrieval Endpoint:
After successfully recording the medication intake, users can utilize the '/medication/completion/{id}' GET endpoint.
The JSON response for the above should be in the following format:
    
    {
        "2023-07-01": "completed",
        "2023-07-02": "",
        "2023-07-03": "",
        "2023-07-04": "",
        "2023-07-05": "",
        "2023-07-06": "",
        "2023-07-07": "completed",
        "2023-07-08": "",
        "2023-07-09": "",
        "2023-07-10": ""
    }

This endpoint will display all the dates within the range of the medication's start and end dates.
For each date, the system will indicate the completion status of medication intake. It will specify whether the medication intake for a particular day has been completed or not.
The response from this endpoint will provide users with an overview of their medication completion progress, allowing them to track their adherence to the prescribed medication schedule.

By successfully accomplishing this task, you will demonstrate your ability to implement functionality for tracking medication completion and updating medication intake information accurately. 

# Task 4 : Medication Notes Management
The objective of this task is to develop endpoints that allow users to create, update, view, and delete notes related to specific medicines for future reference. Let's break down the requirements step by step:

Add Medication Note Endpoint:
The '/medication/notes/add/{medicationId}' endpoint is a POST endpoint that enables users to add notes for a particular medicine using its ID.
The endpoint should adhere to the following request JSON format:


    {
        "note": "This is a medication note"
    }

The JSON response for the above should be in the following format:

    {
        "message": "Medication note added successfully.",
        "status": "success"
    }

Upon successful creation of the note, the system will store the note in the "medication_note" table in the database.
The system will provide a response indicating the success of the process.
In case of any errors or failures, appropriate error messages will be displayed to guide the user.

View Medication Notes Endpoint:
After successfully creating a note, users can access the '/medication/notes/{medicationId}'  GET endpoint.
    {
        "note": "This is a medication note",
        "status": "success"
    }

This endpoint will display  the notes associated with the specific medicine identified by its ID.
Users can easily access and view all the notes related to the medicine for future reference.

Update Medication Note Endpoint:
Users can utilize the "/medication/notes/update/{medicationId}" POST endpoint to update existing notes for the medicine.
The endpoint should adhere to the following request JSON format:


    {
        "note": "This is the updated medication note"
    }

The JSON response for the above should be in the following format:

    {
        "message": "Medication note updated successfully.",
        "status": "success"
    }

The system will provide a response indicating the success or failure of the update process.

Delete Medication Note Endpoint:
Users can use the "/medication/notes/delete/{medicationId}" POST endpoint to delete notes associated with the medicine.
The JSON response for the above should be in the following format:

    {
        "message": "Medication note deleted successfully.",
        "status": "success"
    }

The system will permanently delete the specified note from the database.
The system will provide a response indicating the success or failure of the deletion process.

By successfully completing this task, you will demonstrate your ability to implement functional endpoints for managing medication notes. Additionally, you will showcase your expertise in presenting information clearly and efficiently to the user, enabling them to easily create ,update, access and delete notes for their medicines.

