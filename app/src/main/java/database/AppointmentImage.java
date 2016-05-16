package database;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;

import java.io.ByteArrayOutputStream;

/**
 * Created by Joel Wade p4100378 on 13/05/2016.
 */

public class AppointmentImage {

    private int appointmentNo;
    private Bitmap image;
    private String points;


    public AppointmentImage(int appNumb, Bitmap i, String points) {
        appointmentNo = appNumb;
        image = i;
        this.points = points;
    }

    public Bitmap getBitImage() {
        return image;
    }

    public int getAppointmentNo() {
        return appointmentNo;
    }

    //Converts bitmap image into byte array for storing in the database.
    public byte[] getImage() {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    public String getPoints() {
        return points;
    }
}
