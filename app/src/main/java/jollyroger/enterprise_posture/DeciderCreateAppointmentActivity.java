package jollyroger.enterprise_posture;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.util.Date;

import database.Appointment;
import database.DatabaseHandler;

import static jollyroger.enterprise_posture.R.id.decider_toolbar;

public class DeciderCreateAppointmentActivity extends AppCompatActivity {

    DatabaseHandler dbHandler = new DatabaseHandler(this);
    Bitmap photo = null;
    int pID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decider_create_appointment);

        TextView appointmentNo = (TextView) findViewById(R.id.appointmentNumber);
        appointmentNo.setText(dbHandler.getNextAppointmentID() + "");

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        int pid = extras.getInt("patientID");
        byte[] byteArray = getIntent().getByteArrayExtra("CameraImage");
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        photo = bitmap;

        pID = pid;

        Toolbar toolbar = (Toolbar) findViewById(decider_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Spinner dropdown = (Spinner) findViewById(R.id.goodOrBadSpinner);
        String[] items = new String[]{"Good", "Bad"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
    }

    public void AddAppointment(View view) {
        Spinner goodOrBadSpinner = (Spinner) findViewById(R.id.goodOrBadSpinner);

        dbHandler.insertDataAppointments(new Appointment
                (pID, dbHandler.getNextAppointmentID(), new Date(), photo, goodOrBadSpinner.getSelectedItem().toString()));

        Toast.makeText(getBaseContext(), "Appointment Added.", Toast.LENGTH_LONG).show();

        startActivity(new Intent(this, Main_Menu_Activity.class));
    }
}
