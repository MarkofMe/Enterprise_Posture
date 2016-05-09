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


    DatabaseHandler dbHandler = new DatabaseHandler(getContext());
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

    public void Add(View view)
    {



       dbHandler.insertDataPatients(new Patient("Joel2", "Wade2", new Date(date), "male", true));

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
