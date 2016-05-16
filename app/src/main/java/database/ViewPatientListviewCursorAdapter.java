package database;

import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.DatabaseUtils;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import jollyroger.enterprise_posture.R;

/**
 * Created by Joel wade p4100378 on 14/05/2016.
 */
public class ViewPatientListviewCursorAdapter extends CursorAdapter {

    public ViewPatientListviewCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.listview_appointment, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor c) {

        TextView appointmentNumber = (TextView) view.findViewById(R.id.viewAppointmentNumber);
        TextView appointmentDate = (TextView) view.findViewById(R.id.viewAppointmentDate);
        TextView postureResult = (TextView) view.findViewById(R.id.viewPostureResult);
        ImageView appointment_Imageholder = (ImageView) view.findViewById(R.id.viewAppointment_imageholder);

        //Log.d("ViewAppointemntActivity", (DatabaseUtils.dumpCursorToString(c)));

        int appointNumb = c.getInt(c.getColumnIndexOrThrow("AppointmentNo"));
        appointmentNumber.setText(appointNumb + "");
        appointmentDate.setText(c.getString(c.getColumnIndexOrThrow("AppointmentDate")));
        postureResult.setText(c.getString(c.getColumnIndexOrThrow("Diagnostic")));


        Log.d("1", appointNumb + "");
        DatabaseHandler db = new DatabaseHandler(context);
        Cursor image = db.getAppointmentImages(appointNumb);
        Log.d("2", DatabaseUtils.dumpCursorToString(image));

        image.moveToFirst();
        byte[] b = image.getBlob(image.getColumnIndex("Image"));
        Bitmap img = BitmapFactory.decodeByteArray(b, 0, b.length);
        appointment_Imageholder.setImageBitmap(img);
    }
}
