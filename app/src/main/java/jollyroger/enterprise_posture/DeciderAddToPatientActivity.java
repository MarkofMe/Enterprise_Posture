package jollyroger.enterprise_posture;

import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import database.DatabaseHandler;
import database.ListviewCursorAdapter;
import database.Patient;

public class DeciderAddToPatientActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decider_add_to_patient);

        Toolbar toolbar = (Toolbar) findViewById(R.id.add_to_patient_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        populateListView();
    }

    private void populateListView() {

        DatabaseHandler dbHandler = new DatabaseHandler(this);

        Cursor cursor = dbHandler.getPatientsTable();

        //Log.v("Cursor Object", DatabaseUtils.dumpCursorToString(cursor));

        final ListView lv = (ListView) findViewById(R.id.listview_add_to_patients);
        lv.setClickable(true);

        ListviewCursorAdapter lvAdapter = new ListviewCursorAdapter(this, cursor);
        lv.setAdapter(lvAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                SQLiteCursor s = (SQLiteCursor) lv.getItemAtPosition(position);

                String dob = s.getString(3);
                SimpleDateFormat readFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy");

                Date d = null;
                try {
                    d = readFormat.parse(dob);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Patient p = new Patient(s.getInt(0), s.getString(1), s.getString(2), d, s.getString(4), s.getInt(5));

                Log.d("Paient is: ", "" + p.getFirstName());

//                Intent intent = new Intent(this, ViewPatientActivity.class);
//                intent.putExtra("patient", p);
//                startActivity(intent);

            }
        });
    }
}