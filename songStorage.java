package com.example.navigationexercice;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class songStorage {

    private static final String SONG_PREFS = "SongPrefs";
    private static final String SONG_LIST_KEY = "songList";

    public static void saveSongs(Context context, List<songModel> songs) {
        SharedPreferences preferences = context.getSharedPreferences(SONG_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(songs);
        editor.putString(SONG_LIST_KEY, json);
        editor.apply();
    }

    public static List<songModel> loadSongs(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(SONG_PREFS, Context.MODE_PRIVATE);
        Gson gson = new Gson();

        String json = preferences.getString(SONG_LIST_KEY, "");

        Type type = new TypeToken<List<songModel>>(){}.getType();
        return gson.fromJson(json, type);
    }
}
