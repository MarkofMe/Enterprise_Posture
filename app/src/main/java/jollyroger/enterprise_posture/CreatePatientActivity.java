package jollyroger.enterprise_posture;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Date;

import static jollyroger.enterprise_posture.R.id.create_patient_toolbar;
import static jollyroger.enterprise_posture.R.id.datePicker;
import static jollyroger.enterprise_posture.R.id.firstNameInput;
import static jollyroger.enterprise_posture.R.id.lastNameInput;

import database.DatabaseHandler;
import database.ListviewCursorAdapter;
import database.Patient;

public class CreatePatientActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    DatabaseHandler dbHandler = new DatabaseHandler(this);
    String firstName;
    String lastName;
    //int date = datePicker;
    String date;
    Spinner yearSpinner;
    Spinner monthSpinner;
    Spinner daySpinner;
    String day;
    String month;
    String year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_patient);
        Toolbar toolbar = (Toolbar) findViewById(create_patient_toolbar);
        setSupportActionBar(toolbar);

        yearSpinner = (Spinner) findViewById(R.id.YearSpinner);
        ArrayAdapter yearAdapter = ArrayAdapter.createFromResource(this, R.array.Years, android.R.layout.simple_spinner_item);
        yearSpinner.setAdapter(yearAdapter);
        yearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                year = view.toString();
                System.out.println(year);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        yearSpinner.setOnItemSelectedListener(this);

        monthSpinner = (Spinner) findViewById(R.id.MonthSpinner);
        ArrayAdapter monthAdapter = ArrayAdapter.createFromResource(this, R.array.Months, android.R.layout.simple_spinner_item);
        monthSpinner.setAdapter(monthAdapter);
        monthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                month = view.toString();
                System.out.println(month);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        monthSpinner.setOnItemSelectedListener(this);

        daySpinner = (Spinner) findViewById(R.id.DaySpinner);
        ArrayAdapter dayAdapter = ArrayAdapter.createFromResource(this, R.array.Days, android.R.layout.simple_spinner_item);
        daySpinner.setAdapter(dayAdapter);
        daySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                day = view.toString();
                System.out.println(day);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        daySpinner.setOnItemSelectedListener(this);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    //On button press of add patient
    public void Add(View view) {
        //Grab text from the text fields
        EditText firstName = (EditText) findViewById(R.id.firstNameInput);
        EditText lastName = (EditText) findViewById(R.id.lastNameInput);
        date = month+"/"+day+"/"+year;


        //If both the fore and sur name fields contain text. (currently only needs text, doesnt need actual names i.e can have only spaces/punctuation.
        if (!firstName.getText().toString().matches("") && !lastName.getText().toString().matches("")) {
            dbHandler.insertDataPatients(new Patient(firstName.getText().toString(), lastName.getText().toString(), new Date(date), "male", 1));
            finish();
        } else { //One of the fields was blank.
            Toast.makeText(this, "You did not enter your name", Toast.LENGTH_SHORT).show();
        }
    }

    public void AddDate(View view) {
        setContentView(R.layout.activity_create_patient_date);
        Toolbar toolbar = (Toolbar) findViewById(create_patient_toolbar);
        setSupportActionBar(toolbar);
    }

    public void Return(View view) {

        setContentView(R.layout.activity_create_patient);
        Toolbar toolbar = (Toolbar) findViewById(create_patient_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
