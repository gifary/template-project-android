package com.gifary.commontemplate.app;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.gifary.commontemplate.BaseActivity;
import com.gifary.commontemplate.R;
import com.gifary.commontemplate.app.account.AccountFragment;
import com.gifary.commontemplate.app.home.HomeFragment;
import com.gifary.commontemplate.app.notification.NotificationFragment;
import com.gifary.commontemplate.interfaces.OnFragmentInteractionListener;

public class MainActivity extends BaseActivity implements OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        activity =MainActivity.this;
        checkRunTimePermission();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        homeFragment();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            Class fragmentClass = null;
            Bundle args = new Bundle();
            item.setChecked(true);
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragmentClass = HomeFragment.class;
                    break;
                case R.id.navigation_notifications:
                    fragmentClass = NotificationFragment.class;
                    break;
                case R.id.navigation_my_account:
                    fragmentClass = AccountFragment.class;
                    break;
            }

            if(fragmentClass!=null){
                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                    fragment.setArguments(args);
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.fragment_layout_for_activity_navigation, fragment).commit();
                }catch (Exception e){
                    Log.e(MainActivity.class.getSimpleName(),e.toString());
                    e.printStackTrace();
                }
            }

            return false;
        }
    };

    public void homeFragment(){
        try {
            Bundle args = new Bundle();
            Class fragmentClass =HomeFragment.class;
            args.putString("route","Welcome");
            if(getIntent().getAction()!=null){
                args.putInt("position",3);
            }else{
                args.putInt("position",0);
            }

            if(getIntent().hasExtra("position")){
                args.putInt("position",getIntent().getExtras().getInt("position"));
            }
            Fragment fragment = (Fragment) fragmentClass.newInstance();
            fragment.setArguments(args);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_layout_for_activity_navigation, fragment).commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFragmentMessage(int TAG, String data) {

    }
}
