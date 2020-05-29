package com.example.photoeditor;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences("PRES_FILE", 0);
        MainActivityFragment.r = sharedPreferences.getInt("RED", 255);
        MainActivityFragment.g = sharedPreferences.getInt("GREEN", 140);
        MainActivityFragment.b = sharedPreferences.getInt("BLUE", 70);
        MainActivityFragment.strokeWidth = sharedPreferences.getFloat("STROKE_WIDTH", 20f);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        int orientation = getResources().getConfiguration().orientation;

        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            getMenuInflater().inflate(R.menu.menu, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
