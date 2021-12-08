package com.project.uconverter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.NavHostController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Setting up the toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(""); //To remove the title that is set by default to the app name
        setSupportActionBar(toolbar);
        //Set the navcontroller var (responsible for navigation)
        navController = Navigation.findNavController(MainActivity.this, R.id.nav_host_fragment);
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

        //Navigate to the corresponding view
         if(selectedItemId == R.id.action_about) {
            navController.navigate(R.id.aboutFragment);
        } else if(selectedItemId == R.id.action_share) {
            navController.navigate(R.id.inviteFragment);
        } else if(selectedItemId == R.id.action_feedback) {
            navController.navigate(R.id.feedbackFragment);
        }

        return super.onOptionsItemSelected(item);
    }
}