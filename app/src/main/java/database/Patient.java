package database;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Patient implements Parcelable {

    private int PatientID;
    private String FirstName;
    private String SurName;
    private String DoB;
    private String Gender;
    private Integer Active;

    //Constructors
    public Patient() {

    }

    public Patient(String FirstName, String SurName, Date DoB, String Gender, Integer Active) {
        this.FirstName = FirstName;
        this.SurName = SurName;
        this.DoB = setDate(DoB);
        this.Gender = Gender;
        this.Active = Active;
    }

    public Patient(Integer patientID, String FirstName, String SurName, Date DoB, String Gender, Integer Active) {
        this.PatientID = patientID;
        this.FirstName = FirstName;
        this.SurName = SurName;
        this.DoB = setDate(DoB);
        this.Gender = Gender;
        this.Active = Active;
    }

    public Patient(Integer patientID, String FirstName, String SurName, String DoB, String Gender, Integer Active) {
        this.PatientID = patientID;
        this.FirstName = FirstName;
        this.SurName = SurName;
        this.DoB = DoB;
        this.Gender = Gender;
        this.Active = Active;
    }

    public Patient(Parcel in) {
        in.readInt();
        PatientID = Integer.parseInt(in.readString());
        FirstName = in.readString();
        SurName = in.readString();
        DoB = in.readString();
        Gender = in.readString();
        Active = in.readInt();
    }

    private String setDate(Date d) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(d);
    }

    public static final Creator<Patient> CREATOR = new Creator<Patient>() {
        @Override
        public Patient createFromParcel(Parcel in) {
            return new Patient(in);
        }

        @Override
        public Patient[] newArray(int size) {
            return new Patient[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[]{"" + this.PatientID, this.FirstName, this.SurName, "" + this.DoB, this.Gender, "" + this.Active});
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

    public void setSurName(String SurName) {
        SurName = SurName;
    }

    public String getDoB() {
        return DoB;
    }

    public void setDoB(Date doB) {
        DoB = setDate(doB);
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
