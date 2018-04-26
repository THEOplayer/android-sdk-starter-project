package com.theoplayer.theoplayerexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.theoplayer.android.api.THEOplayerView;

public class MainActivity extends AppCompatActivity {

    THEOplayerView theoPlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        theoPlayerView = findViewById(R.id.theoplayer);

    }
}
