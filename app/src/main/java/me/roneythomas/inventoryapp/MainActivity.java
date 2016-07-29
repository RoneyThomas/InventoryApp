package me.roneythomas.inventoryapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.contianer_main);
        if (fragment == null) {
            fragment = InventoryListFragment.newInstance();
            manager.beginTransaction().add(R.id.contianer_main, fragment).commit();
        }
        setContentView(R.layout.activity_main);
    }
}
