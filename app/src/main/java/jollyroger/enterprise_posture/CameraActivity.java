package jollyroger.enterprise_posture;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import static jollyroger.enterprise_posture.R.id.camera_toolbar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CameraActivity extends AppCompatActivity {


    private Camera camera;
    private Preview preview;
    private Button captureButton;
    private static String imageUri;

    private Camera.PictureCallback pic = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            File pictureFile = getOutputMediaFile();
            if (pictureFile == null) {
                Log.d(null, "Error creating media file, check storage permissions");
                return;
            }

            try {
                FileOutputStream fos = new FileOutputStream(pictureFile);
                fos.write(data);
                fos.close();
            } catch (FileNotFoundException e) {
                Log.d(null, "File not found: " + e.getMessage());
            } catch (IOException e) {
                Log.d(null, "Error accessing file: " + e.getMessage());
            }
        }
    };

    //without the ability to test, I think this SHOULD be the place that the activity changes?
    public void confirm() {
        final Context cont = this;
        AlertDialog.Builder b = new AlertDialog.Builder(this);

        b.setMessage(R.string.dialog_message);
        b.setTitle(R.string.dialog_title);

        //User confirms
        b.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                camera.takePicture(null, null, pic);
                /*
                Intent intent = new Intent(cont, PlotterActivity.class);
                intent.putExtra("image location as path", imageUri);
                startActivity(intent);
                */


                Intent intent = new Intent(cont, PlotterActivity.class);
                Bitmap image= INSERT-IMAGEVIEW-VARIABLE-HERE.getDrawingCache();  // create bitmap representation of the picture taken.

                Bundle extras = new Bundle();
                extras.putParcelable("imagebitmap", image);
                intent.putExtras(extras);
                startActivity(intent);


            }
        });

        b.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                return;
            }
        });

        AlertDialog ad = b.create();
        ad.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        Toolbar toolbar = (Toolbar) findViewById(camera_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        camera = getCameraInstance();
        preview = new Preview(this, camera);

        FrameLayout fl = (FrameLayout) findViewById(R.id.camera_preview);
        fl.addView(preview);

        captureButton = (Button) findViewById(R.id.button_capture);
        captureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirm();
            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_camera, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPause() {
        super.onPause();
        releaseCamera();
    }

    public static Camera getCameraInstance() {
        Camera c = null;

        try {
            c = Camera.open();
        } catch (Exception e) {
            Log.d(null, "Error opening camera: " + e.getMessage());
        }

        return c;
    }

    private void releaseCamera() {
        if (camera != null) {
            camera.release();
            camera = null;
        }
    }

    private static File getOutputMediaFile() {
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "MyCameraApp");

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d("MyCameraApp", "failed to create directory");
                return null;
            }
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        imageUri = mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp + ".jpg";
        File mediaFile = new File(imageUri);

        return mediaFile;
    }
}