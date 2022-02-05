package com.application.musicdatabaseapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.application.musicdatabaseapp.adapters.AlbumSongRVAdapter;
import com.application.musicdatabaseapp.adapters.ArtistRVAdapter;
import com.application.musicdatabaseapp.adapters.MovieSongRVAdapter;
import com.application.musicdatabaseapp.adapters.PlaylistRVAdapter;
import com.application.musicdatabaseapp.adapters.PodcastRVAdapter;
import com.application.musicdatabaseapp.adapters.PodcasterRVAdapter;
import com.application.musicdatabaseapp.adapters.UserRVAdapter;
import com.application.musicdatabaseapp.db.DatabaseHelper;
import com.application.musicdatabaseapp.models.AlbumSongModel;
import com.application.musicdatabaseapp.models.ArtistModel;
import com.application.musicdatabaseapp.models.MovieSongModel;
import com.application.musicdatabaseapp.models.PlaylistModel;
import com.application.musicdatabaseapp.models.PodcastModel;
import com.application.musicdatabaseapp.models.PodcasterModel;
import com.application.musicdatabaseapp.models.UserModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private ArrayList<ArtistModel> artistModelArrayList;
    private ArrayList<MovieSongModel> movieSongModelArrayList;
    private ArrayList<AlbumSongModel> albumSongModelArrayList;
    private ArrayList<UserModel> userModelArrayList;
    private ArrayList<PlaylistModel> playlistModelArrayList;
    private ArrayList<PodcasterModel> podcasterModelArrayList;
    private ArrayList<PodcastModel> podcastModelArrayList;
    public static final String TAG = "ABCD";

    private TextView noArtistData, noMovieSongData, noAlbumSongData, noUserData, noPlaylistData, noPodcasterData, noPodcastData;
    private RecyclerView artistRV, movieSongRV, albumSongRV, userRV, playlistRV, podcasterRV, podcastRV;
    private Button addArtist, addMovieSongBtn, addAlbumSongBtn, addUserBtn, addPlaylistBtn, addPodcasterBtn, addPodcastBtn;

    private ArtistRVAdapter artistRVAdapter;
    private MovieSongRVAdapter movieSongRVAdapter;
    private AlbumSongRVAdapter albumSongRVAdapter;
    private UserRVAdapter userRVAdapter;
    private PlaylistRVAdapter playlistRVAdapter;
    private PodcasterRVAdapter podcasterRVAdapter;
    private PodcastRVAdapter podcastRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();

        databaseHelper = new DatabaseHelper(this);

        artistModelArrayList = databaseHelper.getAllArtists();
        movieSongModelArrayList = databaseHelper.getAllMovieSongs();
        albumSongModelArrayList = databaseHelper.getAllAlbumSongs();
        userModelArrayList = databaseHelper.getAllUsers();
        playlistModelArrayList = databaseHelper.getAllPlaylists();
        podcasterModelArrayList = databaseHelper.getAllPodcasters();
        podcastModelArrayList = databaseHelper.getAllPodcasts();

        artistRVAdapter = new ArtistRVAdapter(this, artistModelArrayList);
        artistRV.setAdapter(artistRVAdapter);

        movieSongRVAdapter = new MovieSongRVAdapter(this, movieSongModelArrayList);
        movieSongRV.setAdapter(movieSongRVAdapter);

        albumSongRVAdapter = new AlbumSongRVAdapter(this, albumSongModelArrayList);
        albumSongRV.setAdapter(albumSongRVAdapter);

        userRVAdapter = new UserRVAdapter(this, userModelArrayList);
        userRV.setAdapter(userRVAdapter);

        playlistRVAdapter = new PlaylistRVAdapter(this, playlistModelArrayList);
        playlistRV.setAdapter(playlistRVAdapter);

        podcasterRVAdapter = new PodcasterRVAdapter(this, podcasterModelArrayList);
        podcasterRV.setAdapter(podcasterRVAdapter);

        podcastRVAdapter = new PodcastRVAdapter(this, podcastModelArrayList);
        podcastRV.setAdapter(podcastRVAdapter);

        if (artistModelArrayList.size() == 0){
            artistRV.setVisibility(View.GONE);
            noArtistData.setVisibility(View.VISIBLE);
        }
        else{
            noArtistData.setVisibility(View.GONE);
            artistRV.setVisibility(View.VISIBLE);
        }

        if (movieSongModelArrayList.size() == 0){
            movieSongRV.setVisibility(View.GONE);
            noMovieSongData.setVisibility(View.VISIBLE);
        }
        else{
            noMovieSongData.setVisibility(View.GONE);
            movieSongRV.setVisibility(View.VISIBLE);
        }

        if (albumSongModelArrayList.size() == 0){
            albumSongRV.setVisibility(View.GONE);
            noAlbumSongData.setVisibility(View.VISIBLE);
        }
        else{
            noAlbumSongData.setVisibility(View.GONE);
            albumSongRV.setVisibility(View.VISIBLE);
        }

        if (userModelArrayList.size() == 0){
            userRV.setVisibility(View.GONE);
            noUserData.setVisibility(View.VISIBLE);
        }
        else{
            noUserData.setVisibility(View.GONE);
            userRV.setVisibility(View.VISIBLE);
        }

        if (playlistModelArrayList.size() == 0){
            playlistRV.setVisibility(View.GONE);
            noPlaylistData.setVisibility(View.VISIBLE);
        }
        else{
            noPlaylistData.setVisibility(View.GONE);
            playlistRV.setVisibility(View.VISIBLE);
        }

        if (podcasterModelArrayList.size() == 0){
            podcasterRV.setVisibility(View.GONE);
            noPodcasterData.setVisibility(View.VISIBLE);
        }
        else{
            noPodcasterData.setVisibility(View.GONE);
            podcasterRV.setVisibility(View.VISIBLE);
        }

        if (podcastModelArrayList.size() == 0){
            podcastRV.setVisibility(View.GONE);
            noPodcastData.setVisibility(View.VISIBLE);
        }
        else{
            noPodcastData.setVisibility(View.GONE);
            podcastRV.setVisibility(View.VISIBLE);
        }

        addArtist.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, AddArtistActivity.class));
        });

        addMovieSongBtn.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, AddMovieSongActivity.class));
        });

        addAlbumSongBtn.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, AddAlbumSongActivity.class));
        });

        addUserBtn.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, AddAlbumSongActivity.class));
        });

        addPlaylistBtn.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, AddAlbumSongActivity.class));
        });

        addPodcasterBtn.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, AddAlbumSongActivity.class));
        });

        addPodcastBtn.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, AddAlbumSongActivity.class));
        });

    }

    private void initialize() {
        noArtistData = findViewById(R.id.noArtistData);
        noMovieSongData = findViewById(R.id.noMovieSongData);
        noAlbumSongData = findViewById(R.id.noAlbumSongData);
        noUserData = findViewById(R.id.noUserData);
        noPlaylistData = findViewById(R.id.noPlaylistData);
        noPodcasterData = findViewById(R.id.noPodcasterData);
        noPodcastData = findViewById(R.id.noPodcastData);

        artistRV = findViewById(R.id.artistRV);
        artistRV.setHasFixedSize(true);
        movieSongRV = findViewById(R.id.movieSongRV);
        movieSongRV.setHasFixedSize(true);
        albumSongRV = findViewById(R.id.albumSongRV);
        albumSongRV.setHasFixedSize(true);
        userRV = findViewById(R.id.userRV);
        userRV.setHasFixedSize(true);
        playlistRV = findViewById(R.id.playlistRV);
        playlistRV.setHasFixedSize(true);
        podcasterRV = findViewById(R.id.podcasterRV);
        podcasterRV.setHasFixedSize(true);
        podcastRV = findViewById(R.id.podcastRV);
        podcastRV.setHasFixedSize(true);

        addArtist = findViewById(R.id.addArtistBtn);
        addMovieSongBtn = findViewById(R.id.addMovieSongBtn);
        addAlbumSongBtn = findViewById(R.id.addAlbumSongBtn);
        addUserBtn = findViewById(R.id.addUserBtn);
        addPlaylistBtn = findViewById(R.id.addPlaylistBtn);
        addPodcasterBtn = findViewById(R.id.addPodcasterBtn);
        addPodcastBtn = findViewById(R.id.addPodcasterBtn);
    }

    private void displayMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}