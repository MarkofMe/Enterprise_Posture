package jollyroger.enterprise_posture;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.DatabaseUtils;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import database.DatabaseHandler;
import database.DbFragListviewCursorAdapter;
import database.Patient;
import database.ViewPatientListviewCursorAdapter;

public class ViewAppointmentActivity extends AppCompatActivity {

    public static Activity act;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        act = this;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_appointments);

        Toolbar toolbar = (Toolbar) findViewById(R.id.View_Appointment_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //
        DatabaseHandler dbHandler = new DatabaseHandler(getBaseContext());

        Bundle data = getIntent().getExtras();
        Patient p = data.getParcelable("patient");

        Cursor cursor = dbHandler.getPatientAppointments(p.getPatientID());

        final ListView lv = (ListView) findViewById(R.id.viewAppointment_Listview);
        //lv.setClickable(true);

        ViewPatientListviewCursorAdapter lvAdapter = new ViewPatientListviewCursorAdapter(getBaseContext(), cursor);
        lv.setAdapter(lvAdapter);
    }
}
