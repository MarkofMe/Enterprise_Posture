package database;

import android.graphics.Bitmap;
import android.media.Image;

import java.util.Date;

public class Appointment {
    private int AppointmentID; // Primary Key
    private int PatientID; // Foreign key
    private int AppointmentNo;
    private Date AppointmentDate;
    private Bitmap PatientImage;
    private String Diagnostic;

    //Constructors
    public Appointment() {

    }

    public Appointment(int patientID, int appointmentNo, Date appointmentDate, Bitmap patientImage, String diagnostic) {
        this.PatientID = patientID;
        this.AppointmentNo = appointmentNo;
        this.AppointmentDate = appointmentDate;
        this.PatientImage = patientImage;
        this.Diagnostic = diagnostic;
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

    public Bitmap getPatientImage() {
        return PatientImage;
    }

    public void setPatientImage(Bitmap patientImage) {
        PatientImage = patientImage;
    }

    public String getDiagnostic() {
        return Diagnostic;
    }

    public void setDiagnostic(String diagnostic) {
        Diagnostic = diagnostic;
    }
}


