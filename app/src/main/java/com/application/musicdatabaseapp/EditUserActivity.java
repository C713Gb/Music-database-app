package com.application.musicdatabaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.application.musicdatabaseapp.db.DatabaseHelper;
import com.application.musicdatabaseapp.models.AlbumSongModel;
import com.application.musicdatabaseapp.models.UserModel;
import com.google.gson.Gson;

public class EditUserActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;

    private EditText userId, userName, userAge, userSex, userAddress, userPhone;
    private Button editUser, deleteUser;
    public static final String TAG = "ABCD";
    private UserModel userModel = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        initialize();

        if (getIntent() != null){
            if (getIntent().hasExtra("user")){
                String data = getIntent().getStringExtra("user");
                Gson gson = new Gson();
                userModel = gson.fromJson(data, UserModel.class);
            }
        }

        databaseHelper = new DatabaseHelper(this);

        if (userModel != null){
            userId.setText(userModel.getUser_id());
            userName.setText(userModel.getName());
            userAge.setText(Integer.toString(userModel.getAge()));
            userSex.setText(userModel.getSex());
            userAddress.setText(userModel.getAddress());
            userPhone.setText(Long.toString(userModel.getPhone()));
        }

        editUser.setOnClickListener(v -> {
            editUserToDB();
        });

        deleteUser.setOnClickListener(v -> {
            deleteUserFromDB();
        });

        findViewById(R.id.backBtn).setOnClickListener(v -> {
            onBackPressed();
        });
    }

    private void deleteUserFromDB() {
        String id = userId.getText().toString().trim();

        if (TextUtils.isEmpty(id)){
            userId.setError("Enter User id");
            userId.requestFocus();
            return;
        }

        databaseHelper.deleteUser(id);

        startActivity(new Intent(EditUserActivity.this, MainActivity.class));
        finish();
    }

    private void editUserToDB() {
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

        int result = databaseHelper.updateUser(
                id,
                userName.getText().toString().trim(),
                numAge,
                userSex.getText().toString().trim(),
                numPhone,
                userAddress.getText().toString().trim()
        );

        if (result == 1){
            startActivity(new Intent(EditUserActivity.this, MainActivity.class));
            finish();
        }
        else{
            Toast.makeText(this, "Failed to update user", Toast.LENGTH_SHORT).show();
        }
    }

    private void initialize() {
        userId = findViewById(R.id.userIdTxt);
        userName = findViewById(R.id.userNameTxt);
        userAge = findViewById(R.id.userAgeTxt);
        userSex = findViewById(R.id.userSexTxt);
        userAddress = findViewById(R.id.userAddressTxt);
        userPhone = findViewById(R.id.userPhoneTxt);
        editUser = findViewById(R.id.editUserBtn);
        deleteUser = findViewById(R.id.deleteUserBtn);
    }
}