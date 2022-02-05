package com.application.musicdatabaseapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.application.musicdatabaseapp.db.DatabaseHelper;
import com.application.musicdatabaseapp.models.AlbumSongModel;
import com.application.musicdatabaseapp.models.MovieSongModel;
import com.google.gson.Gson;

public class EditAlbumSongActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;

    private EditText albumId, artistId, albumSongName, songs, duration;
    private Button editAlbumSong, deleteAlbumSong;
    private AlbumSongModel albumSongModel = null;
    public static final String TAG = "ABCD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_album_song);

        initialize();

        if (getIntent() != null){
            if (getIntent().hasExtra("album_song")){
                String data = getIntent().getStringExtra("album_song");
                Gson gson = new Gson();
                albumSongModel = gson.fromJson(data, AlbumSongModel.class);
            }
        }

        databaseHelper = new DatabaseHelper(this);

        if (albumSongModel != null){
            artistId.setText(albumSongModel.getArt_id());
            albumId.setText(albumSongModel.getAlb_id());
            albumSongName.setText(albumSongModel.getName());
            songs.setText(Integer.toString(albumSongModel.getNo_of_songs()));
            duration.setText(Float.toString(albumSongModel.getDuration()));
        }

        editAlbumSong.setOnClickListener(v -> {
            editAlbumSongToDB();
        });

        deleteAlbumSong.setOnClickListener(v -> {
            deleteAlbumSongFromDB();
        });

        findViewById(R.id.backBtn).setOnClickListener(v -> {
            onBackPressed();
        });

    }

    private void editAlbumSongToDB() {
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

        int result = databaseHelper.updateAlbumSong(
                id,
                artistId.getText().toString().trim(),
                albumSongName.getText().toString().trim(),
                dura,
                numSongs
        );

        if (result == 1){
            startActivity(new Intent(EditAlbumSongActivity.this, MainActivity.class));
            finish();
        }
        else{
            Toast.makeText(this, "Failed to update album song", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteAlbumSongFromDB() {
        String id = albumId.getText().toString().trim();

        if (TextUtils.isEmpty(id)){
            albumId.setError("Enter Album id");
            albumId.requestFocus();
            return;
        }

        databaseHelper.deleteAlbumSong(id);

        startActivity(new Intent(EditAlbumSongActivity.this, MainActivity.class));
        finish();
    }

    private void initialize() {
        artistId = findViewById(R.id.artistIdTxt);
        albumId = findViewById(R.id.albumIdTxt);
        albumSongName = findViewById(R.id.albumSongNameTxt);
        songs = findViewById(R.id.NoSongsTxt);
        duration = findViewById(R.id.DurationTxt);
        editAlbumSong = findViewById(R.id.editAlbumSongBtn);
        deleteAlbumSong = findViewById(R.id.deleteAlbumSongBtn);
    }
}