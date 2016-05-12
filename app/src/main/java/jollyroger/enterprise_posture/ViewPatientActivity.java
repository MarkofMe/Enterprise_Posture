package jollyroger.enterprise_posture;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import database.Patient;

/**
 * Created by hj on 11/05/2016.
 */
public class ViewPatientActivity extends Activity {

    public static Activity act;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        act = this;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_patient);

        Bundle data = getIntent().getExtras();
        Patient p = data.getParcelable("patient");

        Log.d("PatientID: ", "" + p.getPatientID());
        TextView firstName = (TextView) findViewById(R.id.viewFirstName);
        TextView lastName = (TextView) findViewById(R.id.viewLastName);
        TextView dob = (TextView) findViewById(R.id.viewDob);
        TextView gender = (TextView) findViewById(R.id.viewGender);

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        firstName.setText(p.getFirstName());
        lastName.setText(p.getSurName());
        dob.setText(formatter.format(p.getDoB()));

        gender.setText(p.getGender());

    }

    public void EditPatient(View v) {
        Bundle data = getIntent().getExtras();
        Patient p = data.getParcelable("patient");

        Intent intent = new Intent(getApplicationContext(), DatabaseFragmentCreatePatientActivity.class);
        intent.putExtra("patient", p);
        startActivity(intent);
    }

}
