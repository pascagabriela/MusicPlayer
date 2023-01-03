package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity2 extends AppCompatActivity {

    private ArrayList<Song> songsList;
    private RecyclerView recyclerViewMain;
    recyclerViewAdapter adapter;
    List<String> titles = new ArrayList<>();
    List<String> artists = new ArrayList<>();
    List<Integer> songsImage = new ArrayList<>();
    ArrayList<Integer> images = new ArrayList<>();
    String eachLine = null;
    private recyclerViewAdapter.RecyclerViewClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Field[] fields=R.drawable.class.getFields();
        for(int count=0; count < fields.length; count++){
            songsImage.add(getResources().getIdentifier(fields[count].getName(), "drawable", getApplicationContext().getPackageName()));
        }

        images.add(0, R.drawable.everything_black);
        images.add(1, R.drawable.lean_on_me);
        images.add(2, R.drawable.thunder);
        images.add(3, R.drawable.where_we_are);
        images.add(4, R.drawable.you);
        images.add(5, R.drawable.feel_so_close);
        images.add(6, R.drawable.dont);
        images.add(7, R.drawable.remember_the_name);
        images.add(8, R.drawable.snap);

        InputStream inputStream = getResources().openRawResource(R.raw.artists);
        BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(inputStream));
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

        recyclerViewMain = findViewById(R.id.recyclerViewMain);
        songsList =  new ArrayList<>();
        setSongInfo();
        setAdapter();
        adapter = new recyclerViewAdapter(songsList,listener);

    }

    private void setAdapter(){
        setOnClickListener();
        recyclerViewAdapter adapter = new recyclerViewAdapter(songsList, listener);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewMain.setLayoutManager(layoutManager);
        recyclerViewMain.setItemAnimator(new DefaultItemAnimator());
        recyclerViewMain.setAdapter(adapter);
    }

    private void setOnClickListener(){
        listener = new recyclerViewAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getApplicationContext(), SpecificSong.class);
                intent.putExtra("index", position);
                intent.putExtra("connected", true);
                startActivity(intent);
            }
        };
    }

    private  void setSongInfo(){
        for(int i=0; i<titles.size(); i++){
            songsList.add(new Song(titles.get(i), artists.get(i), images.get(i)));
        }
    }
}
