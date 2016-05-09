package database;

import java.util.Date;


public class Patient {

    private int PatientID;
    private String FirstName;
    private String SurName;
    private Date DoB;
    private String Gender;
    private Boolean Active;

    //Constructor
    public Patient() {

    }

    public Patient(String FirstName, String SurName, Date DoB, String Gender, Boolean Active) {
        this.FirstName = FirstName;
        this.SurName = SurName;
        this.DoB = DoB;
        this.Gender = Gender;
        this.Active = Active;
    }


    public int getPatientID() {
        return PatientID;
    }

    public void setPatientID(int patientID) {
        PatientID = patientID;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getSurName() {
        return SurName;
    }

    public void setSurName(String surName) {
        SurName = surName;
    }

    public Date getDoB() {
        return DoB;
    }

    public void setDoB(Date doB) {
        DoB = doB;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public Boolean getActive() {
        return Active;
    }

    public void setActive(Boolean active) {
        Active = active;
    }
}
