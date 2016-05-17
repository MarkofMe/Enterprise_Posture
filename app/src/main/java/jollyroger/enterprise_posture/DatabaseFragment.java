package jollyroger.enterprise_posture;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import database.DatabaseHandler;
import database.DbFragListviewCursorAdapter;
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

        populateListView(v, null);

        return v;
    }

    private void populateListView(View v, Cursor c) {

        DatabaseHandler dbHandler;
        //Allow the listview to be drawn from a custom cursor. Used when searching the DB.
        if (c == null) {
            dbHandler = new DatabaseHandler(getContext());
            c = dbHandler.getPatientsTable();
        }

        final ListView lv = (ListView) v.findViewById(R.id.listview_patients);
        lv.setClickable(true);

        DbFragListviewCursorAdapter lvAdapter = new DbFragListviewCursorAdapter(getContext(), c);
        lv.setAdapter(lvAdapter);

        //Allow for clicking on individual listview items. Clicking on an item
        //starts a new activity for viewing the patients details.
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                //Get the patient that was clicked on from the database.
                SQLiteCursor s = (SQLiteCursor) lv.getItemAtPosition(position);

                String dob = s.getString(3);
                SimpleDateFormat readFormat = new SimpleDateFormat("dd-MM-yyyy");

                Date d = null;
                try {
                    d = readFormat.parse(dob);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                //Recreate patient from the Database.
                Patient p = new Patient(s.getInt(0), s.getString(1), s.getString(2), d, s.getString(4), s.getInt(5));

                Intent intent = new Intent(getContext(), ViewPatientActivity.class);
                intent.putExtra("patient", p);
                startActivity(intent);

            }
        });


        //Setup the searchview.
        final SearchView search = (SearchView) v.findViewById(R.id.dbFragSearchview);

        search.setQueryHint("Search the database...");

        //The searchview runs a database query when the user clicks submit on the keyboard widget.
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                DatabaseHandler db = new DatabaseHandler(getContext());
                String searchQuery = search.getQuery().toString();
                //Remove any spaces, this makes comparing to the database values easier.
                searchQuery = searchQuery.replace(" ", "");
                Cursor c = db.searchForNames(searchQuery);
                populateListView(getView(), c);
                search.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.equals("")) {
                    this.onQueryTextSubmit("");
                }
                return true;
            }
        });
    }

    //Rebuilds the listview for times when it has been updated, such as when the app goes from the create
    //patient activity back to the database activity.
    @Override
    public void onResume() {
        super.onResume();
        if (!getUserVisibleHint()) {
            return;
        }

        populateListView(getView(), null);
    }

}