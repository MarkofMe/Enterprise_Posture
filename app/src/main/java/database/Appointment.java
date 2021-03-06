package database;

import android.graphics.Bitmap;
import android.media.Image;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Appointment {
    private int AppointmentID; // Primary Key
    private int PatientID; // Foreign key
    private int AppointmentNo;
    private String AppointmentDate;
    private String Diagnostic;

    //Constructors
    public Appointment() {

    }

    public Appointment(int patientID, int appointmentNo, Date appointmentDate, String diagnostic) {
        this.PatientID = patientID;
        this.AppointmentNo = appointmentNo;
        this.AppointmentDate = setDate(appointmentDate);
        this.Diagnostic = diagnostic;
    }

    //Standardises the input date.
    private String setDate(Date d) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(d);
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

    public String getAppointmentDate() {
        return AppointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        AppointmentDate = setDate(appointmentDate);
    }

    public String getDiagnostic() {
        return Diagnostic;
    }

    public void setDiagnostic(String diagnostic) {
        Diagnostic = diagnostic;
    }
}


