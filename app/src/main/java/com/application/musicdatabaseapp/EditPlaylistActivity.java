package com.application.musicdatabaseapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.application.musicdatabaseapp.db.DatabaseHelper;
import com.application.musicdatabaseapp.models.PlaylistModel;
import com.google.gson.Gson;

public class EditPlaylistActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;

    private EditText playlistId, userId, playlistName, songs, duration;
    private Button editPlaylist, deletePlaylist;
    public static final String TAG = "ABCD";
    private PlaylistModel playlistModel = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_playlist);

        initialize();

        if (getIntent() != null){
            if (getIntent().hasExtra("playlist")){
                String data = getIntent().getStringExtra("playlist");
                Gson gson = new Gson();
                playlistModel = gson.fromJson(data, PlaylistModel.class);
            }
        }

        databaseHelper = new DatabaseHelper(this);

        if (playlistModel != null){
            playlistId.setText(playlistModel.getPlaylist_id());
            userId.setText(playlistModel.getUser_id());
            playlistName.setText(playlistModel.getName());
            songs.setText(Integer.toString(playlistModel.getNo_of_songs()));
            duration.setText(Float.toString(playlistModel.getDuration()));
        }

        editPlaylist.setOnClickListener(v -> {
            editPlaylistToDB();
        });

        deletePlaylist.setOnClickListener(v -> {
            deletePlaylistFromDB();
        });

        findViewById(R.id.backBtn).setOnClickListener(v -> {
            onBackPressed();
        });
    }

    private void deletePlaylistFromDB() {
        String id = playlistId.getText().toString().trim();

        if (TextUtils.isEmpty(id)){
            playlistId.setError("Enter Playlist id");
            playlistId.requestFocus();
            return;
        }

        databaseHelper.deletePlaylist(id);

        startActivity(new Intent(EditPlaylistActivity.this, MainActivity.class));
        finish();
    }

    private void editPlaylistToDB() {
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

        int result = databaseHelper.updatePlaylist(
                id,
                userId.getText().toString().trim(),
                playlistName.getText().toString().trim(),
                numSong,
                numDur
        );

        if (result == 1){
            startActivity(new Intent(EditPlaylistActivity.this, MainActivity.class));
            finish();
        }
        else{
            Toast.makeText(this, "Failed to update playlist", Toast.LENGTH_SHORT).show();
        }
    }

    private void initialize() {
        playlistId = findViewById(R.id.playlistIdTxt);
        userId = findViewById(R.id.userIdTxt);
        playlistName = findViewById(R.id.playlistNameTxt);
        playlistName = findViewById(R.id.playlistNameTxt);
        songs = findViewById(R.id.NoSongsTxt);
        duration = findViewById(R.id.DurationTxt);
        editPlaylist = findViewById(R.id.editPlaylistBtn);
        deletePlaylist = findViewById(R.id.deletePlaylistBtn);
    }
}