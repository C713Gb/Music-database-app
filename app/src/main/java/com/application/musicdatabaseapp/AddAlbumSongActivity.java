package com.application.musicdatabaseapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.application.musicdatabaseapp.db.DatabaseHelper;

public class AddAlbumSongActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;

    private EditText albumId, artistId, albumSongName, songs, duration;
    private Button addAlbumSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_album_song);

        initialize();

        databaseHelper = new DatabaseHelper(this);

        addAlbumSong.setOnClickListener(v -> {
            addAlbumSongToDB();
        });

        findViewById(R.id.backBtn).setOnClickListener(v -> {
            onBackPressed();
        });

    }

    private void addAlbumSongToDB() {
        String id = albumId.getText().toString().trim();

        if (TextUtils.isEmpty(id)){
            albumId.setError("Enter Album id");
            albumId.requestFocus();
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

        boolean result = databaseHelper.insertAlbumSong(
                id,
                artistId.getText().toString().trim(),
                albumSongName.getText().toString().trim(),
                numSongs,
                dura
        );

        if (result){
            startActivity(new Intent(AddAlbumSongActivity.this, MainActivity.class));
            finish();
        }
        else{
            Toast.makeText(this, "Failed to add album song", Toast.LENGTH_SHORT).show();
        }
    }

    private void initialize() {
        artistId = findViewById(R.id.artistIdTxt);
        albumId = findViewById(R.id.albumIdTxt);
        albumSongName = findViewById(R.id.albumSongNameTxt);
        songs = findViewById(R.id.NoSongsTxt);
        duration = findViewById(R.id.DurationTxt);
        addAlbumSong = findViewById(R.id.addAlbumSongBtn);
    }
}