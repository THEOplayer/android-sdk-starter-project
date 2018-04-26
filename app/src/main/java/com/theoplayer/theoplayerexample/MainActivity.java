package com.theoplayer.theoplayerexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.theoplayer.android.api.THEOplayerView;
import com.theoplayer.android.api.source.SourceDescription;
import com.theoplayer.android.api.source.SourceType;
import com.theoplayer.android.api.source.TypedSource;

public class MainActivity extends AppCompatActivity {

    THEOplayerView theoPlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        theoPlayerView = findViewById(R.id.theoplayer);

        TypedSource typedSource = TypedSource.Builder
                .typedSource()
                .src("https://cdn.theoplayer.com/video/dash/big_buck_bunny/BigBuckBunny_10s_simple_2014_05_09.mpd")
                .type(SourceType.DASH)
                .build();

        SourceDescription sourceDescription = SourceDescription.Builder
                .sourceDescription(typedSource)
                .build();

        theoPlayerView.getPlayer().setSource(sourceDescription);
    }
}
