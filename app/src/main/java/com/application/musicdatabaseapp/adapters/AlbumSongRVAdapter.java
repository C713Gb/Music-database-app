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
import com.application.musicdatabaseapp.EditMovieSongActivity;
import com.application.musicdatabaseapp.R;
import com.application.musicdatabaseapp.models.AlbumSongModel;
import com.application.musicdatabaseapp.models.MovieSongModel;
import com.google.gson.Gson;

import java.util.ArrayList;

public class AlbumSongRVAdapter extends RecyclerView.Adapter<AlbumSongRVAdapter.ViewHolder> {

    private Context context;
    private ArrayList<AlbumSongModel> albumSongModelArrayList;

    public AlbumSongRVAdapter(Context context, ArrayList<AlbumSongModel> albumSongModelArrayList) {
        this.context = context;
        this.albumSongModelArrayList = albumSongModelArrayList;
    }

    @NonNull
    @Override
    public AlbumSongRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.album_song_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumSongRVAdapter.ViewHolder holder, int position) {
        holder.bind(albumSongModelArrayList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return albumSongModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView artistId,albumId, songName, songs, duration, editAlbumSong;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            artistId = itemView.findViewById(R.id.artistId);
            albumId = itemView.findViewById(R.id.albumId);
            songName = itemView.findViewById(R.id.albumSongName);
            songs = itemView.findViewById(R.id.albumSongs);
            duration = itemView.findViewById(R.id.duration);
            editAlbumSong = itemView.findViewById(R.id.editAlbumSong);
        }

        public void bind(AlbumSongModel albumSongModel, int position) {

            artistId.setText("Artist id: "+albumSongModel.getArt_id());
            albumId.setText("Album id: "+albumSongModel.getAlb_id());
            songName.setText("Album Song Name: "+albumSongModel.getName());
            songs.setText("No. of Songs: "+Integer.toString(albumSongModel.getNo_of_songs()));
            duration.setText("Duration: "+Float.toString(albumSongModel.getDuration()));

            editAlbumSong.setOnClickListener(view -> {
                Gson gson = new Gson();
                String data = gson.toJson(albumSongModel, AlbumSongModel.class);

                Intent intent = new Intent(context, EditAlbumSongActivity.class);
                intent.putExtra("album_song", data);
                context.startActivity(intent);
            });
        }
    }
}
