package jollyroger.enterprise_posture;


import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import database.DatabaseHandler;
import database.ListviewCursorAdapter;
import database.Patient;


/**
 * A simple {@link Fragment} subclass.
 */
public class DatabaseFragment extends Fragment {

    public DatabaseFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_database, container, false);

        populateListView(v);

        return v;
    }

    private void populateListView(View v) {

        DatabaseHandler dbHandler = new DatabaseHandler(getContext());

        Cursor cursor = dbHandler.getPatientsTable();

        //Log.v("Cursor Object", DatabaseUtils.dumpCursorToString(cursor));

        ListView lv = (ListView) v.findViewById(R.id.listview_patients);

        ListviewCursorAdapter lvAdapter = new ListviewCursorAdapter(getContext(), cursor);
        lv.setAdapter(lvAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!getUserVisibleHint()) {
            return;
        }

        populateListView(getView());
    }

}