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
import com.application.musicdatabaseapp.R;
import com.application.musicdatabaseapp.models.PodcasterModel;
import com.application.musicdatabaseapp.models.UserModel;
import com.google.gson.Gson;

import java.util.ArrayList;

public class PodcasterRVAdapter extends RecyclerView.Adapter<PodcasterRVAdapter.ViewHolder> {

    private Context context;
    private ArrayList<PodcasterModel> podcasterModelArrayList;

    public PodcasterRVAdapter(Context context, ArrayList<PodcasterModel> podcasterModelArrayList) {
        this.context = context;
        this.podcasterModelArrayList = podcasterModelArrayList;
    }

    @NonNull
    @Override
    public PodcasterRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.podcaster_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PodcasterRVAdapter.ViewHolder holder, int position) {
        holder.bind(podcasterModelArrayList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return podcasterModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView podcasterID, podcasterName, podcasterAge, podcasterSex, podcasterLanguage, editPodcaster;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            podcasterID = itemView.findViewById(R.id.podcasterId);
            podcasterName = itemView.findViewById(R.id.podcasterName);
            podcasterAge = itemView.findViewById(R.id.podcasterAge);
            podcasterSex = itemView.findViewById(R.id.podcasterSex);
            podcasterLanguage = itemView.findViewById(R.id.podcasterLanguage);
            editPodcaster = itemView.findViewById(R.id.editPodcaster);
        }

        public void bind(PodcasterModel podcasterModel, int position) {

            podcasterID.setText("Podcaster id: "+podcasterModel.getPod_caster_id());
            podcasterName.setText("Podcaster Name: "+podcasterModel.getName());
            podcasterAge.setText("Podcaster Age: "+Integer.toString(podcasterModel.getAge()));
            podcasterSex.setText("Podcaster Sex: "+podcasterModel.getSex());
            podcasterLanguage.setText("Podcaster Language: "+podcasterModel.getLanguage());

            editPodcaster.setOnClickListener(view -> {
                Gson gson = new Gson();
                String data = gson.toJson(podcasterModel, PodcasterModel.class);

                Intent intent = new Intent(context, EditAlbumSongActivity.class);
                intent.putExtra("podcaster", data);
                context.startActivity(intent);
            });
        }
    }
}
