package com.application.musicdatabaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.application.musicdatabaseapp.db.DatabaseHelper;

public class AddPodcasterActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;

    private EditText podcasterId, podcasterName, podcasterAge, podcasterSex, podcasterLanguage;
    private Button addPodcaster;
    public static final String TAG = "ABCD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_podcaster);

        initialize();

        databaseHelper = new DatabaseHelper(this);

        addPodcaster.setOnClickListener(v -> {
            addPodcasterToDB();
        });

        findViewById(R.id.backBtn).setOnClickListener(v -> {
            onBackPressed();
        });
    }

    private void addPodcasterToDB() {
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

        boolean result = databaseHelper.insertPodcaster(
                id,
                podcasterName.getText().toString().trim(),
                numAge,
                podcasterSex.getText().toString().trim(),
                podcasterLanguage.getText().toString().trim()
        );

        if (result){
            startActivity(new Intent(AddPodcasterActivity.this, MainActivity.class));
            finish();
        }
        else{
            Toast.makeText(this, "Failed to add podcaster", Toast.LENGTH_SHORT).show();
        }
    }

    private void initialize() {
        podcasterId = findViewById(R.id.podcasterIdTxt);
        podcasterName = findViewById(R.id.podcasterNameTxt);
        podcasterAge = findViewById(R.id.podcasterAgeTxt);
        podcasterSex = findViewById(R.id.podcasterSexTxt);
        podcasterLanguage = findViewById(R.id.podcasterLanguageTxt);
        addPodcaster = findViewById(R.id.addPodcasterBtn);
    }
}