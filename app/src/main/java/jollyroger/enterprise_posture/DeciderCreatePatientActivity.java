package jollyroger.enterprise_posture;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import java.io.ByteArrayOutputStream;
import java.util.Date;

import database.DatabaseHandler;
import database.Patient;

import static jollyroger.enterprise_posture.R.id.create_patient_toolbar;

public class DeciderCreatePatientActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    DatabaseHandler dbHandler = new DatabaseHandler(this);
    String firstName;
    String lastName;
    int date;

    int patientID;

    int month_x, day_x;
    int year_x = 2000;
    static final int DIALOG_ID = 0;
    Button btn;

    Bitmap photo = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decider_create_patient);

        Toolbar toolbar = (Toolbar) findViewById(create_patient_toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        Bitmap bitmap = intent.getParcelableExtra("CameraImage");
        photo = bitmap;


        showDialogOnButtonClick();

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Spinner dropdown = (Spinner) findViewById(R.id.decidergenderSpinner);
        String[] items = new String[]{"Male", "Female", "Other", "Not specified"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
    }

    public void showDialogOnButtonClick() {
        btn = (Button) findViewById(R.id.decideraddPatientDOB);

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
            year_x = year;
            month_x = monthOfYear + 1;
            day_x = dayOfMonth;
            TextView DOB = (TextView) findViewById(R.id.deciderDOB);
            DOB.setText("Date of Birth: " + day_x + "/" + month_x + "/" + year_x);

        }
    };

    //On button press of add patient
    public void AddDeciderPatient(View view) {
        //Grab text from the text fields
        EditText firstName = (EditText) findViewById(R.id.deciderfirstNameInput);
        EditText lastName = (EditText) findViewById(R.id.deciderlastNameInput);
        Spinner gender = (Spinner) findViewById(R.id.decidergenderSpinner);


        //If both the fore and sur name fields contain text. (currently only needs text, doesnt need actual names i.e can have only spaces/punctuation.
        if (!firstName.getText().toString().matches("") && !lastName.getText().toString().matches("")) {
            dbHandler.insertDataPatients(new Patient(firstName.getText().toString(), lastName.getText().toString(), new Date(year_x - 1900, month_x, day_x), gender.getSelectedItem().toString(), 1));
            patientID = dbHandler.getNextPatientID();
            finish();
        } else { //One of the fields was blank.
            Toast.makeText(this, "You did not enter your name", Toast.LENGTH_SHORT).show();
        }

        Intent intent = new Intent(this, DeciderCreateAppointmentActivity.class);
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        photo.compress(Bitmap.CompressFormat.JPEG, 100, bs);

        Bundle extras = new Bundle();
        extras.putByteArray("CameraImage", bs.toByteArray());
        extras.putInt("patientID", patientID);
        intent.putExtras(extras);
        startActivity(intent);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    public void onNothingSelected(AdapterView<?> parent) {

    }
}