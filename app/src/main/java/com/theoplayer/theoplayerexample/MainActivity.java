package com.theoplayer.theoplayerexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.theoplayer.android.api.THEOplayerGlobal;
import com.theoplayer.android.api.THEOplayerView;
import com.theoplayer.android.api.contentprotection.KeySystemId;
import com.theoplayer.android.api.event.EventListener;
import com.theoplayer.android.api.event.player.PauseEvent;
import com.theoplayer.android.api.event.player.PlayEvent;
import com.theoplayer.android.api.event.player.PlayerEventTypes;
import com.theoplayer.android.api.event.player.TimeUpdateEvent;
import com.theoplayer.android.api.source.SourceDescription;
import com.theoplayer.android.api.source.SourceType;
import com.theoplayer.android.api.source.TypedSource;
import com.theoplayer.android.api.source.drm.DRMConfiguration;

import java.util.HashMap;

import static com.theoplayer.android.api.source.drm.KeySystemConfiguration.Builder.keySystemConfiguration;

public class MainActivity extends AppCompatActivity {

    THEOplayerView theoPlayerView;
    Button btnPlayPause;
    TextView txtPlayStatus, txtTimeUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        theoPlayerView = findViewById(R.id.theoplayer);
        theoPlayerView.getSettings().setFullScreenOrientationCoupled(true);

        THEOplayerGlobal
                .getSharedInstance(getApplicationContext())
                .registerContentProtectionIntegration("my-custom-integration", KeySystemId.WIDEVINE, new CustomWidevineContentProtectionIntegrationFactory());

        /*
         * The DRMConfiguration builder allows configuring some integrationParameters. Use this to
         * pass all data you desire to the ContentProtectionIntegration.
         */
        HashMap<String, Object> customIntegrationParameters = new HashMap<>();
        customIntegrationParameters.put("custom-key", "custom-value");

        TypedSource typedSource = TypedSource.Builder
                .typedSource()
                .src("STREAM URL")
                .type(SourceType.DASH)
                .setNativeRenderingEnabled(true)
                .setNativeUiRenderingEnabled(true)
                .drm(
                        new DRMConfiguration.Builder()
                                .customIntegrationId("my-custom-integration") // Ensure this string matches the one used when registering your ContentProtectionIntegrationFactory.
                                .integrationParameters(customIntegrationParameters) // Will be accessible from the ContentProtectionIntegration.
                                .widevine(
                                        keySystemConfiguration("LICENSE URL")
                                                .build()
                                )
                                .build()
                )
                .build();

        SourceDescription sourceDescription = SourceDescription.Builder
                .sourceDescription(typedSource)
                .build();

        theoPlayerView.getPlayer().setSource(sourceDescription);

        btnPlayPause = findViewById(R.id.btn_playpause);
        btnPlayPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (theoPlayerView.getPlayer().isPaused()) {
                    theoPlayerView.getPlayer().play();
                } else {
                    theoPlayerView.getPlayer().pause();
                }
            }
        });

        txtPlayStatus = findViewById(R.id.txt_playstatus);
        txtTimeUpdate = findViewById(R.id.txt_timeupdate);

        theoPlayerView.getPlayer().addEventListener(PlayerEventTypes.PLAY, new EventListener<PlayEvent>() {
            @Override
            public void handleEvent(PlayEvent playEvent) {
                txtPlayStatus.setText("Playing");
            }
        });

        theoPlayerView.getPlayer().addEventListener(PlayerEventTypes.PAUSE, new EventListener<PauseEvent>() {
            @Override
            public void handleEvent(PauseEvent pauseEvent) {
                txtPlayStatus.setText("Paused");
            }
        });

        theoPlayerView.getPlayer().addEventListener(PlayerEventTypes.TIMEUPDATE, new EventListener<TimeUpdateEvent>() {
            @Override
            public void handleEvent(TimeUpdateEvent timeUpdateEvent) {
                txtTimeUpdate.setText(String.valueOf(timeUpdateEvent.getCurrentTime()));
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        theoPlayerView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        theoPlayerView.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        theoPlayerView.onDestroy();
    }
}
