package com.application.musicdatabaseapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.musicdatabaseapp.EditArtistActivity;
import com.application.musicdatabaseapp.R;
import com.application.musicdatabaseapp.models.ArtistModel;
import com.google.gson.Gson;

import java.util.ArrayList;

public class ArtistRVAdapter extends RecyclerView.Adapter<ArtistRVAdapter.ViewHolder> {
    
    private Context context;
    private ArrayList<ArtistModel> artistModelArrayList;

    public ArtistRVAdapter(Context context, ArrayList<ArtistModel> artistModelArrayList) {
        this.context = context;
        this.artistModelArrayList = artistModelArrayList;
    }

    @NonNull
    @Override
    public ArtistRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.artist_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistRVAdapter.ViewHolder holder, int position) {
        holder.bind(artistModelArrayList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return artistModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView artistId, artistName, artistAge, artistSex, artistLanguage, songsComposed, editArtist;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            artistId = itemView.findViewById(R.id.artistId);
            artistName = itemView.findViewById(R.id.artistName);
            artistAge = itemView.findViewById(R.id.artistAge);
            artistSex = itemView.findViewById(R.id.artistSex);
            artistLanguage = itemView.findViewById(R.id.artistLanguage);
            songsComposed = itemView.findViewById(R.id.artistSongs);
            editArtist = itemView.findViewById(R.id.editArtist);
        }

        public void bind(ArtistModel artistModel, int position) {
            artistId.setText("Artist id: "+artistModel.getArt_id());
            artistAge.setText("Artist age: "+Integer.toString(artistModel.getAge()));
            artistName.setText("Artist name: "+artistModel.getName());
            artistSex.setText("Artist sex: "+artistModel.getSex());
            artistLanguage.setText("Artist language: "+artistModel.getLanguage());
            songsComposed.setText("Songs composed: "+Integer.toString(artistModel.getNo_of_songs_composed()));

            editArtist.setOnClickListener(view -> {
                Gson gson = new Gson();
                String data = gson.toJson(artistModel, ArtistModel.class);

                Intent intent = new Intent(context, EditArtistActivity.class);
                intent.putExtra("artist", data);
                context.startActivity(intent);
            });
        }
    }
}
