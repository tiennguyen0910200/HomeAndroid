package com.example.fragments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomnav = findViewById(R.id.bottomnavigation);
        bottomnav.setOnNavigationItemSelectedListener(navListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFrament = null;

                    switch (item.getItemId()){
                        case R.id.home:
                            selectedFrament = new HomeFrament();
                            break;
                        case R.id.favorites:
                            selectedFrament = new FavoritesFrament();
                            break;
                        case R.id.search:
                            selectedFrament = new SearchFrament();
                            break;

                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.frament_container, selectedFrament).commit();
                    return true;
                }
            };


}