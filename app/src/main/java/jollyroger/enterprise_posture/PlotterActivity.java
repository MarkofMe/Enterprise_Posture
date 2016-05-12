package jollyroger.enterprise_posture;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

import static jollyroger.enterprise_posture.R.id.plotter_toolbar;

public class
PlotterActivity extends AppCompatActivity {

    ViewGroup root; // to soon be view of id "rootXML", which is the "relativeLayout" reference id.
    ArrayList<ImageView> clickMarker;   // stores collection of markers.
    ArrayList<Point> markerPositions;   // stores the X and Y of the pointers.

    static final int CAM_REQUEST = 10;
    Bitmap photo = null;
    private ImageView ImageHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plotter);

        root = (ViewGroup) findViewById(R.id.rootXML);  // view of id "rootXML", which is the "relativeLayout" reference id.
        clickMarker = new ArrayList<>(); // arrayList to store new image, views, which will be markers to go over top of photograph when touched.
        markerPositions = new ArrayList<>(); // arrayList which stores X, Y value of a point. This is used to store the locations of all points in this Image.


        //Intent i = getIntent();

        //photo = new ImageView(this);
        //photo.setImageBitmap(BitmapFactory.decodeFile(i.getStringExtra(Main_Menu_Activity.path)));
//        Bundle extras = getIntent().getExtras();    // get the bitmap of image from CameraActivity.
//        Bitmap bmp = (Bitmap) extras.getParcelable("Bitmap");   // setting bitmap stored in parcel to new bitmap variable.
//        //photo = null; // ? needed ? //
//        photo.setImageBitmap(bmp);  // setting the image stored in bitmap to the imageview here.

        //RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        //photo.setLayoutParams(lp);  // ?
        //root.addView(photo);
        //root.addView(photo, lp);

        ImageHolder = (ImageView) findViewById(R.id.ImageHolder);

        setTouchPointListener();

        Toolbar toolbar = (Toolbar) findViewById(plotter_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void setTouchPointListener() // method to handle displaying pointers when clicked.
    {

        root.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent e) {
                if (e.getAction() == MotionEvent.ACTION_UP)  // touch lifted (clicked and released),
                {
                    // creating the image (pointer icon).
                    ImageView newMarker = new ImageView(PlotterActivity.this);
                    newMarker.setImageResource(R.drawable.markericon);

                    // creating a relativeLayout LayoutParams object, to set Margins of object.
                    RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                    newMarker.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);  // Solves issue! - centre of image issue, allows the image to be centred when touched.
                    //lp.setMargins((int) e.getX() - (newMarker.getMeasuredWidth() / 2), (int) e.getY() - (newMarker.getMeasuredHeight() / 2), 0, 0);    // setting margins of layoutParams to location touched.
                    lp.leftMargin = (int) e.getX() - (newMarker.getMeasuredWidth() / 2);
                    lp.topMargin = (int) e.getY() - (newMarker.getMeasuredHeight() / 2);

                    newMarker.setLayoutParams(lp);  // setting ImageView layoutParams to that set above.
                    newMarker.setScaleType(ImageView.ScaleType.MATRIX); // Solves issue! - setting scale, to avoid the smaller image issue when clicking at the sides of the screen.
                    root.addView(newMarker);       // adding newMarker to the RelativeLayout.
                    clickMarker.add(newMarker);   // add marker to array, so it can be undone if needed.

                    // Points recording section.
                    Point thisImage = new Point((int) e.getX() - (newMarker.getMeasuredWidth() / 2), (int) e.getY() - (newMarker.getMeasuredHeight() / 2));    // creating new Point object containing the location clicked (X, Y).
                    markerPositions.add(thisImage); // adding created Point object to arrayList.
                    // for testing purposes -Log.d("Points check", "The X is: " + thisImage.x  + " The Y is: " + thisImage.y + ". Also should match this X " + e.getX() + " and this: " + e.getY());
                    return true;
                }
                return true;
            }
        });

    }

    // method to handle button functionality when clicked.
    // android:onClick="onBtnClicked" registered for undo_button in XML section. can now use this method to handle clicks to "Undo" button.
    public void onBtnClicked(View v) {
        switch (v.getId()) {
            case R.id.undo_button:

                // if arrayList is not null and not empty, as well as markerPositions being instantiated and not being empty (i.e points added to this collection, also from onTouchEvent method.
                if ((clickMarker != null && !clickMarker.isEmpty()) && (markerPositions != null && !clickMarker.isEmpty())) {
                    // deleting marker from screen view and arrayList.
                    ImageView delThisMarker = clickMarker.get((clickMarker.size() - 1));    // get the last in the list (i.e the current most recent marker).
                    root.removeView(delThisMarker); // remove this from the view.
                    clickMarker.remove(delThisMarker);  // remove it from the collection.

                    // deleting corresponding point from arrayList. Similar to above, without removing from view (as not part of view).
                    Point delThisPoint = markerPositions.get((markerPositions.size() - 1));
                    //for testing purposes - Log.d("Points check#2", "The X is: " + delThisPoint.x + " The Y is: " + delThisPoint.y);
                    markerPositions.remove(delThisPoint);
                }
                break;
            case R.id.submit_button:


                Intent intent = new Intent(this, DeciderActivity.class);
                intent.putExtra("CameraImage", photo);
                startActivity(intent);
                // continue on to calculation page (needs implementing).

                // also - write imageView stored in here, markerPositions (array), and the returned data to the database.


                break;
            default:
                break;
        }
    }


    public void OpenCamera(View view) {
        Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //File file = getFile();
        //camera_intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        startActivityForResult(camera_intent, CAM_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == CAM_REQUEST) {
                Bitmap cameraImage = (Bitmap) data.getExtras().get("data");
                ImageHolder.setImageBitmap(cameraImage);
                photo = cameraImage;
            }
        }

    }
}
