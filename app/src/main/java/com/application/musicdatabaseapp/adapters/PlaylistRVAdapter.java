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
import com.application.musicdatabaseapp.EditPlaylistActivity;
import com.application.musicdatabaseapp.R;
import com.application.musicdatabaseapp.models.PlaylistModel;
import com.application.musicdatabaseapp.models.UserModel;
import com.google.gson.Gson;

import java.util.ArrayList;

public class PlaylistRVAdapter extends RecyclerView.Adapter<PlaylistRVAdapter.ViewHolder> {

    private Context context;
    private ArrayList<PlaylistModel> playlistModelArrayList;

    public PlaylistRVAdapter(Context context, ArrayList<PlaylistModel> playlistModelArrayList) {
        this.context = context;
        this.playlistModelArrayList = playlistModelArrayList;
    }

    @NonNull
    @Override
    public PlaylistRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.playlist_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaylistRVAdapter.ViewHolder holder, int position) {
        holder.bind(playlistModelArrayList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return playlistModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView userID, playlistId, name, songs, duration, editPlaylist;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            userID = itemView.findViewById(R.id.userId);
            playlistId = itemView.findViewById(R.id.playlistId);
            name = itemView.findViewById(R.id.playlistName);
            songs = itemView.findViewById(R.id.playlistSongs);
            duration = itemView.findViewById(R.id.duration);
            editPlaylist = itemView.findViewById(R.id.editPlaylist);
        }

        public void bind(PlaylistModel playlistModel, int position) {

            userID.setText("User id: "+playlistModel.getUser_id());
            playlistId.setText("Playlist id: "+playlistModel.getPlaylist_id());
            name.setText("Playlist Name: "+playlistModel.getName());
            songs.setText("No. of Songs: "+Integer.toString(playlistModel.getNo_of_songs()));
            duration.setText("Duration: "+Float.toString(playlistModel.getDuration()));


            editPlaylist.setOnClickListener(view -> {
                Gson gson = new Gson();
                String data = gson.toJson(playlistModel, PlaylistModel.class);

                Intent intent = new Intent(context, EditPlaylistActivity.class);
                intent.putExtra("playlist", data);
                context.startActivity(intent);
            });
        }
    }
}
