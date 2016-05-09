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
    int date;

    int year_x, month_x, day_x;
    static final int DIALOG_ID = 0;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_patient);
        Toolbar toolbar = (Toolbar) findViewById(create_patient_toolbar);
        setSupportActionBar(toolbar);

        showDialogOnButtonClick();

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void showDialogOnButtonClick()
    {
        btn = (Button)findViewById(R.id.addPatientButton);

        btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                showDialog(DIALOG_ID);
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id){
        if(id == DIALOG_ID)
        {
            return new DatePickerDialog(this, dpickerListener, year_x, month_x, day_x);
        }
        else
        {
            return null;
        }
    }

    private DatePickerDialog.OnDateSetListener dpickerListener
            = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            year_x = year;
            month_x = monthOfYear;
            day_x = dayOfMonth;
        }
    };

    //On button press of add patient
    public void Add(View view) {
        //Grab text from the text fields
        EditText firstName = (EditText) findViewById(R.id.firstNameInput);
        EditText lastName = (EditText) findViewById(R.id.lastNameInput);

        //If both the fore and sur name fields contain text. (currently only needs text, doesnt need actual names i.e can have only spaces/punctuation.
        if (!firstName.getText().toString().matches("") && !lastName.getText().toString().matches("")) {
            dbHandler.insertDataPatients(new Patient(firstName.getText().toString(), lastName.getText().toString(), new Date(year_x - 1900, month_x, day_x), "male", 1));
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
