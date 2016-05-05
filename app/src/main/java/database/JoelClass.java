package database;

import java.util.Date;

public class JoelClass {
    private int id;
    private String firstName;
    private String surName;
    private Date dob;

    public int getId() {
        return id;
    }

    public void setId(int p) {
        id = p;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String f) {
        firstName = f;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String s) {
        surName = s;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date doB) {
        dob = doB;
    }
}
