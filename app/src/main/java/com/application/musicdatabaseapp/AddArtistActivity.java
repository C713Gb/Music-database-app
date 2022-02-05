package com.application.musicdatabaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.application.musicdatabaseapp.db.DatabaseHelper;

public class AddArtistActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;

    private EditText artistId, artistName, artistAge, artistSex, artistLanguage, songsComposed;
    private Button addArtist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_artist);

        initialize();

        databaseHelper = new DatabaseHelper(this);

        addArtist.setOnClickListener(v -> {
            addArtistToDB();
        });

        findViewById(R.id.backBtn).setOnClickListener(v -> {
            onBackPressed();
        });

    }

    private void addArtistToDB() {
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

        boolean result = databaseHelper.insertArtist(
                id,
                artistName.getText().toString().trim(),
                numAge,
                artistSex.getText().toString().trim(),
                artistLanguage.getText().toString().trim(),
                numSongs
        );

        if (result){
            startActivity(new Intent(AddArtistActivity.this, MainActivity.class));
            finish();
        }
        else{
            Toast.makeText(this, "Failed to add artist", Toast.LENGTH_SHORT).show();
        }
    }

    private void initialize() {
        artistId = findViewById(R.id.artistIdTxt);
        artistName = findViewById(R.id.artistNameTxt);
        artistAge = findViewById(R.id.artistAgeTxt);
        artistSex = findViewById(R.id.artistSexTxt);
        artistLanguage = findViewById(R.id.artistLanguageTxt);
        songsComposed = findViewById(R.id.artistNoSongsComposedTxt);
        addArtist = findViewById(R.id.addArtistBtn);
    }
}