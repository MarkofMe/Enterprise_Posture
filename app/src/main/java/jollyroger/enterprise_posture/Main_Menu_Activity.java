package jollyroger.enterprise_posture;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.net.URI;

import tabs.SlidingTabLayout;

import static jollyroger.enterprise_posture.R.id.main_menu_toolbar;
import static jollyroger.enterprise_posture.R.layout.activity_main_menu;

public class Main_Menu_Activity extends AppCompatActivity {

    private ViewPager mPager;
    private SlidingTabLayout mTabs;
    ImageView imageView;
    static final int CAM_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main_menu);
        Toolbar toolbar = (Toolbar) findViewById(main_menu_toolbar);
        setSupportActionBar(toolbar);
        imageView = (ImageView)findViewById(R.id.image_viewer);


        //NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment)
        //        getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        //drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);


        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        mTabs = (SlidingTabLayout) findViewById(R.id.tabs);
        mTabs.setDistributeEvenly(true);
        mTabs.setCustomTabView(R.layout.custom_tab_view, R.id.tabText);
        mTabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.colorAccent);
            }
        });
        mTabs.setViewPager(mPager);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.


        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        return super.onOptionsItemSelected(item);
    }


    class MyPagerAdapter extends FragmentPagerAdapter {

        int icons[] = {R.drawable.ic_camera, R.drawable.ic_database, R.drawable.ic_settings, R.drawable.ic_logo};
        String[] tabText = getResources().getStringArray(R.array.tabs);

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
            tabText = getResources().getStringArray(R.array.tabs);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            if (position == 0) {

                fragment = new CameraFragment();
            } else if (position == 1) {
                fragment = new DatabaseFragment();
            } else if (position == 2) {
                fragment = new SettingsFragment();
            } else if (position == 3) {
                fragment = new CreditsFragment();
            }
            return fragment;
        }

        public CharSequence getPageTitle(int position) {
            Drawable drawable = getResources().getDrawable(icons[position]);
            drawable.setBounds(0, 0, 50, 50);
            ImageSpan imageSpan = new ImageSpan(drawable);
            SpannableString spannableString = new SpannableString(" ");
            spannableString.setSpan(imageSpan, 0, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            return spannableString;
        }

        @Override
        public int getCount() {
            return 4;
        }
    }

    public static class MyFragment extends Fragment {


        public static MyFragment getInstance(int position) {
            MyFragment myFragment = new MyFragment();
            Bundle args = new Bundle();
            args.putInt("position", position);
            myFragment.setArguments(args);
            return myFragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View layout = inflater.inflate(R.layout.fragment_my, container, false);
            Bundle bundle = getArguments();

            if (bundle != null) {
                FragmentManager fragmentManager = getFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            }
            return layout;
        }
    }

    public void ChangeToBlue(View view) {
        findViewById(R.id.main_menu_toolbar).setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        findViewById(R.id.tabContainer).setBackground(getResources().getDrawable(R.drawable.custom_bg));
    }

    public void ChangeToPink(View view) {
        findViewById(R.id.main_menu_toolbar).setBackgroundColor(getResources().getColor(R.color.my_pink));
        findViewById(R.id.tabContainer).setBackground(getResources().getDrawable(R.drawable.custom_pink));
    }

    public void ChangeToOrange(View view) {
        findViewById(R.id.main_menu_toolbar).setBackgroundColor(getResources().getColor(R.color.my_orange));
        findViewById(R.id.tabContainer).setBackground(getResources().getDrawable(R.drawable.custom_orange));
    }

    public void OpenCamera(View view) {

        Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file = getFile();
        camera_intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        startActivityForResult(camera_intent, CAM_REQUEST);

//        imageView.buildDrawingCache();
//        Bitmap image= imageView.getDrawingCache();
//
//        Bundle extras = new Bundle();
//        extras.putParcelable("imagebitmap", image);
//        intent.putExtras(extras);
//        startActivity(intent);

        //startActivity(new Intent(this, CameraActivity.class));


    }


    public void AddNewPatient(View view) {
        startActivity(new Intent(this, CreatePatientActivity.class));
    }

    private File getFile()
    {
        File folder = new File("sdcard/camera_app");

        if(!folder.exists())
        {
            folder.mkdir();
        }

        File image_file = new File(folder, "cam_image.jpg");

        return image_file;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        File file = getFile();
        String path = "sdcard/camera_app/cam_image.jpg";
        //imageView.setImageDrawable(Drawable.createFromPath(path));
        Intent intent = new Intent(this, PlotterActivity.class);
        intent.putExtra(path, Uri.fromFile(file));
        startActivity(intent);

    }
}
