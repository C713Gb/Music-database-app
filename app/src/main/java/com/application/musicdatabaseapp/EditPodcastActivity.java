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
import com.application.musicdatabaseapp.models.PodcastModel;
import com.google.gson.Gson;

public class EditPodcastActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;

    private EditText podcastID, podcasterID, podcastName, episodes;
    private Button editPodcast, deletePodcast;
    public static final String TAG = "ABCD";
    private PodcastModel podcastModel = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_podcast);

        initialize();

        if (getIntent() != null){
            if (getIntent().hasExtra("podcast")){
                String data = getIntent().getStringExtra("podcast");
                Gson gson = new Gson();
                podcastModel = gson.fromJson(data, PodcastModel.class);
            }
        }

        databaseHelper = new DatabaseHelper(this);

        if (podcastModel != null){
            podcastID.setText(podcastModel.getPodcasts_id());
            podcasterID.setText(podcastModel.getPodcaster_id());
            podcastName.setText(podcastModel.getName());
            episodes.setText(Integer.toString(podcastModel.getNo_of_episodes()));
        }

        editPodcast.setOnClickListener(v -> {
            editPodcastToDB();
        });

        deletePodcast.setOnClickListener(v -> {
            deletePodcastFromDB();
        });

        findViewById(R.id.backBtn).setOnClickListener(v -> {
            onBackPressed();
        });

    }

    private void deletePodcastFromDB() {
        String id = podcastID.getText().toString().trim();

        if (TextUtils.isEmpty(id)){
            podcastID.setError("Enter Podcast id");
            podcastID.requestFocus();
            return;
        }

        databaseHelper.deletePodcast(id);

        startActivity(new Intent(EditPodcastActivity.this, MainActivity.class));
        finish();
    }

    private void editPodcastToDB() {
        String id = podcastID.getText().toString().trim();

        if (TextUtils.isEmpty(id)){
            podcastID.setError("Enter Podcast id");
            podcastID.requestFocus();
            return;
        }

        String ep = episodes.getText().toString().trim();
        int numEP = 0;
        if (ep != null){
            if (ep.length() > 0){
                numEP = Integer.parseInt(ep);
            }
        }

        int result = databaseHelper.updatePodcast(
                id,
                podcasterID.getText().toString().trim(),
                podcastName.getText().toString().trim(),
                numEP
        );

        if (result == 1){
            startActivity(new Intent(EditPodcastActivity.this, MainActivity.class));
            finish();
        }
        else{
            Toast.makeText(this, "Failed to update podcast", Toast.LENGTH_SHORT).show();
        }
    }

    private void initialize() {
        podcastID = findViewById(R.id.podcastIdTxt);
        podcasterID = findViewById(R.id.podcasterIdTxt);
        podcastName = findViewById(R.id.podcastNameTxt);
        episodes = findViewById(R.id.episodes);
        editPodcast = findViewById(R.id.editPodcastBtn);
        deletePodcast = findViewById(R.id.deletePodcastBtn);
    }
}