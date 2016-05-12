package jollyroger.enterprise_posture;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;

import database.DatabaseHandler;

import static jollyroger.enterprise_posture.R.id.decider_toolbar;

public class DeciderCreateAppointmentActivity extends AppCompatActivity {

    DatabaseHandler dbHandler = new DatabaseHandler(this);
    Bitmap photo = null;
    int pID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decider_create_appointment);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        int pid = extras.getInt("patientID");
        byte[] byteArray = getIntent().getByteArrayExtra("CameraImage");
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

        ImageView ImageHolder = (ImageView) findViewById(R.id.appointment_imageholder);
        ImageHolder.setImageBitmap(bitmap);
        photo = bitmap;

        pID = pid;

        //Toast.makeText(this, pid, Toast.LENGTH_SHORT).show();
        Log.d("ID: ", ""+pid);

        Toolbar toolbar = (Toolbar) findViewById(decider_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
