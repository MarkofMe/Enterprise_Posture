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

public class ListviewCursorAdapter extends CursorAdapter {
    public ListviewCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    // The newView method is used to inflate a new view and return it,
    // you don't bind any data to the view at this point.
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.listview_items, parent, false);
    }

    // The bindView method is used to bind all data to a given view
    // such as setting the text on a TextView.
    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        // Find textviews to populate in listview_items template
        TextView fullName = (TextView) view.findViewById(R.id.fullName);
        TextView dob = (TextView) view.findViewById(R.id.dob);

        // Take properties from cursor
        String strName = cursor.getString(cursor.getColumnIndexOrThrow("FirstName")) + " " + cursor.getString(cursor.getColumnIndexOrThrow("Surname"));
        String strDob = cursor.getString(cursor.getColumnIndexOrThrow("DoB"));

        Log.d("Into to dob", strDob);

        //Convert date in the DB into a more readable format.
        SimpleDateFormat readFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy");
        SimpleDateFormat writeFormat = new SimpleDateFormat("dd-MM-yyyy");

        //Set ListView fields.
        String d;
        try {
            Date dbDate = readFormat.parse(strDob);
            d = writeFormat.format(dbDate);
            if (d.equals(null)) {
                d = "No dob set";
            } else {
                dob.setText(d);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        fullName.setText(strName);
    }

}