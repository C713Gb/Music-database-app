package com.application.musicdatabaseapp.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.application.musicdatabaseapp.models.AlbumSongModel;
import com.application.musicdatabaseapp.models.ArtistModel;
import com.application.musicdatabaseapp.models.MovieSongModel;
import com.application.musicdatabaseapp.models.PlaylistModel;
import com.application.musicdatabaseapp.models.PodcastModel;
import com.application.musicdatabaseapp.models.PodcasterModel;
import com.application.musicdatabaseapp.models.UserModel;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "music_db";
    public static final int DB_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
        super.onConfigure(db);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("PRAGMA foreign_keys=ON");

        String artistTable = "create table artist (" +
                "Art_id varchar(5) primary key, " +
                "Name varchar(15), " +
                "Age int," +
                "Sex varchar(5)," +
                "Language varchar(10)," +
                "No_of_songs_composed int);";

        String movieTable = "create table Movie_Songs (" +
                "Mov_id varchar(5)," +
                "Art_id references artist(Art_id) on delete cascade," +
                "Name varchar(20)," +
                "No_of_songs int," +
                "duration decimal(4,2)," +
                "primary key(Art_id, Mov_id));";

        String albumTable = "create table Album_Songs (" +
                "Alb_id varchar(5)," +
                "Art_id references artist(Art_id) on delete cascade," +
                "Name varchar(20)," +
                "No_of_songs int," +
                "duration decimal(4,2)," +
                "primary key (Art_id, Alb_id));";

        String userTable = "create table user(" +
                "User_id varchar(5) primary key," +
                "Name varchar(20)," +
                "Age int," +
                "Sex varchar(5)," +
                "Phone long," +
                "Address varchar(30));";

        String playlistTable = "create table playlist(" +
                "Playlist_id varchar(5)," +
                "User_id references user(User_id) on delete cascade," +
                "Name varchar(20)," +
                "No_of_songs int," +
                "duration decimal(4,2)," +
                "primary key(Playlist_id, User_id));";

        String podCasterTable = "create table podcaster(" +
                "Pod_caster_id varchar(5) primary key," +
                "Name varchar(15)," +
                "Age int," +
                "Sex varchar(5)," +
                "language varchar(10));";

        String podCastsTable = "create table podcasts(" +
                "Podcasts_id varchar(5)," +
                "Podcaster_id references podcaster(Pod_caster_id) on delete cascade," +
                "Name varchar(20)," +
                "No_of_episodes int," +
                "primary key (Podcaster_id, Podcasts_id));";

        try {
            DB.execSQL(artistTable);
            DB.execSQL(movieTable);
            DB.execSQL(albumTable);
            DB.execSQL(userTable);
            DB.execSQL(playlistTable);
            DB.execSQL(podCasterTable);
            DB.execSQL(podCastsTable);
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("PRAGMA foreign_keys=ON");

        DB.execSQL("DROP TABLE IF EXISTS artist");
        DB.execSQL("DROP TABLE IF EXISTS Movie_Songs");
        DB.execSQL("DROP TABLE IF EXISTS Album_Songs");
        DB.execSQL("DROP TABLE IF EXISTS user");
        DB.execSQL("DROP TABLE IF EXISTS playlist");
        DB.execSQL("DROP TABLE IF EXISTS podcaster");
        DB.execSQL("DROP TABLE IF EXISTS podcasts");

        onCreate(DB);
    }

    public boolean insertPodcast(String Podcasts_id,
                                   String Podcaster_id,
                                   String Name,
                                   int No_of_episodes) {
        try {
            SQLiteDatabase DB = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("Podcasts_id", Podcasts_id);
            contentValues.put("Podcaster_id", Podcaster_id);
            contentValues.put("Name", Name);
            contentValues.put("No_of_episodes", No_of_episodes);
            DB.replace("podcasts", null, contentValues);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertPodcaster(String Pod_caster_id,
                                  String Name,
                                  int Age,
                                  String Sex,
                                   String language) {
        try {
            SQLiteDatabase DB = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("Pod_caster_id", Pod_caster_id);
            contentValues.put("Name", Name);
            contentValues.put("Age", Age);
            contentValues.put("Sex", Sex);
            contentValues.put("language", language);
            DB.replace("podcaster", null, contentValues);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertPlaylist(String Playlist_id,
                              String User_id,
                              String Name,
                              int No_of_songs,
                              float duration) {
        try {
            SQLiteDatabase DB = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("Playlist_id", Playlist_id);
            contentValues.put("User_id", User_id);
            contentValues.put("Name", Name);
            contentValues.put("No_of_songs", No_of_songs);
            contentValues.put("duration", duration);
            DB.replace("playlist", null, contentValues);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertUser(String User_id,
                                   String Name,
                                   int Age,
                                   String Sex,
                                    long Phone,
                                   String Address) {
        try {
            SQLiteDatabase DB = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("User_id", User_id);
            contentValues.put("Name", Name);
            contentValues.put("Age", Age);
            contentValues.put("Sex", Sex);
            contentValues.put("Phone", Phone);
            contentValues.put("Address", Address);
            DB.replace("user", null, contentValues);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertAlbumSong(String Alb_id,
                                   String Art_id,
                                   String Name,
                                   int No_of_songs,
                                   float duration) {
        try {
            SQLiteDatabase DB = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("Alb_id", Alb_id);
            contentValues.put("Art_id", Art_id);
            contentValues.put("Name", Name);
            contentValues.put("No_of_songs", No_of_songs);
            contentValues.put("duration", duration);
            DB.replace("Album_Songs", null, contentValues);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertMovieSong(String Mov_id,
                                   String Art_id,
                                   String Name,
                                   int No_of_songs,
                                   float duration) {
        try {
            SQLiteDatabase DB = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("Mov_id", Mov_id);
            contentValues.put("Art_id", Art_id);
            contentValues.put("Name", Name);
            contentValues.put("No_of_songs", No_of_songs);
            contentValues.put("duration", duration);
            DB.replace("Movie_Songs", null, contentValues);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertArtist(String Art_id,
                                String Name,
                                int Age,
                                String Sex,
                                String Language,
                                int No_of_songs_composed) {
        try {
            SQLiteDatabase DB = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("Art_id", Art_id);
            contentValues.put("Name", Name);
            contentValues.put("Age", Age);
            contentValues.put("Sex", Sex);
            contentValues.put("Language", Language);
            contentValues.put("No_of_songs_composed", No_of_songs_composed);
            DB.replace("artist", null, contentValues);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @SuppressLint("Range")
    public ArrayList<PodcastModel> getAllPodcasts() {

        ArrayList<PodcastModel> podcastModelArrayList = new ArrayList<>();

        String selectQuery = "select * from podcasts";
        SQLiteDatabase DB = this.getReadableDatabase();
        Cursor c = DB.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                PodcastModel podcastModel = new PodcastModel();
                podcastModel.setPodcasts_id(c.getString(c.getColumnIndex("Podcasts_id")));
                podcastModel.setPodcaster_id(c.getString(c.getColumnIndex("Podcaster_id")));
                podcastModel.setName(c.getString(c.getColumnIndex("Name")));
                podcastModel.setNo_of_episodes(c.getInt(c.getColumnIndex("No_of_episodes")));

                podcastModelArrayList.add(podcastModel);
            }
            while (c.moveToNext());
        }

        return podcastModelArrayList;

    }

    @SuppressLint("Range")
    public ArrayList<PodcasterModel> getAllPodcasters() {

        ArrayList<PodcasterModel> podcasterModelArrayList = new ArrayList<>();

        String selectQuery = "select * from podcaster";
        SQLiteDatabase DB = this.getReadableDatabase();
        Cursor c = DB.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                PodcasterModel podcasterModel = new PodcasterModel();
                podcasterModel.setPod_caster_id(c.getString(c.getColumnIndex("Pod_caster_id")));
                podcasterModel.setName(c.getString(c.getColumnIndex("Name")));
                podcasterModel.setAge(c.getInt(c.getColumnIndex("Age")));
                podcasterModel.setSex(c.getString(c.getColumnIndex("Sex")));
                podcasterModel.setLanguage(c.getString(c.getColumnIndex("language")));

                podcasterModelArrayList.add(podcasterModel);
            }
            while (c.moveToNext());
        }

        return podcasterModelArrayList;

    }

    @SuppressLint("Range")
    public ArrayList<PlaylistModel> getAllPlaylists() {

        ArrayList<PlaylistModel> playlistModelArrayList = new ArrayList<>();

        String selectQuery = "select * from playlist";
        SQLiteDatabase DB = this.getReadableDatabase();
        Cursor c = DB.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                PlaylistModel playlistModel = new PlaylistModel();
                playlistModel.setPlaylist_id(c.getString(c.getColumnIndex("Playlist_id")));
                playlistModel.setUser_id(c.getString(c.getColumnIndex("User_id")));
                playlistModel.setName(c.getString(c.getColumnIndex("Name")));
                playlistModel.setNo_of_songs(c.getInt(c.getColumnIndex("No_of_songs")));
                playlistModel.setDuration(c.getFloat(c.getColumnIndex("duration")));

                playlistModelArrayList.add(playlistModel);
            }
            while (c.moveToNext());
        }

        return playlistModelArrayList;

    }

    @SuppressLint("Range")
    public ArrayList<UserModel> getAllUsers() {

        ArrayList<UserModel> userModelArrayList = new ArrayList<>();

        String selectQuery = "select * from user";
        SQLiteDatabase DB = this.getReadableDatabase();
        Cursor c = DB.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                UserModel userModel = new UserModel();
                userModel.setUser_id(c.getString(c.getColumnIndex("User_id")));
                userModel.setName(c.getString(c.getColumnIndex("Name")));
                userModel.setAge(c.getInt(c.getColumnIndex("Age")));
                userModel.setSex(c.getString(c.getColumnIndex("Sex")));
                userModel.setPhone(c.getLong(c.getColumnIndex("Phone")));
                userModel.setAddress(c.getString(c.getColumnIndex("Address")));

                userModelArrayList.add(userModel);
            }
            while (c.moveToNext());
        }

        return userModelArrayList;

    }

    @SuppressLint("Range")
    public ArrayList<ArtistModel> getAllArtists() {

        ArrayList<ArtistModel> artistModelArrayList = new ArrayList<>();

        String selectQuery = "select * from artist";
        SQLiteDatabase DB = this.getReadableDatabase();
        Cursor c = DB.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                ArtistModel artistModel = new ArtistModel();
                artistModel.setArt_id(c.getString(c.getColumnIndex("Art_id")));
                artistModel.setName(c.getString(c.getColumnIndex("Name")));
                artistModel.setAge(c.getInt(c.getColumnIndex("Age")));
                artistModel.setSex(c.getString(c.getColumnIndex("Sex")));
                artistModel.setLanguage(c.getString(c.getColumnIndex("Language")));
                artistModel.setNo_of_songs_composed(c.getInt(c.getColumnIndex("No_of_songs_composed")));

                artistModelArrayList.add(artistModel);
            }
            while (c.moveToNext());
        }

        return artistModelArrayList;

    }

    @SuppressLint("Range")
    public ArrayList<MovieSongModel> getAllMovieSongs() {

        ArrayList<MovieSongModel> movieSongModelArrayList = new ArrayList<>();

        String selectQuery = "select * from Movie_Songs";
        SQLiteDatabase DB = this.getReadableDatabase();
        Cursor c = DB.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                MovieSongModel movieSongModel = new MovieSongModel();
                movieSongModel.setMov_id(c.getString(c.getColumnIndex("Mov_id")));
                movieSongModel.setArt_id(c.getString(c.getColumnIndex("Art_id")));
                movieSongModel.setName(c.getString(c.getColumnIndex("Name")));
                movieSongModel.setDuration(c.getInt(c.getColumnIndex("duration")));
                movieSongModel.setNo_of_songs(c.getInt(c.getColumnIndex("No_of_songs")));

                movieSongModelArrayList.add(movieSongModel);
            }
            while (c.moveToNext());
        }

        return movieSongModelArrayList;

    }

    @SuppressLint("Range")
    public ArrayList<AlbumSongModel> getAllAlbumSongs() {

        ArrayList<AlbumSongModel> albumSongModelArrayList = new ArrayList<>();

        String selectQuery = "select * from Album_Songs";
        SQLiteDatabase DB = this.getReadableDatabase();
        Cursor c = DB.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                AlbumSongModel albumSongModel = new AlbumSongModel();
                albumSongModel.setAlb_id(c.getString(c.getColumnIndex("Alb_id")));
                albumSongModel.setArt_id(c.getString(c.getColumnIndex("Art_id")));
                albumSongModel.setName(c.getString(c.getColumnIndex("Name")));
                albumSongModel.setNo_of_songs(c.getInt(c.getColumnIndex("No_of_songs")));
                albumSongModel.setDuration(c.getFloat(c.getColumnIndex("duration")));

                albumSongModelArrayList.add(albumSongModel);
            }
            while (c.moveToNext());
        }

        return albumSongModelArrayList;

    }

    public int updatePodcast(String Podcasts_id,
                                 String Podcaster_id,
                                 String Name,
                                 int No_of_episodes) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Podcasts_id", Podcasts_id);
        contentValues.put("Podcaster_id", Podcaster_id);
        contentValues.put("Name", Name);
        contentValues.put("No_of_episodes", No_of_episodes);
        return DB.update("podcasts", contentValues,"Podcasts_id = ?", new String[]{String.valueOf(Podcasts_id)});
    }

    public int updatePodcaster(String Pod_caster_id,
                                   String Name,
                                   int Age,
                                   String Sex,
                                   String language) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Pod_caster_id", Pod_caster_id);
        contentValues.put("Name", Name);
        contentValues.put("Age", Age);
        contentValues.put("Sex", Sex);
        contentValues.put("language", language);
        return DB.update("podcaster", contentValues, "Pod_caster_id = ?", new String[]{String.valueOf(Pod_caster_id)});
    }

    public int updatePlaylist(String Playlist_id,
                                  String User_id,
                                  String Name,
                                  int No_of_songs,
                                  float duration) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Playlist_id", Playlist_id);
        contentValues.put("User_id", User_id);
        contentValues.put("Name", Name);
        contentValues.put("No_of_songs", No_of_songs);
        contentValues.put("duration", duration);
        return DB.update("playlist", contentValues, "Playlist_id = ?", new String[]{String.valueOf(Playlist_id)});
    }

    public int updateUser(String User_id,
                              String Name,
                              int Age,
                              String Sex,
                              long Phone,
                              String Address) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("User_id", User_id);
        contentValues.put("Name", Name);
        contentValues.put("Age", Age);
        contentValues.put("Sex", Sex);
        contentValues.put("Phone", Phone);
        contentValues.put("Address", Address);
        return DB.update("user", contentValues, "User_id = ?", new String[]{String.valueOf(User_id)});
    }

    public int updateAlbumSong(String Alb_id,
                               String Art_id,
                               String Name,
                               float duration,
                               int No_of_songs) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Alb_id", Alb_id);
        contentValues.put("Art_id", Art_id);
        contentValues.put("Name", Name);
        contentValues.put("No_of_songs", No_of_songs);
        contentValues.put("duration", duration);
        return DB.update("Album_Songs", contentValues, "Alb_id = ?", new String[]{String.valueOf(Alb_id)});
    }

    public int updateMovieSong(String Mov_id,
                               String Art_id,
                               String Name,
                               float duration,
                               int No_of_songs) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Mov_id", Mov_id);
        contentValues.put("Art_id", Art_id);
        contentValues.put("Name", Name);
        contentValues.put("duration", duration);
        contentValues.put("No_of_songs", No_of_songs);
        return DB.update("Movie_Songs", contentValues, "Mov_id = ?", new String[]{String.valueOf(Mov_id)});
    }

    public int updateArtist(String Art_id,
                            String Name,
                            int Age,
                            String Sex,
                            String Language,
                            int No_of_songs_composed) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Art_id", Art_id);
        contentValues.put("Name", Name);
        contentValues.put("Age", Age);
        contentValues.put("Sex", Sex);
        contentValues.put("Language", Language);
        contentValues.put("No_of_songs_composed", No_of_songs_composed);

        return DB.update("artist", contentValues, "Art_id = ?", new String[]{String.valueOf(Art_id)});
    }

    public void deleteArtist(String Art_id) {
        SQLiteDatabase DB = this.getWritableDatabase();
        DB.delete("artist", "Art_id = ?", new String[]{String.valueOf(Art_id)});
    }

    public void deleteMovieSong(String Mov_id) {
        SQLiteDatabase DB = this.getWritableDatabase();
        DB.delete("Movie_Songs", "Mov_id = ?", new String[]{String.valueOf(Mov_id)});
    }

    public void deleteAlbumSong(String Alb_id) {
        SQLiteDatabase DB = this.getWritableDatabase();
        DB.delete("Album_Songs", "Alb_id = ?", new String[]{String.valueOf(Alb_id)});
    }

    public void deleteUser(String User_id) {
        SQLiteDatabase DB = this.getWritableDatabase();
        DB.delete("user", "User_id = ?", new String[]{String.valueOf(User_id)});
    }

    public void deletePlaylist(String Playlist_id) {
        SQLiteDatabase DB = this.getWritableDatabase();
        DB.delete("playlist", "Playlist_id = ?", new String[]{String.valueOf(Playlist_id)});
    }

    public void deletePodcaster(String Pod_caster_id) {
        SQLiteDatabase DB = this.getWritableDatabase();
        DB.delete("podcaster", "Pod_caster_id = ?", new String[]{String.valueOf(Pod_caster_id)});
    }

    public void deletePodcast(String Podcasts_id) {
        SQLiteDatabase DB = this.getWritableDatabase();
        DB.delete("podcasts", "Podcasts_id = ?", new String[]{String.valueOf(Podcasts_id)});
    }
}
