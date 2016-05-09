package database;

import java.util.Date;


public class Patient {

    private int PatientID;
    private String FirstName;
    private String SurName;
    private Date DoB;
    private String Gender;
    private Integer Active;

    //Constructor
    public Patient() {

    }

    public Patient(String FirstName, String SurName, Date DoB, String Gender, Integer Active) {
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

    public Integer getActive() {
        return Active;
    }

    public void setActive(Integer active) {
        Active = active;
    }
}
