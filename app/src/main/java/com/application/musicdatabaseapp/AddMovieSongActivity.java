package com.application.musicdatabaseapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.application.musicdatabaseapp.db.DatabaseHelper;

public class AddMovieSongActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;

    private EditText movieId, artistId, movieSongName, songs, duration;
    private Button addMovieSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie_song);

        initialize();

        databaseHelper = new DatabaseHelper(this);

        addMovieSong.setOnClickListener(v -> {
            addMovieSongToDB();
        });

        findViewById(R.id.backBtn).setOnClickListener(v -> {
            onBackPressed();
        });

    }

    private void addMovieSongToDB() {
        String id = movieId.getText().toString().trim();

        if (TextUtils.isEmpty(id)){
            movieId.setError("Enter Movie id");
            movieId.requestFocus();
            return;
        }

        String songsStr = songs.getText().toString().trim();
        int numSongs = 0;
        if (songsStr != null){
            if (songsStr.length() > 0){
                numSongs = Integer.parseInt(songsStr);
            }
        }

        String dur = duration.getText().toString().trim();
        float dura = 0;
        if (dur != null){
            if (dur.length() > 0){
                dura = Float.parseFloat(dur);
            }
        }

        boolean result = databaseHelper.insertMovieSong(
                id,
                artistId.getText().toString().trim(),
                movieSongName.getText().toString().trim(),
                numSongs,
                dura
        );

        if (result){
            startActivity(new Intent(AddMovieSongActivity.this, MainActivity.class));
            finish();
        }
        else{
            Toast.makeText(this, "Failed to add movie song", Toast.LENGTH_SHORT).show();
        }
    }

    private void initialize() {
        artistId = findViewById(R.id.artistIdTxt);
        movieId = findViewById(R.id.movieIdTxt);
        movieSongName = findViewById(R.id.movieSongNameTxt);
        songs = findViewById(R.id.NoSongsTxt);
        duration = findViewById(R.id.DurationTxt);
        addMovieSong = findViewById(R.id.addMovieSongBtn);
    }
}