package jollyroger.enterprise_posture;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.Date;

import database.DatabaseHandler;
import database.Patient;

import static jollyroger.enterprise_posture.R.id.create_patient_toolbar;

public class CreatePatientActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_patient);
        Toolbar toolbar = (Toolbar) findViewById(create_patient_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void Add(View view)
    {

    }



}
