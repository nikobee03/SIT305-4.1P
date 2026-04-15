package com.example.pep;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.bottomnavigation.BottomNavigationView;

// MainActivity is the entry point of the app.
// It hosts fragments and manages navigation between them.
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Called when the activity is first created
        super.onCreate(savedInstanceState);

        // Sets the UI layout for this activity
        // This layout contains the NavHostFragment and BottomNavigationView
        setContentView(R.layout.activity_main);

        // Retrieve the NavHostFragment from the layout
        // NavHostFragment acts as a container that swaps fragments in/out
        NavHostFragment navHost =
                (NavHostFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.nav_host_fragment);

        // Get reference to the Bottom Navigation bar from the layout
        BottomNavigationView navView = findViewById(R.id.bottom_nav);

        // Connect the Bottom Navigation with the Navigation Controller
        // This allows tapping menu items to automatically switch fragments
        NavigationUI.setupWithNavController(navView, navHost.getNavController());
    }
}