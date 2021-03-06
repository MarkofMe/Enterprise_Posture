package database;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jollyroger.enterprise_posture.R;

public class DbFragListviewCursorAdapter extends CursorAdapter {

    public DbFragListviewCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.listview_items, parent, false);
    }

    // The bindView method is used to bind all data to a given view
    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        // Find textviews to populate in listview_items template
        TextView fullName = (TextView) view.findViewById(R.id.fullName);
        TextView dob = (TextView) view.findViewById(R.id.dob);

        // Take properties from cursor
        String strName = cursor.getString(cursor.getColumnIndexOrThrow("FirstName")) + " " + cursor.getString(cursor.getColumnIndexOrThrow("Surname"));
        String strDob = cursor.getString(cursor.getColumnIndexOrThrow("DoB"));

        //Assign properties to the corrosponding view objects
        dob.setText(strDob);
        fullName.setText("  " + strName);
    }

}