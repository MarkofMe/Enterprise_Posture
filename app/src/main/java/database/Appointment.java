package database;

import android.media.Image;

import java.util.Date;

public class Appointment {
    private int AppointmentID; // Primary Key
    private int PatientID; // Foreign key
    private int AppointmentNo;
    private Date AppointmentDate;
    private Image PatientImage;
    private String Diagnostic;

    //Constructors
    public Appointment() {

    }

    public Appointment(int appointmentID, int patientID, int appointmentNo, Date appointmentDate, Image patientImage, String diagnostic) {
        this.AppointmentID = appointmentID;
        this.PatientID = patientID;
        this.AppointmentNo = appointmentNo;
        this.AppointmentDate = appointmentDate;
        this.PatientImage = patientImage;
        this.Diagnostic = diagnostic;
    }

    public int getAppointmentID() {
        return AppointmentID;
    }

    public void setAppointmentID(int appointmentID) {
        AppointmentID = appointmentID;
    }

    public int getPatientID() {
        return PatientID;
    }

    public void setPatientID(int patientID) {
        PatientID = patientID;
    }

    public int getAppointmentNo() {
        return AppointmentNo;
    }

    public void setAppointmentNo(int appointmentNo) {
        AppointmentNo = appointmentNo;
    }

    public Date getAppointmentDate() {
        return AppointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        AppointmentDate = appointmentDate;
    }

    public Image getPatientImage() {
        return PatientImage;
    }

    public void setPatientImage(Image patientImage) {
        PatientImage = patientImage;
    }

    public String getDiagnostic() {
        return Diagnostic;
    }

    public void setDiagnostic(String diagnostic) {
        Diagnostic = diagnostic;
    }
}


