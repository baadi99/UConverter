package com.project.uconverter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String currentView = "homepage"; //To keep track of view while navigating
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Setting up the toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(""); //To remove the title that is set by default to the app name
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.appbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here
        int selectedItemId = item.getItemId();

        /*
           check which item was selected and if the selected item doesn't corresponds
           to the current view (ex: item: settings, currView: settings), because if it does
           an exception will be thrown.
        */
        if (selectedItemId == R.id.action_settings && !currentView.equals("settings")) {
            currentView = "settings";
            Navigation.findNavController(MainActivity.this, R.id.nav_host_fragment).navigate(R.id.action_homePageFragment_to_settingsFragment);
        } else if(selectedItemId == R.id.action_about && !currentView.equals("about")) {
            currentView = "about";
            Navigation.findNavController(MainActivity.this, R.id.nav_host_fragment).navigate(R.id.action_homePageFragment_to_aboutFragment);
        } else if(selectedItemId == R.id.action_share && !currentView.equals("share")) {
            currentView = "share";
            Navigation.findNavController(MainActivity.this, R.id.nav_host_fragment).navigate(R.id.action_homePageFragment_to_inviteFragment);
        }

        return super.onOptionsItemSelected(item);
    }
}