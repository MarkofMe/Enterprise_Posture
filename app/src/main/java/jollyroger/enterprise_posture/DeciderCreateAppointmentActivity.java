package jollyroger.enterprise_posture;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import static jollyroger.enterprise_posture.R.id.decider_toolbar;

public class DeciderCreateAppointmentActivity extends AppCompatActivity {

    Bitmap photo = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decider_create_appointment);

        Intent intent = getIntent();
        Bitmap bitmap = intent.getParcelableExtra("CameraImage");
        ImageView ImageHolder = (ImageView) findViewById(R.id.appointment_imageholder);
        ImageHolder.setImageBitmap(bitmap);
        photo = bitmap;

        Toolbar toolbar = (Toolbar) findViewById(decider_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
