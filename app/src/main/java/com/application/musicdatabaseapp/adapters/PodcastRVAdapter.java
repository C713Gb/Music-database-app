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
import com.application.musicdatabaseapp.EditPodcastActivity;
import com.application.musicdatabaseapp.R;
import com.application.musicdatabaseapp.models.PodcastModel;
import com.application.musicdatabaseapp.models.UserModel;
import com.google.gson.Gson;

import java.util.ArrayList;

public class PodcastRVAdapter extends RecyclerView.Adapter<PodcastRVAdapter.ViewHolder> {

    private Context context;
    private ArrayList<PodcastModel> podcastModelArrayList;

    public PodcastRVAdapter(Context context, ArrayList<PodcastModel> podcastModelArrayList) {
        this.context = context;
        this.podcastModelArrayList = podcastModelArrayList;
    }

    @NonNull
    @Override
    public PodcastRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.podcast_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PodcastRVAdapter.ViewHolder holder, int position) {
        holder.bind(podcastModelArrayList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return podcastModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView podcastId, podcasterId, podcastName, eps, editPodcast;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            podcastId = itemView.findViewById(R.id.podcastId);
            podcasterId = itemView.findViewById(R.id.podcasterId);
            podcastName = itemView.findViewById(R.id.podcastName);
            eps = itemView.findViewById(R.id.eps);
            editPodcast = itemView.findViewById(R.id.editPodcast);
        }

        public void bind(PodcastModel podcastModel, int position) {

            podcastId.setText("Podcast id: "+podcastModel.getPodcasts_id());
            podcasterId.setText("Podcaster id: "+podcastModel.getPodcaster_id());
            podcastName.setText("Podcast Name: "+podcastModel.getName());
            eps.setText("No. of Episodes: "+Integer.toString(podcastModel.getNo_of_episodes()));

            editPodcast.setOnClickListener(view -> {
                Gson gson = new Gson();
                String data = gson.toJson(podcastModel, PodcastModel.class);

                Intent intent = new Intent(context, EditPodcastActivity.class);
                intent.putExtra("podcast", data);
                context.startActivity(intent);
            });
        }
    }
}
