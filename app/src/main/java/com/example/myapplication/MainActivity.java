package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;

import com.example.myapplication.databinding.ActivityMainBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding root;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        root  = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(root.getRoot());

        navController = Navigation.findNavController(MainActivity.this ,
                R.id.nav_host_fragment);

        root.navView.setOnNavigationItemSelectedListener(item -> {

            switch (item.getItemId())
            {
                case R.id.homeFragment:
                    navController.navigate(R.id.homeFragment);
                    return true;
                case R.id.searchFragment:
                    navController.navigate(R.id.searchFragment);
                    return true;
                case R.id.myListFragment:
                    navController.navigate(R.id.myListFragment);
                    return true;
                case R.id.profileFragment:
                    navController.navigate(R.id.profileFragment);
                    return true;
            }
            return false;

        });


    }
}