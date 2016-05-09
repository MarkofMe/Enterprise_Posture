package jollyroger.enterprise_posture;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

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
    String firstName = Integer.toString(firstNameInput);
    String lastName = Integer.toString(lastNameInput);
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

    public void Add(View view)
    {

       dbHandler.insertDataPatients(new Patient(firstName, lastName, new Date("15/11/1992"), "male", true));

    }
}
