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
import com.application.musicdatabaseapp.models.PodcasterModel;
import com.google.gson.Gson;

public class EditPodcasterActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;

    private EditText podcasterId, podcasterName, podcasterAge, podcasterSex, podcasterLanguage;
    private Button editPodcaster, deletePodcaster;
    public static final String TAG = "ABCD";
    private PodcasterModel podcasterModel = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_podcaster);

        initialize();

        if (getIntent() != null){
            if (getIntent().hasExtra("podcaster")){
                String data = getIntent().getStringExtra("podcaster");
                Gson gson = new Gson();
                podcasterModel = gson.fromJson(data, PodcasterModel.class);
            }
        }

        databaseHelper = new DatabaseHelper(this);

        if (podcasterModel != null){
            podcasterId.setText(podcasterModel.getPod_caster_id());
            podcasterName.setText(podcasterModel.getName());
            podcasterAge.setText(Integer.toString(podcasterModel.getAge()));
            podcasterSex.setText(podcasterModel.getSex());
            podcasterLanguage.setText(podcasterModel.getLanguage());
        }

        editPodcaster.setOnClickListener(v -> {
            editPodcasterToDB();
        });

        deletePodcaster.setOnClickListener(v -> {
            deletePodcasterFromDB();
        });

        findViewById(R.id.backBtn).setOnClickListener(v -> {
            onBackPressed();
        });
    }

    private void deletePodcasterFromDB() {
        String id = podcasterId.getText().toString().trim();

        if (TextUtils.isEmpty(id)){
            podcasterId.setError("Enter Podcaster id");
            podcasterId.requestFocus();
            return;
        }

        databaseHelper.deletePodcaster(id);

        startActivity(new Intent(EditPodcasterActivity.this, MainActivity.class));
        finish();
    }

    private void editPodcasterToDB() {
        String id = podcasterId.getText().toString().trim();

        if (TextUtils.isEmpty(id)){
            podcasterId.setError("Enter Podcaster id");
            podcasterId.requestFocus();
            return;
        }

        String age = podcasterAge.getText().toString().trim();
        int numAge = 0;
        if (age != null){
            if (age.length() > 0){
                numAge = Integer.parseInt(age);
            }
        }

        int result = databaseHelper.updatePodcaster(
                id,
                podcasterName.getText().toString().trim(),
                numAge,
                podcasterSex.getText().toString().trim(),
                podcasterLanguage.getText().toString().trim()
        );

        if (result == 1){
            startActivity(new Intent(EditPodcasterActivity.this, MainActivity.class));
            finish();
        }
        else{
            Toast.makeText(this, "Failed to update podcaster", Toast.LENGTH_SHORT).show();
        }
    }

    private void initialize() {
        podcasterId = findViewById(R.id.podcasterIdTxt);
        podcasterName = findViewById(R.id.podcasterNameTxt);
        podcasterAge = findViewById(R.id.podcasterAgeTxt);
        podcasterSex = findViewById(R.id.podcasterSexTxt);
        podcasterLanguage = findViewById(R.id.podcasterLanguageTxt);
        editPodcaster = findViewById(R.id.editPodcasterBtn);
        deletePodcaster = findViewById(R.id.deletePodcasterBtn);
    }
}