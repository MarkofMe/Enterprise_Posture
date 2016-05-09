package jollyroger.enterprise_posture;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

import static jollyroger.enterprise_posture.R.id.create_patient_toolbar;
import static jollyroger.enterprise_posture.R.id.datePicker;
import static jollyroger.enterprise_posture.R.id.firstNameInput;
import static jollyroger.enterprise_posture.R.id.lastNameInput;

import database.DatabaseHandler;
import database.ListviewCursorAdapter;
import database.Patient;

public class CreatePatientActivity extends AppCompatActivity {


    DatabaseHandler dbHandler = new DatabaseHandler(this);
    String firstName;
    String lastName;
    int date = datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_patient);
        Toolbar toolbar = (Toolbar) findViewById(create_patient_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    //On button press of add patient
    public void Add(View view)
    {
        //Grab text from the text fields
        EditText firstName = (EditText) findViewById(R.id.firstNameInput);
        EditText lastName = (EditText) findViewById(R.id.lastNameInput);

        //If both the fore and sur name fields contain text. (currently only needs text, doesnt need actual names i.e can have only spaces/punctuation.
        if (!firstName.getText().toString().matches("") && !lastName.getText().toString().matches("")){
            dbHandler.insertDataPatients(new Patient(firstName.getText().toString(), lastName.getText().toString(), new Date(date), "male", 1));
            finish();
        } else { //One of the fields was blank.
            Toast.makeText(this, "You did not enter your name", Toast.LENGTH_SHORT).show();
        }
    }


    public void AddDate(View view)
    {
        setContentView(R.layout.activity_create_patient_date);
        Toolbar toolbar = (Toolbar) findViewById(create_patient_toolbar);
        setSupportActionBar(toolbar);
    }

    public void Return(View view){

        setContentView(R.layout.activity_create_patient);
        Toolbar toolbar = (Toolbar) findViewById(create_patient_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
