package com.example.emran.youtubelikeapp;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

public class MainActivity extends AppCompatActivity {

    private Activity mActivity;

    /*for youtube*/
    private static final int RECOVERY_REQUEST = 1;
    public static final String YOUTUBE_API_KEY= "J2peEejD8aCDpVUAIzaSyADd_jmgrYvmOUB0p3z";//AIzaSyADd_jmgrYvmOUB0p3zJ2peEejD8aCDpVU

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initialView();
        setYoutubeVideo();
    }

    private void initialView() {
        setContentView(R.layout.activity_main);
        mActivity = this;
    }

    /*########################################## YOUTUBE #########################################*/
    private void setYoutubeVideo() {
        YouTubePlayerSupportFragment youTubePlayerFragment = YouTubePlayerSupportFragment.newInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frgUtbDtlsVideo, youTubePlayerFragment).commit();
        youTubePlayerFragment.initialize(YOUTUBE_API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
                if (!wasRestored) {
                    youTubePlayer.cueVideo("eZZie3zs1mE"); // Plays https://www.youtube.com/watch?v=eZZie3zs1mE
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult errorReason) {
                if (errorReason.isUserRecoverableError()) {
                    errorReason.getErrorDialog(mActivity, RECOVERY_REQUEST).show();
                } else {
                    //String error = String.format(getString(R.string.player_error), errorReason.toString());
                    //Toast.makeText(mContext, error, Toast.LENGTH_LONG).show();
                    Log.d("test","youtube error: "+errorReason.toString());
                }
            }
        });
    }
    /*--------------------------------------------------------------------------------------------*/
}
