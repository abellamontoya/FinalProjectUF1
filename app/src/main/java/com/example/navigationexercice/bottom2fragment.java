package com.example.navigationexercice;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class bottom2fragment extends Fragment {

    private RecyclerView recyclerView;
    private recyclerViewAdapter adapter;
    private ArrayList<songModel> songModels;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom2fragment, container, false);

        recyclerView = view.findViewById(R.id.RecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        songModels = (ArrayList<songModel>) songStorage.loadSongs(getContext());

        if (songModels == null) {
            songModels = new ArrayList<>();
        }

        adapter = new recyclerViewAdapter(getContext(), songModels);

        adapter.setOnItemClickListener(new recyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(songModel song) {
                playSong(song);
            }
        });

        recyclerView.setAdapter(adapter);

        setUpSongModels();

        return view;
    }

    private void setUpSongModels() {
        String[] songNames = getResources().getStringArray(R.array.Song);
        String[] authorNames = getResources().getStringArray(R.array.Author);

        int[] albumCovers = {R.drawable.albumcover1, R.drawable.albumcover2, R.drawable.albumcover3, R.drawable.albumcover4, R.drawable.albumcover5, R.drawable.albumcover6, R.drawable.albumcover7, R.drawable.albumcover8, R.drawable.albumcover9, R.drawable.albumcover10, R.drawable.albumcover11, R.drawable.albumcover12};

        songModels.clear();

        for (int i = 0; i < songNames.length; i++) {
            songModels.add(new songModel(songNames[i], authorNames[i], null, albumCovers[i]));
        }
        adapter.notifyDataSetChanged();
    }

    public void addNewSong(songModel newSong) {

        songModels.add(newSong);

        adapter.notifyDataSetChanged();

        songStorage.saveSongs(requireContext(), songModels);
    }

    private void playSong(songModel song) {

        String message = "Playing: " + song.getSongName() + " by " + song.getAuthorName();
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
