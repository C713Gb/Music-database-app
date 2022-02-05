package com.application.musicdatabaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.application.musicdatabaseapp.db.DatabaseHelper;
import com.application.musicdatabaseapp.models.ArtistModel;
import com.google.gson.Gson;

public class EditArtistActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;

    private EditText artistId, artistName, artistAge, artistSex, artistLanguage, songsComposed;
    private Button editArtist, deleteArtist;
    private ArtistModel artistModel = null;
    public static final String TAG = "ABCD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_artist);

        initialize();

        if (getIntent() != null){
            if (getIntent().hasExtra("artist")){
                String data = getIntent().getStringExtra("artist");
                Gson gson = new Gson();
                artistModel = gson.fromJson(data, ArtistModel.class);
            }
        }

        databaseHelper = new DatabaseHelper(this);

        if (artistModel != null){
            artistId.setText(artistModel.getArt_id());
            artistName.setText(artistModel.getName());
            artistAge.setText(Integer.toString(artistModel.getAge()));
            artistSex.setText(artistModel.getSex());
            artistLanguage.setText(artistModel.getLanguage());
            songsComposed.setText(Integer.toString(artistModel.getNo_of_songs_composed()));
        }

        editArtist.setOnClickListener(v -> {
            editArtistToDB();
        });

        deleteArtist.setOnClickListener(v -> {
            deleteArtistFromDB();
        });

        findViewById(R.id.backBtn).setOnClickListener(v -> {
            onBackPressed();
        });

    }

    private void initialize() {
        artistId = findViewById(R.id.artistIdTxt);
        artistName = findViewById(R.id.artistNameTxt);
        artistAge = findViewById(R.id.artistAgeTxt);
        artistSex = findViewById(R.id.artistSexTxt);
        artistLanguage = findViewById(R.id.artistLanguageTxt);
        songsComposed = findViewById(R.id.artistNoSongsComposedTxt);
        editArtist = findViewById(R.id.editArtistBtn);
        deleteArtist = findViewById(R.id.deleteArtistBtn);
    }

    private void deleteArtistFromDB() {
        String id = artistId.getText().toString().trim();

        if (TextUtils.isEmpty(id)){
            artistId.setError("Enter Artist id");
            artistId.requestFocus();
            return;
        }

        databaseHelper.deleteArtist(id);

        startActivity(new Intent(EditArtistActivity.this, MainActivity.class));
        finish();
    }

    private void editArtistToDB() {
        String id = artistId.getText().toString().trim();

        if (TextUtils.isEmpty(id)){
            artistId.setError("Enter Artist id");
            artistId.requestFocus();
            return;
        }

        String age = artistAge.getText().toString().trim();
        int numAge = 0;
        if (age != null){
            if (age.length() > 0){
                numAge = Integer.parseInt(age);
            }
        }

        String songs = songsComposed.getText().toString().trim();
        int numSongs = 0;
        if (songs != null){
            if (songs.length() > 0){
                numSongs = Integer.parseInt(songs);
            }
        }

        int result = databaseHelper.updateArtist(
                id,
                artistName.getText().toString().trim(),
                numAge,
                artistSex.getText().toString().trim(),
                artistLanguage.getText().toString().trim(),
                numSongs
        );

        if (result == 1){
            startActivity(new Intent(EditArtistActivity.this, MainActivity.class));
            finish();
        }
        else{
            Toast.makeText(this, "Failed to update artist", Toast.LENGTH_SHORT).show();
        }
    }
}