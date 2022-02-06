package com.application.musicdatabaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.application.musicdatabaseapp.db.DatabaseHelper;

public class AddPlaylistActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;

    private EditText playlistId, userId, playlistName, songs, duration;
    private Button addPlaylist;
    public static final String TAG = "ABCD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_playlist);

        initialize();

        databaseHelper = new DatabaseHelper(this);

        addPlaylist.setOnClickListener(v -> {
            addPlaylistToDB();
        });

        findViewById(R.id.backBtn).setOnClickListener(v -> {
            onBackPressed();
        });
    }

    private void addPlaylistToDB() {
        String id = playlistId.getText().toString().trim();

        if (TextUtils.isEmpty(id)){
            playlistId.setError("Enter Playlist id");
            playlistId.requestFocus();
            return;
        }

        String so = songs.getText().toString().trim();
        int numSong = 0;
        if (so != null){
            if (so.length() > 0){
                numSong = Integer.parseInt(so);
            }
        }

        String dur = duration.getText().toString().trim();
        float numDur = 0;
        if (dur != null){
            if (dur.length() > 0){
                numDur = Float.parseFloat(dur);
            }
        }

        boolean result = databaseHelper.insertPlaylist(
                id,
                userId.getText().toString().trim(),
                playlistName.getText().toString().trim(),
                numSong,
                numDur
        );

        if (result){
            startActivity(new Intent(AddPlaylistActivity.this, MainActivity.class));
            finish();
        }
        else{
            Toast.makeText(this, "Failed to add playlist", Toast.LENGTH_SHORT).show();
        }
    }

    private void initialize() {
        playlistId = findViewById(R.id.playlistIdTxt);
        userId = findViewById(R.id.userIdTxt);
        playlistName = findViewById(R.id.playlistNameTxt);
        playlistName = findViewById(R.id.playlistNameTxt);
        songs = findViewById(R.id.NoSongsTxt);
        duration = findViewById(R.id.DurationTxt);
        addPlaylist = findViewById(R.id.addPlaylistBtn);
    }
}