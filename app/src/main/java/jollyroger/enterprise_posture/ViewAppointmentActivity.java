package jollyroger.enterprise_posture;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import database.DatabaseHandler;
import database.Patient;
import database.Appointment;

public class ViewAppointmentActivity extends AppCompatActivity {

    public static Activity act;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        act = this;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_appointment);

        Toolbar toolbar = (Toolbar) findViewById(R.id.View_Appointment_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle data = getIntent().getExtras();
        Patient p = data.getParcelable("patient");
        Appointment a = new Appointment();

        Log.d("PatientID: ", "" + p.getPatientID());
        Log.d("PatientID: ", "" + a.getPatientID());
        TextView appointmentNumber = (TextView) findViewById(R.id.viewAppointmentNumber);
        TextView appointmentDate = (TextView) findViewById(R.id.viewAppointmentDate);
        TextView postureResult = (TextView) findViewById(R.id.viewPostureResult);
        ImageView appointment_Imageholder = (ImageView) findViewById(R.id.viewAppointment_imageholder);

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        appointmentNumber.setText(a.getAppointmentNo());
        appointmentDate.setText(formatter.format(a.getAppointmentDate()));
        postureResult.setText(formatter.format(a.getDiagnostic()));

        appointment_Imageholder.setImageBitmap(a.getPatientImage());
    }

    public void EditAppointment(View view) {
    }
}
