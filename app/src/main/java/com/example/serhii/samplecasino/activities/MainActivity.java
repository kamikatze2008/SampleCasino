package com.example.serhii.samplecasino.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.serhii.samplecasino.R;
import com.example.serhii.samplecasino.fragments.box_chooser.BoxChooserFragment;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, new BoxChooserFragment())
                .addToBackStack(BoxChooserFragment.class.getSimpleName())
                .commit();
    }
}
