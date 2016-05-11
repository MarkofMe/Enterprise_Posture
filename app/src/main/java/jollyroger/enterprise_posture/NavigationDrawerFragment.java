//package jollyroger.enterprise_posture;
//
//
//import android.content.Context;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.support.v4.widget.DrawerLayout;
//import android.support.v7.app.ActionBarDrawerToggle;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.support.v7.widget.Toolbar;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
///**
// * A simple {@link Fragment} subclass.
// */
//public class NavigationDrawerFragment extends Fragment {
//
//    private RecyclerView recyclerView;
//    public static final String PREF_FILE = "pref";
//    public static final String KEY_USER_LEARNED_DRAWER = "user_learned_drawer";
//    private ActionBarDrawerToggle mDrawerToggle;
//    private DrawerLayout mDrawerLayout;
//    private RecyclerAdapter adapter;
//    private boolean mUserLearnedDrawer;
//    private boolean mFromSavedInstanceState;
//    private View containerView;
//
//    public NavigationDrawerFragment() {
//        // Required empty public constructor
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        mUserLearnedDrawer = Boolean.valueOf(readFromPreference(getActivity(), KEY_USER_LEARNED_DRAWER, "false"));
//        if (savedInstanceState != null) {
//            mFromSavedInstanceState = true;
//        }
//
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View layout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
//        recyclerView = (RecyclerView) layout.findViewById(R.id.drawerList);
//
//        List<RecyclerButton> data = new ArrayList<>();
//        data.add(new RecyclerButton(R.drawable.ic_camera, "Camera"));
//        data.add(new RecyclerButton(R.drawable.ic_database, "Database"));
//        data.add(new RecyclerButton(R.drawable.ic_settings, "Settings"));
//        data.add(new RecyclerButton(R.drawable.ic_logo, "Credits"));
//
//        adapter = new RecyclerAdapter(getActivity(), data); //getActivity()
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        return layout;
//    }
//
//
//    public void setUp(int fragmentId, DrawerLayout drawerLayout, Toolbar toolBar) {
//        containerView = getActivity().findViewById(fragmentId);
//        mDrawerLayout = drawerLayout;
//        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolBar, R.string.drawer_open, R.string.drawer_close) {
//            @Override
//            public void onDrawerOpened(View drawerView) {
//                super.onDrawerOpened(drawerView);
//                if (!mUserLearnedDrawer) {
//                    mUserLearnedDrawer = true;
//                    saveToPreference(getActivity(), KEY_USER_LEARNED_DRAWER, mUserLearnedDrawer + "");
//                }
//                getActivity().invalidateOptionsMenu();
//            }
//
//            @Override
//            public void onDrawerClosed(View drawerView) {
//                super.onDrawerClosed(drawerView);
//                getActivity().invalidateOptionsMenu();
//            }
//
//        };
//        if (!mUserLearnedDrawer && !mFromSavedInstanceState) {
//            mDrawerLayout.openDrawer(containerView);
//        }
//        mDrawerLayout.setDrawerListener(mDrawerToggle);
//
//        mDrawerLayout.post(new Runnable() {
//            @Override
//            public void run() {
//                mDrawerToggle.syncState();
//            }
//        });
//
//    }
//
//    public static void saveToPreference(Context context, String preferenceName, String preferenceValue) {
//        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE, context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString(preferenceName, preferenceValue);
//        editor.commit();
//    }
//
//    public static String readFromPreference(Context context, String preferenceName, String defaultValue) {
//        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE, context.MODE_PRIVATE);
//        return sharedPreferences.getString(preferenceName, defaultValue);
//    }
//}
