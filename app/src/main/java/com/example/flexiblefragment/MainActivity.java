package com.example.flexiblefragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // add android home fragment
        FragmentManager mFragmentManager= getSupportFragmentManager();
        FragmentTransaction mFragmentTransaction= mFragmentManager.beginTransaction();
        HomeFragment mHomeFragment= new HomeFragment();

        Fragment fragment= mFragmentManager.findFragmentByTag(HomeFragment.class.getSimpleName());
        if(!(fragment instanceof  HomeFragment)){
            // menambah fragment
            mFragmentTransaction.add(R.id.frame_container, mHomeFragment, HomeFragment.class.getSimpleName());
            mFragmentTransaction.commit();
        }
    }
}
