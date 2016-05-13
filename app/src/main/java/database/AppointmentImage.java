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
    private byte[] image;
    private String points;

    AppointmentImage(int appoint, byte[] i, String points) {
        appointmentNo = appoint;
        image = i;
        this.points = points;
    }

    AppointmentImage(int appoint, Bitmap i, String points) {
        appointmentNo = appoint;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        i.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        image = stream.toByteArray();
        this.points = points;
    }

    public Bitmap getBitImage() {
        return BitmapFactory.decodeByteArray(image,0, image.length);
    }

    public int getAppointmentNo() {
        return appointmentNo;
    }

    public byte[] getImage() {
        return image;
    }

    public String getPoints() {
        return points;
    }
}
