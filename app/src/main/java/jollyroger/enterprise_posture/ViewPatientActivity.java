package jollyroger.enterprise_posture;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.text.SimpleDateFormat;

import database.Patient;


public class ViewPatientActivity extends AppCompatActivity {

    public static Activity act;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        act = this;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_patient);

        Toolbar toolbar = (Toolbar) findViewById(R.id.View_Patient_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle data = getIntent().getExtras();

        //Log.d("PatientID: ", "" + p.getPatientID());
        TextView firstName = (TextView) findViewById(R.id.viewFirstName);
        TextView lastName = (TextView) findViewById(R.id.viewLastName);
        TextView dob = (TextView) findViewById(R.id.viewDob);
        TextView gender = (TextView) findViewById(R.id.viewGender);

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        //When using the back button to get to this class, the intent isn't used to create the class
        //and thus p is null. Send back to the DB fragment instead.
        try {
            Patient p = data.getParcelable("patient");
            firstName.setText(p.getFirstName());
            lastName.setText(p.getSurName());
            dob.setText(formatter.format(p.getDoB()));

            gender.setText(p.getGender());
        } catch (NullPointerException e) {
            finish();
        }
    }

    public void EditPatient(View v) {
        Bundle data = getIntent().getExtras();
        Patient p = data.getParcelable("patient");

        Intent intent = new Intent(getApplicationContext(), UpdatePatientActivity.class);
        intent.putExtra("patient", p);
        startActivity(intent);
    }

    public void ViewAppointment(View view) {
        Bundle data = getIntent().getExtras();
        Patient p = data.getParcelable("patient");

        Intent intent = new Intent(getApplicationContext(), ViewAppointmentActivity.class);
        intent.putExtra("patient", p);
        startActivity(intent);
    }
}
