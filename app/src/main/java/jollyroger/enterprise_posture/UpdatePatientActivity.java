package jollyroger.enterprise_posture;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
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

import static jollyroger.enterprise_posture.R.id.Update_Patient_toolbar;

public class UpdatePatientActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    DatabaseHandler dbHandler = new DatabaseHandler(this);

    EditText firstName;
    EditText lastName;
    Spinner gender;
    int month_x, day_x;
    int year_x = 2000;
    static final int DIALOG_ID = 0;
    Patient editP;
    Button btn;
    int patientId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_patient);

        Toolbar toolbar = (Toolbar) findViewById(Update_Patient_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //Get patient opbject to update.
        Bundle data = getIntent().getExtras();
        Patient p = data.getParcelable("patient");
        patientId = p.getPatientID();

        editP = p;

        //Set fields to vars in the patient object.
        firstName = (EditText) findViewById(R.id.firstNameUpdate);
        lastName = (EditText) findViewById(R.id.lastNameUpdate);
        gender = (Spinner) findViewById(R.id.editgenderSpinner);
        firstName.setText(p.getFirstName());
        lastName.setText(p.getSurName());

        TextView txtDob = (TextView) findViewById(R.id.DOB);

        showDialogOnButtonClick();

        String dob = p.getDoB();
        SimpleDateFormat writeFormat = new SimpleDateFormat("dd-MM-yyyy");
        String ddd = writeFormat.format(dob);

        Date d = writeFormat.parse(ddd, null);

        year_x = Integer.parseInt((android.text.format.DateFormat.format("yyyy", d)).toString());
        month_x = Integer.parseInt((android.text.format.DateFormat.format("MM", d)).toString()) - 1;
        day_x = Integer.parseInt((android.text.format.DateFormat.format("dd", d)).toString());

        txtDob.setText(p.getDoB());

        Spinner dropdown = (Spinner) findViewById(R.id.editgenderSpinner);
        String[] items = new String[]{"Male", "Female", "Other", "Not specified"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        //Set gender in dropdown menu
        if (p.getGender().equals("Male")) {
            gender.setSelection(0);
        } else if (p.getGender().equals("Female")) {
            gender.setSelection(1);
        } else if (p.getGender().equals("Other")) {
            gender.setSelection(2);
        } else {
            gender.setSelection(3);
        }
    }


    public void showDialogOnButtonClick() {
        btn = (Button) findViewById(R.id.editPatientDOB);

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

    public void Edit(View view) {
        //Grab text from the text fields
        firstName = (EditText) findViewById(R.id.firstNameUpdate);
        lastName = (EditText) findViewById(R.id.lastNameUpdate);
        gender = (Spinner) findViewById(R.id.editgenderSpinner);

        //If editing and not creating, update db, else add new value to db.
        //If both the fore and sur name fields contain text. (currently only needs text, doesnt need actual names i.e can have only spaces/punctuation.
        if (!firstName.getText().toString().matches("") && !lastName.getText().toString().matches("")) {
            dbHandler.updatePatients(new Patient(patientId, firstName.getText().toString(), lastName.getText().toString(),
                    new Date(year_x - 1900, month_x - 1, day_x), gender.getSelectedItem().toString(), 1));
            finish();
            startActivity(new Intent(this, Main_Menu_Activity.class));
        } else { //One of the fields was blank.
            Toast.makeText(this, "You did not enter your name", Toast.LENGTH_SHORT).show();
        }
    }

    public void DeletePatient(View v) {
        //Grab text from the text fields
        firstName = (EditText) findViewById(R.id.firstNameUpdate);
        lastName = (EditText) findViewById(R.id.lastNameUpdate);
        gender = (Spinner) findViewById(R.id.editgenderSpinner);

        dbHandler.updatePatients(new Patient(patientId, firstName.getText().toString(), lastName.getText().toString(),
                new Date(year_x - 1900, month_x - 1, day_x), gender.getSelectedItem().toString(), 0));
        finish();

        startActivity(new Intent(this, Main_Menu_Activity.class));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
