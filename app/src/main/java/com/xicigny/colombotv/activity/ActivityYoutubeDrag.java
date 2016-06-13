package com.xicigny.colombotv.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.github.pedrovgs.DraggableListener;
import com.github.pedrovgs.DraggablePanel;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.squareup.picasso.Picasso;
import com.xicigny.colombotv.R;
import com.xicigny.colombotv.fragments.EntertainmentFragment;
import com.xicigny.colombotv.fragments.FragmentChat;
import com.xicigny.colombotv.fragments.PopularFragment;
import com.xicigny.colombotv.fragments.ReligiousFragment;
import com.xicigny.colombotv.fragments.TalkShowFragment;
import com.xicigny.colombotv.fragments.YouTubePosterFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class ActivityYoutubeDrag extends FragmentActivity {

    private static final String YOUTUBE_API_KEY = "AIzaSyDqBYg-Wuj5MSzcLa_NfsRMcP52bWPmM6A";

    private String VIDEO_KEY;
    private String VIDEO_POSTER_THUMBNAIL;
    private String VIDEO_POSTER_TITLE;

    private static final String SECOND_VIDEO_POSTER_THUMBNAIL =
            "https://i.ytimg.com/vi/783FSJ9HTY4/maxresdefault.jpg";

    @InjectView(R.id.iv_thumbnail)
    ImageView thumbnailImageView;
    @InjectView(R.id.draggable_panel)
    DraggablePanel draggablePanel;

    private YouTubePlayer youtubePlayer;
    private YouTubePlayerSupportFragment youtubeFragment;

    private String FRAGMENT;

    /**
     * Initialize the Activity with some injected data.
     */
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_drag);
        ButterKnife.inject(this);

        Intent intent = getIntent();

        VIDEO_KEY = intent.getStringExtra("VIDEO_ID");
        VIDEO_POSTER_THUMBNAIL = intent.getStringExtra("THUMB_URL");
        VIDEO_POSTER_TITLE = intent.getStringExtra("TITLE");
        FRAGMENT = intent.getStringExtra("FRAGMENT");

        initializeYoutubeFragment();
        initializeDraggablePanel();
        hookDraggablePanelListeners();

    }

    @OnClick(R.id.iv_thumbnail) void onContainerClicked() {
        draggablePanel.maximize();
    }

    private void initializeYoutubeFragment() {
        youtubeFragment = new YouTubePlayerSupportFragment();
        youtubeFragment.initialize(YOUTUBE_API_KEY, new YouTubePlayer.OnInitializedListener() {

            @Override public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                          YouTubePlayer player, boolean wasRestored) {
                if (!wasRestored) {
                    youtubePlayer = player;
                    youtubePlayer.loadVideo(VIDEO_KEY);
                    youtubePlayer.setShowFullscreenButton(true);
                }
            }

            @Override public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                          YouTubeInitializationResult error) {
            }
        });
    }

    private void initializeDraggablePanel() {
        draggablePanel.setFragmentManager(getSupportFragmentManager());
        draggablePanel.setTopFragment(youtubeFragment);
        FragmentChat fragmentChat = new FragmentChat();
        draggablePanel.setBottomFragment(fragmentChat);
        draggablePanel.initializeView();
        Picasso.with(this)
                .load(SECOND_VIDEO_POSTER_THUMBNAIL)
                .placeholder(R.drawable.colombotv)
                .into(thumbnailImageView);
    }

    private void hookDraggablePanelListeners() {
        draggablePanel.setDraggableListener(new DraggableListener() {
            @Override public void onMaximized() {
                playVideo();
            }

            @Override public void onMinimized() {
                //Empty
            }

            @Override public void onClosedToLeft() {
                pauseVideo();
            }

            @Override public void onClosedToRight() {
                pauseVideo();
            }
        });
    }

    private void pauseVideo() {
        if (youtubePlayer.isPlaying()) {
            youtubePlayer.pause();
        }
    }

    private void playVideo() {
        if (!youtubePlayer.isPlaying()) {
            youtubePlayer.play();
        }
    }
}
