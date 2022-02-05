package com.application.musicdatabaseapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.application.musicdatabaseapp.db.DatabaseHelper;
import com.application.musicdatabaseapp.models.MovieSongModel;
import com.google.gson.Gson;

public class EditMovieSongActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;

    private EditText movieId, artistId, movieSongName, songs, duration;
    private Button editMovieSong, deleteMovieSong;
    private MovieSongModel movieSongModel = null;
    public static final String TAG = "ABCD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_movie_song);

        initialize();

        if (getIntent() != null){
            if (getIntent().hasExtra("movie_song")){
                String data = getIntent().getStringExtra("movie_song");
                Gson gson = new Gson();
                movieSongModel = gson.fromJson(data, MovieSongModel.class);
            }
        }

        databaseHelper = new DatabaseHelper(this);

        if (movieSongModel != null){
            artistId.setText(movieSongModel.getArt_id());
            movieId.setText(movieSongModel.getMov_id());
            movieSongName.setText(movieSongModel.getName());
            songs.setText(Integer.toString(movieSongModel.getNo_of_songs()));
            duration.setText(Float.toString(movieSongModel.getDuration()));
        }

        editMovieSong.setOnClickListener(v -> {
            editMovieSongToDB();
        });

        deleteMovieSong.setOnClickListener(v -> {
            deleteMovieSongFromDB();
        });

        findViewById(R.id.backBtn).setOnClickListener(v -> {
            onBackPressed();
        });

    }

    private void editMovieSongToDB() {
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

        int result = databaseHelper.updateMovieSong(
                id,
                artistId.getText().toString().trim(),
                movieSongName.getText().toString().trim(),
                dura,
                numSongs
        );

        if (result == 1){
            startActivity(new Intent(EditMovieSongActivity.this, MainActivity.class));
            finish();
        }
        else{
            Toast.makeText(this, "Failed to update movie song", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteMovieSongFromDB() {
        String id = movieId.getText().toString().trim();

        if (TextUtils.isEmpty(id)){
            movieId.setError("Enter Movie id");
            movieId.requestFocus();
            return;
        }

        databaseHelper.deleteMovieSong(id);

        startActivity(new Intent(EditMovieSongActivity.this, MainActivity.class));
        finish();
    }

    private void initialize() {
        artistId = findViewById(R.id.artistIdTxt);
        movieId = findViewById(R.id.movieIdTxt);
        movieSongName = findViewById(R.id.movieSongNameTxt);
        songs = findViewById(R.id.NoSongsTxt);
        duration = findViewById(R.id.DurationTxt);
        editMovieSong = findViewById(R.id.editMovieSongBtn);
        deleteMovieSong = findViewById(R.id.deleteMovieSongBtn);
    }
}