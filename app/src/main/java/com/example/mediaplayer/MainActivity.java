package com.example.mediaplayer;


import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


//    File f = new File("/home/userone/AndroidStudioProjects/MediaPlayer/app/src/main/res/raw/ShabadList");
//      File[] array_of_shabads = f.listFiles();
//     int x=0;
//   Map<Integer, File> list = new HashMap<Integer, File>();


    Button btn_play;
    Button btn_pause ;
    Button btn_resume;
    Button btn_stop ;
    MediaPlayer mediaPlayer;
    Handler handler;
    Context c ;
    Activity activity ;
    Runnable runnable;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_play= findViewById(R.id.play);
        btn_pause = findViewById(R.id.pause);
        btn_resume =findViewById(R.id.resume);
        btn_stop = findViewById(R.id.stop);

        handler = new Handler();
        c = getApplicationContext();
        activity = MainActivity.this;
        url = "https://rssb.org/audio/shabads/Shabads_Vol-3/Aaj%20Divas%20Leoon%20Balihaara.mp3"; // your URL here




    }


     public void  playMusic(View view) throws IOException {

        int x=0;
        try {
          //  mediaPlayer = MediaPlayer.create(this, R.raw.shabad);


            MediaPlayer mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioAttributes(
                    new AudioAttributes.Builder()
                            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                            .setUsage(AudioAttributes.USAGE_MEDIA)
                            .build()
            );
           // mediaPlayer = MediaPlayer.create(this, Uri.parse(url));
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepareAsync();
            // might take long! (for buffering, etc)

            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mediaPlayer.start();
                }
            });
            //mediaPlayer.start();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        // mediaPlayer.start();

         btn_play.setEnabled(false);
         btn_resume.setEnabled(false);
         btn_pause.setEnabled(true);
         btn_stop.setEnabled(true);
     }

     public void pauseMusic (View view)
     {
//         if(mediaPlayer!=null && mediaPlayer.isPlaying())
//             mediaPlayer.pause();
//             btn_pause.setEnabled(false);
//             btn_stop.setEnabled(false);
//             btn_play.setEnabled(false);
//             btn_resume.setEnabled(true);
//             btn_play.setEnabled(true);


         try{
             if (mediaPlayer!=null && this.mediaPlayer.isPlaying())
                 this.mediaPlayer.pause();

             btn_pause.setEnabled(false);
             btn_stop.setEnabled(false);
             btn_play.setEnabled(false);
             btn_resume.setEnabled(true);
             btn_play.setEnabled(true);
         }
                     catch(Exception e)
             {
                 e.printStackTrace();
             }

// mediaPlayer.start();


         System.out.println("Hey there its already paused!!");




     }

     public void resumeMusic(View view)
     {
         if(mediaPlayer!=null)
         {
             mediaPlayer.start();
             btn_resume.setEnabled(false);
             btn_play.setEnabled(false);
             btn_stop.setEnabled(true);
             btn_pause.setEnabled(true);
         }
     }

     public void stopMusic (View view) {
         if (mediaPlayer != null) {
             mediaPlayer.stop();
             mediaPlayer.release();
             mediaPlayer = null;

             if (handler != null) {
                 handler.removeCallbacks(runnable);
             }

             btn_pause.setEnabled(false);
             btn_stop.setEnabled(false);
             btn_resume.setEnabled(false);
             btn_play.setEnabled(true);
         }


     }

//    // public void nextMusic(View view)
//     {
//         mediaPlayer = MediaPlayer.create(this,R.raw.);
//     }
}