package com.example.navigationexercice;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;


import java.util.ArrayList;
import java.util.List;

public class bottom3fragment extends Fragment {

    private EditText editTextSongName;
    private EditText editTextAuthorName;
    private EditText editTextAudioUrl;
    private Button btnCreateSong;

    private List<songModel> songModels;
    private recyclerViewAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom3fragment, container, false);


        editTextSongName = view.findViewById(R.id.editTextSongName);
        editTextAuthorName = view.findViewById(R.id.editTextAuthorName);
        editTextAudioUrl = view.findViewById(R.id.editTextAudioUrl);
        btnCreateSong = view.findViewById(R.id.btnCreateSong);

        songModels = new ArrayList<>();  // Initialize the songModels list

        adapter = new recyclerViewAdapter(getContext(), (ArrayList<songModel>) songModels);

        btnCreateSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewSong();
            }
        });

        return view;
    }

    private void createNewSong() {
        String songName = editTextSongName.getText().toString().trim();
        String authorName = editTextAuthorName.getText().toString().trim();
        String audioUrl = editTextAudioUrl.getText().toString().trim();

        if (!songName.isEmpty() && !authorName.isEmpty() && !audioUrl.isEmpty()) {
            songModel newSong = new songModel(songName, authorName, audioUrl, R.drawable.default_album_cover);

            songModels.add(newSong);
            adapter.notifyDataSetChanged();
            songStorage.saveSongs(getContext(), songModels);
            editTextSongName.getText().clear();
            editTextAuthorName.getText().clear();
            editTextAudioUrl.getText().clear();

            Toast.makeText(getContext(), "Song created successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
        }
    }
}
