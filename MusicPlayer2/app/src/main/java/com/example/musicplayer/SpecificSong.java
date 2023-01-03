package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpecificSong extends AppCompatActivity {
    private static final String TAG = "SpecificSong";
    ImageView play, prev, next, imageView, back;
    TextView songTitle, songArtist;
    List<String> artists = new ArrayList<>();
    int currentIndex = 0;
    String eachLine = null;
    List<String> titles = new ArrayList<>();
    ArrayList<Integer> images = new ArrayList<>();
    final ArrayList<Integer> songs = new ArrayList();
    boolean isConnected = false;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_song);

        //to display the interface of the selected song
        Intent mIntent = getIntent();
        currentIndex = mIntent.getIntExtra("index", 0);

        intent = new Intent(getApplicationContext(), MyService.class);

        play = findViewById(R.id.play);
        prev = findViewById(R.id.prev);
        next = findViewById(R.id.next);
        back = findViewById(R.id.back);
        songTitle = findViewById(R.id.songTitle);
        songArtist = findViewById(R.id.songArtist);
        imageView = findViewById(R.id.imageView);

        //add songs to the list
        songs.add(0, R.raw.everything_black);
        songs.add(1, R.raw.lean_on_me);
        songs.add(2, R.raw.thunder);
        songs.add(3, R.raw.where_we_are);
        songs.add(4, R.raw.you);
        songs.add(5, R.raw.feel_so_close);
        songs.add(6, R.raw.dont);
        songs.add(7, R.raw.remember_the_name);
        songs.add(8, R.raw.snap);

        //add images to the list
        images.add(0, R.drawable.everything_black);
        images.add(1, R.drawable.lean_on_me);
        images.add(2, R.drawable.thunder);
        images.add(3, R.drawable.where_we_are);
        images.add(4, R.drawable.you);
        images.add(5, R.drawable.feel_so_close);
        images.add(6, R.drawable.dont);
        images.add(7, R.drawable.remember_the_name);
        images.add(8, R.drawable.snap);

        //read artists from txt file
        InputStream inputStream = getResources().openRawResource(R.raw.artists);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            eachLine = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (eachLine != null) {
            artists = Arrays.asList(eachLine.split(","));
            try {
                eachLine = bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //read titles from txt file
        InputStream inputStreamTitle = getResources().openRawResource(R.raw.titles);
        BufferedReader bufferedReaderTitle = new BufferedReader(new InputStreamReader(inputStreamTitle));
        try {
            eachLine = bufferedReaderTitle.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (eachLine != null) {
            titles = Arrays.asList(eachLine.split(","));
            try {
                eachLine = bufferedReaderTitle.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        songNames();

        //return to the second activity
        back.setOnClickListener(v -> {
            Intent intent1 = new Intent(getApplicationContext(), MainActivity2.class);
            startActivity(intent1);
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isConnected == true) {
                    stopService(intent);
                    isConnected = false;
                    Log.i(TAG, "Stopped the previous song");
                    Toast toast = Toast.makeText(getApplicationContext(), "You just stopped the song!", Toast.LENGTH_SHORT);
                    toast.show();
                    play.setImageResource(R.drawable.icon_play);
                } else {
                    intent.putExtra("song", songs.get(currentIndex));
                    startService(intent);
                    isConnected = true;
                    Log.i(TAG, "Play current");
                    Toast toast = Toast.makeText(getApplicationContext(), "The song is playing in background!", Toast.LENGTH_SHORT);
                    toast.show();
                    play.setImageResource(R.drawable.icon_pause);
                }
                songNames();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play.setImageResource(R.drawable.icon_pause);
                if (currentIndex < songs.size() - 1) {
                    currentIndex++;
                } else {
                    currentIndex = 0;
                }
                if (isConnected == true) {
                    stopService(intent);
                    isConnected = false;
                }
                intent.putExtra("song", songs.get(currentIndex));
                startService(intent);
                isConnected = true;
                songNames();
            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play.setImageResource(R.drawable.icon_pause);
                if (currentIndex > 0) {
                    currentIndex--;
                } else {
                    currentIndex = songs.size() - 1;
                }
                if (isConnected == true) {
                    stopService(intent);
                    isConnected = false;
                }
                intent.putExtra("song", songs.get(currentIndex));
                startService(intent);
                isConnected = true;
                songNames();
            }
        });
    }

    private void songNames() {
        if (currentIndex == 0) {
            songTitle.setText(titles.get(currentIndex));
            songArtist.setText(artists.get(currentIndex));
            imageView.setImageResource(images.get(currentIndex));
        }
        if (currentIndex == 1) {
            songTitle.setText(titles.get(currentIndex));
            songArtist.setText(artists.get(currentIndex));
            imageView.setImageResource(images.get(currentIndex));
        }
        if (currentIndex == 2) {
            songTitle.setText(titles.get(currentIndex));
            songArtist.setText(artists.get(currentIndex));
            imageView.setImageResource(images.get(currentIndex));
        }
        if (currentIndex == 3) {
            songTitle.setText(titles.get(currentIndex));
            songArtist.setText(artists.get(currentIndex));
            imageView.setImageResource(images.get(currentIndex));
        }
        if (currentIndex == 4) {
            songTitle.setText(titles.get(currentIndex));
            songArtist.setText(artists.get(currentIndex));
            imageView.setImageResource(images.get(currentIndex));
        }
        if (currentIndex == 5) {
            songTitle.setText(titles.get(currentIndex));
            songArtist.setText(artists.get(currentIndex));
            imageView.setImageResource(images.get(currentIndex));
        }
        if (currentIndex == 6) {
            songTitle.setText(titles.get(currentIndex));
            songArtist.setText(artists.get(currentIndex));
            imageView.setImageResource(images.get(currentIndex));
        }
        if (currentIndex == 7) {
            songTitle.setText(titles.get(currentIndex));
            songArtist.setText(artists.get(currentIndex));
            imageView.setImageResource(images.get(currentIndex));
        }
        if (currentIndex == 8) {
            songTitle.setText(titles.get(currentIndex));
            songArtist.setText(artists.get(currentIndex));
            imageView.setImageResource(images.get(currentIndex));
        }
    }
}