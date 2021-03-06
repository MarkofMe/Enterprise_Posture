package jollyroger.enterprise_posture;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.Toast;

import static jollyroger.enterprise_posture.R.id.decider_toolbar;

public class DeciderActivity extends AppCompatActivity {

    //This activity is used when a picture has been taken, the user makes the decision to either
    //Add the photo to an already existing patient or to create a new patient to add it to.
    Bitmap photo = null;
    String[] points = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decider);
        Intent intent = getIntent();
        Bitmap bitmap = intent.getParcelableExtra("CameraImage");
        ImageView ImageHolder = (ImageView) findViewById(R.id.decider_imageholder);
        ImageHolder.setImageBitmap(bitmap);
        photo = bitmap;

        points = intent.getStringArrayExtra("Points array");
        Toolbar toolbar = (Toolbar) findViewById(decider_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void TakeAnotherPicture(View view) {
        Toast.makeText(this, "Yet to be implemented", Toast.LENGTH_SHORT).show();
    }

    public void AddToNewPatient(View view) {
        Intent intent = new Intent(this, DeciderCreatePatientActivity.class);
        intent.putExtra("Points array", points);
        intent.putExtra("CameraImage", photo);
        startActivity(intent);
    }

    public void AddToExistingPatient(View view) {
        Intent intent = new Intent(this, DeciderAddToPatientActivity.class);
        intent.putExtra("Points array", points);
        intent.putExtra("CameraImage", photo);
        startActivity(intent);
    }

}
