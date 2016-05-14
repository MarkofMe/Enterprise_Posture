package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.util.Log;

import java.io.ByteArrayOutputStream;


public class DatabaseHandler extends SQLiteOpenHelper {

    private Patient patient = new Patient();
    private Appointment appointment = new Appointment(); //idk??

    //Database Name
    private static final String Database_Name = "Posture.db";

    //Patients table name
    private static final String Table_Patients = "Patients";

    //Appointments table name
    private static final String Table_Appointments = "Appointment";

    //Images table name
    private static final String Table_Images = "Images";

    // Images Table Columns names
    private static final String PI_IMAGE = "Image";
    private static final String PI_APPOINTMENTNO = "AppointmentNo";
    private static final String PI_POINTS = "Points";

    // Patients Table Columns names
    private static final String PT_ID = "_id";
    private static final String PT_FIRSTNAME = "FirstName";
    private static final String PT_SURNAME = "Surname";
    private static final String PT_DOB = "DoB";
    private static final String PT_GENDER = "Gender";
    private static final String PT_ACTIVE = "Active";

    //Appointments Table Columns names
    private static final String AP_ID = "_id";
    private static final String AP_PATIENTID = "PatientID";
    private static final String AP_APPOINTMENTNO = "AppointmentNo";
    private static final String AP_APPOINTMENTDATE = "AppointmentDate";
    private static final String AP_PATIENTIMAGE = "PatientImage";
    private static final String AP_DIAGNOSTIC = "Diagnostic";

    //private SQLiteDatabase db;

    //Constructor
    public DatabaseHandler(Context context) {
        super(context, Database_Name, null, 1);
    }

    //Creates the database tables and columns
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PATIENTS_TABLE = "CREATE TABLE "
                + Table_Patients
                + "(" + PT_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PT_FIRSTNAME
                + " TEXT NOT NULL, "
                + PT_SURNAME
                + " TEXT NOT NULL, "
                + PT_DOB
                + " TEXT NOT NULL, "
                + PT_GENDER
                + " TEXT NOT NULL, "
                + PT_ACTIVE
                + " INTEGER NOT NULL );";

        db.execSQL("PRAGMA foreign_keys=ON;");
        String CREATE_APPOINTMENTS_TABLE = "CREATE TABLE "
                + Table_Appointments
                + "(" + AP_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + AP_PATIENTID
                + " INTEGER NOT NULL, "
                + AP_APPOINTMENTNO
                + " INTEGER NOT NULL, "
                + AP_PATIENTIMAGE
                + " BLOB, "
                + AP_APPOINTMENTDATE
                + " TEXT NOT NULL, "
                + AP_DIAGNOSTIC
                + " TEXT NOT NULL, " +
                "FOREIGN KEY(" + AP_PATIENTID + ") REFERENCES " + Table_Patients + "(" + PT_ID + "));";

        String CREATE_IMAGE_TABLE = "CREATE TABLE "
                + Table_Images
                + "(" + PI_IMAGE
                + " BLOB, "
                + PI_POINTS
                + " TEXT, "
                + PI_APPOINTMENTNO
                + " INTEGER NOT NULL, " +
                "FOREIGN KEY(" + PI_APPOINTMENTNO + ") REFERENCES " + Table_Appointments + "(" + AP_ID + "));";
        db.execSQL(CREATE_PATIENTS_TABLE);
        db.execSQL(CREATE_APPOINTMENTS_TABLE);
        db.execSQL(CREATE_IMAGE_TABLE);
    }

    //Converts input int array into a string, to be used for storage of plotter points in the db.
    public String intToString(int[] ints) {
        StringBuilder builder = new StringBuilder();
        for (int i : ints) {
            if (builder.length() > 0) builder.append(",");
            builder.append("'").append(i).append("'");
        }
        return builder.toString();
    }

    //Converts the db's string of plotter points into an int[].
    public int[] stringToInt(String ints) {
        String[] s = ints.split(",");
        int[] numbs = new int[s.length];
        for (int i = 0; i < s.length; i++) {
            numbs[i] = Integer.parseInt(s[i]);
        }
        return numbs;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldNum, int newNum) {
        //Drop older table if exists and create fresh
        db.execSQL("DROP TABLE IF EXISTS " + Table_Patients);
        db.execSQL("DROP TABLE IF EXISTS " + Table_Appointments);
        onCreate(db);
    }

    public Cursor getPatientsTable() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM Patients WHERE Active=1", null);
    }

    public Cursor getPatientAppointments(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM Appointment WHERE patientID=" + id, null);
    }

    public int getNextPatientID(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Patients", null);
        return c.getCount();
    }

    public int getNextAppointmentID(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Appointment", null);
        return c.getCount();
    }

    public boolean insertDataImage(AppointmentImage image) {
        ContentValues values = new ContentValues();
        values.put(PI_IMAGE, image.getImage());
        values.put(PI_POINTS, image.getPoints());
        values.put(PI_APPOINTMENTNO, image.getAppointmentNo());
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.insert(Table_Images, null, values);
        if (result == -1)// if the contents arent inserted db.insert returns -1, so this is a check for if the data is inserted
            return false;
        else
            return true;
    }

    // Adds data to the patients table
    public boolean insertDataPatients() {
        ContentValues values = new ContentValues();
        values.put(PT_FIRSTNAME, patient.getFirstName());
        values.put(PT_SURNAME, patient.getSurName());
        values.put(PT_DOB, String.valueOf(patient.getDoB())); // because its a date variable
        values.put(PT_GENDER, patient.getGender());
        values.put(PT_ACTIVE, patient.getActive());
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.insert(Table_Patients, null, values);
        if (result == -1)// if the contents arent inserted db.insert returns -1, so this is a check for if the data is inserted
            return false;
        else
            return true;
    }

    public boolean insertDataPatients(Patient p) {
        ContentValues values = new ContentValues();
        values.put(PT_FIRSTNAME, p.getFirstName());
        values.put(PT_SURNAME, p.getSurName());
        values.put(PT_DOB, String.valueOf(p.getDoB())); // because its a date variable
        values.put(PT_GENDER, p.getGender());
        values.put(PT_ACTIVE, p.getActive());
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.insert(Table_Patients, null, values);
        if (result == -1)// if the contents arent inserted db.insert returns -1, so this is a check for if the data is inserted
            return false;
        else
            return true;
    }

    // Adds data to the appointments table
    public boolean insertDataAppointments(Appointment appointment) {
        ContentValues values = new ContentValues();
        values.put(AP_PATIENTID, appointment.getPatientID());
        values.put(AP_APPOINTMENTNO, appointment.getAppointmentNo());
        values.put(AP_APPOINTMENTDATE, String.valueOf(appointment.getAppointmentDate())); // because its a date variable
        //Add photo to db
        Log.d("For will, number 1", "");
        Bitmap photo = appointment.getPatientImage();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        photo.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        byte[] bArray = bos.toByteArray();
        values.put(AP_PATIENTIMAGE, bArray);
        Log.d("For will, number 2", "");
        //
        values.put(AP_DIAGNOSTIC, appointment.getDiagnostic());
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.insert(Table_Appointments, null, values);
        if (result == -1)// if the contents arent inserted db.insert returns -1, so this is a check for if the data is inserted
            return false;
        else
            return true;
    }

    // Updates a field based on their ID
    public boolean updatePatients(String id) {
        ContentValues values = new ContentValues();
        values.put(PT_ID, patient.getPatientID());
        values.put(PT_FIRSTNAME, patient.getFirstName());
        values.put(PT_SURNAME, patient.getSurName());
        values.put(PT_DOB, String.valueOf(patient.getDoB())); // because its a date variable
        values.put(PT_GENDER, patient.getGender());
        values.put(PT_ACTIVE, patient.getActive());
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(Table_Patients, values, "_id = ?", new String[]{id}); //queries by finding the field based on id
        return true;
    }

    // Updates a field based on their ID
    public boolean updatePatients(Patient p) {
        ContentValues values = new ContentValues();
        values.put(PT_ID, p.getPatientID());
        values.put(PT_FIRSTNAME, p.getFirstName());
        values.put(PT_SURNAME, p.getSurName());
        values.put(PT_DOB, String.valueOf(p.getDoB())); // because its a date variable
        values.put(PT_GENDER, p.getGender());
        values.put(PT_ACTIVE, p.getActive());
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(Table_Patients, values, "_id = ?", new String[]{Integer.toString(p.getPatientID())}); //queries by finding the field based on id
        return true;
    }

    // Updates a field based on their ID
    public boolean updateAppointments(String id) {
        ContentValues values = new ContentValues();
        //values.put(AP_ID, appointment.getAppointmentID());
        values.put(AP_PATIENTID, appointment.getPatientID());
        values.put(AP_APPOINTMENTNO, appointment.getAppointmentNo());
        values.put(AP_APPOINTMENTDATE, String.valueOf(appointment.getAppointmentDate()));
        values.put(AP_PATIENTIMAGE, String.valueOf(appointment.getPatientImage()));
        values.put(AP_DIAGNOSTIC, appointment.getDiagnostic());
        SQLiteDatabase db = this.getWritableDatabase();
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