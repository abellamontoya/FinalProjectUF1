package com.example.navigationexercice;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
//doesnt work
@Dao
public interface SongModelDao {

    @Insert
    void insert(SongModelEntity song);

    default void insertAsync(SongModelEntity song) {
        AppDatabase.databaseWriteExecutor.execute(() -> insert(song));
    }

    @Query("SELECT * FROM songs")
    LiveData<List<SongModelEntity>> getAllSongs();
}
