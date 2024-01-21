package com.example.navigationexercice;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
//doesnt work
@Entity(tableName = "songs")
public class SongModelEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "songName")
    public String songName;

    @ColumnInfo(name = "authorName")
    public String authorName;

    @ColumnInfo(name = "audioUrl")
    public String audioUrl;

    @ColumnInfo(name = "albumCover")
    public int albumCover;
}
