package com.application.musicdatabaseapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.musicdatabaseapp.EditAlbumSongActivity;
import com.application.musicdatabaseapp.EditUserActivity;
import com.application.musicdatabaseapp.R;
import com.application.musicdatabaseapp.models.AlbumSongModel;
import com.application.musicdatabaseapp.models.UserModel;
import com.google.gson.Gson;

import java.util.ArrayList;

public class UserRVAdapter extends RecyclerView.Adapter<UserRVAdapter.ViewHolder> {

    private Context context;
    private ArrayList<UserModel> userModelArrayList;

    public UserRVAdapter(Context context, ArrayList<UserModel> userModelArrayList) {
        this.context = context;
        this.userModelArrayList = userModelArrayList;
    }

    @NonNull
    @Override
    public UserRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserRVAdapter.ViewHolder holder, int position) {
        holder.bind(userModelArrayList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return userModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView userID, userName, userAge, userSex, userPhone, userAddress, editUser;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            userID = itemView.findViewById(R.id.userId);
            userName = itemView.findViewById(R.id.userName);
            userAge = itemView.findViewById(R.id.userAge);
            userSex = itemView.findViewById(R.id.userSex);
            userPhone = itemView.findViewById(R.id.userPhone);
            userAddress = itemView.findViewById(R.id.userAddress);
            editUser = itemView.findViewById(R.id.editUser);
        }

        public void bind(UserModel userModel, int position) {

            userID.setText("User id: "+userModel.getUser_id());
            userName.setText("User Name: "+userModel.getName());
            userAge.setText("User Age: "+Integer.toString(userModel.getAge()));
            userSex.setText("User Sex: "+userModel.getSex());
            userPhone.setText("User Phone: "+Long.toString(userModel.getPhone()));
            userAddress.setText("User Address: "+userModel.getAddress());

            editUser.setOnClickListener(view -> {
                Gson gson = new Gson();
                String data = gson.toJson(userModel, UserModel.class);

                Intent intent = new Intent(context, EditUserActivity.class);
                intent.putExtra("user", data);
                context.startActivity(intent);
            });
        }
    }
}
