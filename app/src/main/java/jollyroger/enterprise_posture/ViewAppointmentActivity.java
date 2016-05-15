package jollyroger.enterprise_posture;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.sql.Blob;
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
        //Appointment a = new Appointment();

        //For mark, the cursor contains all the appointments for the patient p.
        DatabaseHandler db = new DatabaseHandler(getBaseContext());
        Cursor c = db.getPatientAppointments(p.getPatientID());

        //Use this to grab specific columns, e.g.
        //String appDate = c.getString(c.getColumnIndexOrThrow("AppointmentDate"));
        //Gets the appDate as a String (duh). Take the column name e.g "AppointmentDate" from the dbhandler at the top.

//        Log.d("PatientID: ", "" + p.getPatientID());
        //Log.d("PatientID: ", "" + a.getPatientID());
        TextView appointmentNumber = (TextView) findViewById(R.id.viewAppointmentNumber);
        TextView appointmentDate = (TextView) findViewById(R.id.viewAppointmentDate);
        TextView postureResult = (TextView) findViewById(R.id.viewPostureResult);
        ImageView appointment_Imageholder = (ImageView) findViewById(R.id.viewAppointment_imageholder);

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        //Log.d("App number", (DatabaseUtils.dumpCursorToString(c)));
        //Log.d("App number", c.getString(c.getColumnIndexOrThrow("AppointmentNo")));

        c.moveToFirst();
        appointmentNumber.setText(c.getString(c.getColumnIndexOrThrow("AppointmentNo")));
        appointmentDate.setText(c.getString(c.getColumnIndexOrThrow("AppointmentDate")));
        postureResult.setText(c.getString(c.getColumnIndexOrThrow("Diagnostic")));
        byte[] b = c.getBlob(c.getColumnIndexOrThrow("PatientImage"));
        Bitmap img = BitmapFactory.decodeByteArray(b,0, b.length);
        appointment_Imageholder.setImageBitmap(img);
    }

    public void EditAppointment(View view) {
    }
}
