package com.application.musicdatabaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.application.musicdatabaseapp.db.DatabaseHelper;

public class AddUserActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;

    private EditText userId, userName, userAge, userSex, userAddress, userPhone;
    private Button addUser;
    public static final String TAG = "ABCD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        initialize();

        databaseHelper = new DatabaseHelper(this);

        addUser.setOnClickListener(v -> {
            addUserToDB();
        });

        findViewById(R.id.backBtn).setOnClickListener(v -> {
            onBackPressed();
        });
    }

    private void addUserToDB() {
        String id = userId.getText().toString().trim();

        if (TextUtils.isEmpty(id)){
            userId.setError("Enter User id");
            userId.requestFocus();
            return;
        }

        String ph = userPhone.getText().toString().trim();
        long numPhone = 0;
        if (ph != null){
            if (ph.length() > 0){
                numPhone = Long.parseLong(ph);
            }
        }

        String age = userAge.getText().toString().trim();
        int numAge = 0;
        if (age != null){
            if (age.length() > 0){
                numAge = Integer.parseInt(age);
            }
        }

        boolean result = databaseHelper.insertUser(
                id,
                userName.getText().toString().trim(),
                numAge,
                userSex.getText().toString().trim(),
                numPhone,
                userAddress.getText().toString().trim()
        );

        if (result){
            startActivity(new Intent(AddUserActivity.this, MainActivity.class));
            finish();
        }
        else{
            Toast.makeText(this, "Failed to add user", Toast.LENGTH_SHORT).show();
        }
    }

    private void initialize() {
        userId = findViewById(R.id.userIdTxt);
        userName = findViewById(R.id.userNameTxt);
        userAge = findViewById(R.id.userAgeTxt);
        userSex = findViewById(R.id.userSexTxt);
        userAddress = findViewById(R.id.userAddressTxt);
        userPhone = findViewById(R.id.userPhoneTxt);
        addUser = findViewById(R.id.addUserBtn);
    }
}