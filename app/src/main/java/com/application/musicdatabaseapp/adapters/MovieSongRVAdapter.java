package com.application.musicdatabaseapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.musicdatabaseapp.EditMovieSongActivity;
import com.application.musicdatabaseapp.R;
import com.application.musicdatabaseapp.models.MovieSongModel;
import com.google.gson.Gson;

import java.util.ArrayList;

public class MovieSongRVAdapter extends RecyclerView.Adapter<MovieSongRVAdapter.ViewHolder> {

    private Context context;
    private ArrayList<MovieSongModel> movieSongModelArrayList;

    public MovieSongRVAdapter(Context context, ArrayList<MovieSongModel> movieSongModelArrayList) {
        this.context = context;
        this.movieSongModelArrayList = movieSongModelArrayList;
    }

    @NonNull
    @Override
    public MovieSongRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_song_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieSongRVAdapter.ViewHolder holder, int position) {
        holder.bind(movieSongModelArrayList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return movieSongModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView artistId,movieId, songName, songs, duration, editMovieSong;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            artistId = itemView.findViewById(R.id.artistId);
            movieId = itemView.findViewById(R.id.movieId);
            songName = itemView.findViewById(R.id.movieSongName);
            songs = itemView.findViewById(R.id.movieSongs);
            duration = itemView.findViewById(R.id.duration);
            editMovieSong = itemView.findViewById(R.id.editMovieSong);
        }

        public void bind(MovieSongModel movieSongModel, int position) {
            artistId.setText("Artist id: "+movieSongModel.getArt_id());
            movieId.setText("Movie id: "+movieSongModel.getMov_id());
            songName.setText("Movie Song name: "+movieSongModel.getName());
            songs.setText("Songs composed: "+Integer.toString(movieSongModel.getNo_of_songs()));
            duration.setText("Duration: "+Float.toString(movieSongModel.getDuration()));

            editMovieSong.setOnClickListener(view -> {
                Gson gson = new Gson();
                String data = gson.toJson(movieSongModel, MovieSongModel.class);

                Intent intent = new Intent(context, EditMovieSongActivity.class);
                intent.putExtra("movie_song", data);
                context.startActivity(intent);
            });
        }
    }
}
