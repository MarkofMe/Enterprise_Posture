package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

    private Patient patient = new Patient();
    private Appointment appointment = new Appointment(); //idk??

    //Database Name
    private static final String Database_Name = "Posture.db";

    //Patients table name
    private static final String Table_Patients = "Patients";

    //Appointments table name
    private static final String Table_Appointments = "Appointment";

    // Patients Table Columns names
    private static final String PT_ID = "_id";
    private static final String PT_FIRSTNAME = "FirstName";
    private static final String PT_SURNAME = "Surname";
    private static final String PT_DOB = "DoB";
    private static final String PT_GENDER = "Gender";

    //Appointments Table Columns names
    private static final String AP_ID = "_id";
    private static final String AP_PATIENTID = "PatientID";
    private static final String AP_APPOINTMENTNO = "AppointmentNo";
    private static final String AP_APPOINTMENTDATE = "AppointmentDate";
    private static final String AP_PATIENTIMAGE = "PatientImage";
    private static final String AP_DIAGNOSTIC = "Diagnostic";

    //Constructor
    public DatabaseHandler(Context context) {
        super(context, Database_Name, null, 1);
    }

    //Creates the database tables and columns
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PATIENTS_TABLE = "CREATE TABLE "
                + Table_Patients
                + "(" + PT_ID//Make sure that this isn't an issue
                + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PT_FIRSTNAME
                + " TEXT NOT NULL, "
                + PT_SURNAME
                + " TEXT NOT NULL, "
                + PT_DOB
                + " TEXT NOT NULL, "
                + PT_GENDER
                + " TEXT NOT NULL, " + ") ";

        String CREATE_APPOINTMENTS_TABLE = "CREATE TABLE "
                + Table_Appointments
                + "(" + AP_ID//Make sure that this isn't an issue
                + "INTEGER PRIMARY KEY AUTOINCREMENT, "
                + AP_PATIENTID
                + " TEXT NOT NULL , "
                + AP_APPOINTMENTNO
                + " TEXT NOT NULL , "
                + AP_APPOINTMENTDATE
                + " TEXT NOT NULL , "
                + AP_PATIENTIMAGE
                + " TEXT NOT NULL , "
                + AP_DIAGNOSTIC
                + " TEXT NOT NULL, "
                + " FOREIGN KEY (" + AP_PATIENTID + ") REFERENCES " + Table_Patients + "(" + PT_ID + "));";
        db.execSQL(CREATE_PATIENTS_TABLE);
        db.execSQL(CREATE_APPOINTMENTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldNum, int newNum) {
        //Drop older table if exists and create fresh
        db.execSQL("Drop table if exists " + Table_Patients);
        db.execSQL("DROP TABLE IF EXISTS " + Table_Appointments);
        onCreate(db);
    }

    // Adds data to the patients table
    public boolean insertDataPatients() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PT_FIRSTNAME, patient.getFirstName());
        values.put(PT_SURNAME, patient.getSurName());
        values.put(PT_DOB, String.valueOf(patient.getDoB())); // because its a date variable
        values.put(PT_GENDER, patient.getGender());
        long result = db.insert(Table_Patients, null, values);
        if (result == -1)// if the contents arent inserted db.insert returns -1, so this is a check for if the data is inserted
            return false;
        else
            return true;
    }

    // Adds data to the appointments table
    public boolean insertDataAppointments() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(AP_APPOINTMENTNO, appointment.getAppointmentNo());
        values.put(AP_APPOINTMENTDATE, String.valueOf(appointment.getAppointmentDate())); // because its a date variable
        values.put(AP_PATIENTIMAGE, String.valueOf(appointment.getPatientImage()));
        values.put(AP_DIAGNOSTIC, appointment.getDiagnostic());
        long result = db.insert(Table_Patients, null, values);
        if (result == -1)// if the contents arent inserted db.insert returns -1, so this is a check for if the data is inserted
            return false;
        else
            return true;
    }

    // Updates a field based on their ID
    public boolean updatePatients(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PT_ID, patient.getPatientID());
        values.put(PT_FIRSTNAME, patient.getFirstName());
        values.put(PT_SURNAME, patient.getSurName());
        values.put(PT_DOB, String.valueOf(patient.getDoB())); // because its a date variable
        values.put(PT_GENDER, patient.getGender());
        db.update(Table_Patients, values, "_id = ?", new String[]{id}); //queries by finding the field based on id
        return true;
    }

    // Updates a field based on their ID
    public boolean updateAppointments(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(AP_ID, appointment.getAppointmentID());
        values.put(AP_PATIENTID, appointment.getPatientID());
        values.put(AP_APPOINTMENTNO, appointment.getAppointmentNo());
        values.put(AP_APPOINTMENTDATE, String.valueOf(appointment.getAppointmentDate()));
        values.put(AP_PATIENTIMAGE, String.valueOf(appointment.getPatientImage()));
        values.put(AP_DIAGNOSTIC, appointment.getDiagnostic());
        db.update(Table_Patients, values, "_id = ?", new String[]{id});//queries by finding the field based on id
        return true;
    }

    // Deletes the patient based on ID
    public Integer deletePatient(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(Table_Patients, "_id = ?", new String[]{id}); // delete method returns the number of affected rows
    }

    // Dealtes the appoinment based on ID
    public Integer deleteAppointment(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(Table_Appointments, "_id = ?", new String[]{id}); // delete method returns the number of affected rows
    }
}
