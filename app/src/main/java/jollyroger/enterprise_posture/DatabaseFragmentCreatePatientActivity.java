package jollyroger.enterprise_posture;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import database.DatabaseHandler;
import database.Patient;

import static jollyroger.enterprise_posture.R.id.create_patient_toolbar;

public class DatabaseFragmentCreatePatientActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    DatabaseHandler dbHandler = new DatabaseHandler(this);

    int month_x, day_x;
    int year_x = 2000;
    static final int DIALOG_ID = 0;
    boolean edit = false;
    Patient editP;
    Button btn;
    int patientId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (ViewPatientActivity.act != null) {
            ViewPatientActivity.act.finish();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_fragment_create_patient);
        Toolbar toolbar = (Toolbar) findViewById(create_patient_toolbar);
        setSupportActionBar(toolbar);

        showDialogOnButtonClick();

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Spinner dropdown = (Spinner) findViewById(R.id.genderSpinner);
        String[] items = new String[]{"Male", "Female", "Other", "Not specified"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

    }

    public void showDialogOnButtonClick() {
        btn = (Button) findViewById(R.id.addPatientDOB);

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showDialog(DIALOG_ID);
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_ID) {
            return new DatePickerDialog(this, dpickerListener, year_x, month_x, day_x);
        } else {
            return null;
        }
    }

    private DatePickerDialog.OnDateSetListener dpickerListener
            = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            TextView DOB = (TextView) findViewById(R.id.DOB);

            year_x = year;
            month_x = monthOfYear + 1;
            day_x = dayOfMonth;
            DOB.setText("Date of Birth: " + day_x + "/" + month_x + "/" + year_x);


        }
    };

    //On button press of add patient
    public void Add(View view) {
        //Grab text from the text fields
        EditText firstName = (EditText) findViewById(R.id.firstNameInput);
        EditText lastName = (EditText) findViewById(R.id.lastNameInput);
        Spinner gender = (Spinner) findViewById(R.id.genderSpinner);

        if (!firstName.getText().toString().matches("") && !lastName.getText().toString().matches("")) {
            dbHandler.insertDataPatients(new Patient(firstName.getText().toString(), lastName.getText().toString(),
                    new Date(year_x - 1900, month_x - 1, day_x), gender.getSelectedItem().toString(), 1));
            finish();
        } else { //One of the fields was blank.
            Toast.makeText(this, "You did not enter your name", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
