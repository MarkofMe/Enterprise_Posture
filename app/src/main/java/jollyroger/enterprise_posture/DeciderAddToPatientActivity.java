package jollyroger.enterprise_posture;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import database.Appointment;
import database.DatabaseHandler;
import database.DbFragListviewCursorAdapter;
import database.Patient;

public class DeciderAddToPatientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decider_add_to_patient);

        Toolbar toolbar = (Toolbar) findViewById(R.id.add_to_patient_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        populateListView(null);
    }

    //addPatientSearchView
    private void populateListView(Cursor c) {
        DatabaseHandler dbHandler;
        if (c == null) {
            dbHandler = new DatabaseHandler(this);
            c = dbHandler.getPatientsTable();
        }

        final ListView lv = (ListView) findViewById(R.id.listview_add_to_patients);
        lv.setClickable(true);

        DbFragListviewCursorAdapter lvAdapter = new DbFragListviewCursorAdapter(this, c);
        lv.setAdapter(lvAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                SQLiteCursor s = (SQLiteCursor) lv.getItemAtPosition(position);

                Intent intent = getIntent();
                Bitmap bitmap = intent.getParcelableExtra("CameraImage");
                String[] points = intent.getStringArrayExtra("Points array");

                Intent nextIntent = new Intent(getBaseContext(), DeciderCreateAppointmentActivity.class);
                nextIntent.putExtra("Points array", points);
                Bundle extras = new Bundle();
                extras.putInt("patientID", s.getInt(0));
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bs);
                extras.putByteArray("CameraImage", bs.toByteArray());
                nextIntent.putExtras(extras);
                startActivity(nextIntent);
            }
        });

        final SearchView search = (SearchView) findViewById(R.id.addPatientSearchView);

        search.setQueryHint("Search the database...");

        search.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Log.d("Attacked","");
                populateListView(null);
            }
        });

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                DatabaseHandler db = new DatabaseHandler(getBaseContext());
                String searchQuery = search.getQuery().toString();
                searchQuery = searchQuery.replace(" ", "");
                Cursor c = db.searchForNames(searchQuery);
                populateListView(c);
                search.clearFocus();
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });
    }
}