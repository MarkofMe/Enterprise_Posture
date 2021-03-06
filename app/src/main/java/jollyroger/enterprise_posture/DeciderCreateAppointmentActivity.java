package jollyroger.enterprise_posture;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

import database.Appointment;
import database.AppointmentImage;
import database.DatabaseHandler;

import static jollyroger.enterprise_posture.R.id.decider_toolbar;

public class DeciderCreateAppointmentActivity extends AppCompatActivity {

    DatabaseHandler dbHandler = new DatabaseHandler(this);
    Bitmap photo = null;
    int patientID;
    String[] points2;

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
        points2 = intent.getStringArrayExtra("Points array");

        ImageView image = (ImageView) findViewById(R.id.createAppointmentImageview);
        image.setImageBitmap(bitmap);

        patientID = pid;

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

        int appointmentID = dbHandler.getNextAppointmentID();
        dbHandler.insertDataAppointments(new Appointment
                (patientID, appointmentID, new Date(), goodOrBadSpinner.getSelectedItem().toString()));

        //AppointmentImage(int appNumb, Bitmap i, String points2)
        dbHandler.insertDataImage(new AppointmentImage
                (appointmentID, photo, dbHandler.StringArrayToString(points2)));

        Toast.makeText(getBaseContext(), "Appointment Added.", Toast.LENGTH_LONG).show();

        startActivity(new Intent(this, Main_Menu_Activity.class));
    }
}
