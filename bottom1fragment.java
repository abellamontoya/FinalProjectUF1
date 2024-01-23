package com.example.navigationexercice;

import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.io.IOException;

public class bottom1fragment extends Fragment {

    //If the song insists on play even when you havent selected it, click a lot of times the skip button

    private Button play, stop, pause, skipBack, skipNext;
    private MediaPlayer mediaPlayer;
    private String[] audioUrls = {
            "https://audio.jukehost.co.uk/jV32GbNpTUogY78nyiSuSpKMEitKaLtF",
            "https://audio.jukehost.co.uk/Ie8SqrGfxEftK0R8xEtTRlQ0KeOKLvik",
            "https://audio.jukehost.co.uk/8tOnixMoblnFxSry9nVqCHSxIjv2gXf9",
            "https://audio.jukehost.co.uk/AGZUgvEzTcfsGXsCflGNh4DpzwaeKKS4",
            "https://audio.jukehost.co.uk/Zsc8r58g7K1YEd9Ak1WptyS5VOuOVGBZ",
            "https://audio.jukehost.co.uk/Njx3CxKZx7Jd0fx7IWoDiCSs1eWlXhVJ",
            "https://audio.jukehost.co.uk/1X0GKPfoLi5z4jxWicWagqx2sxoFI7Q6",
            "https://audio.jukehost.co.uk/89ObPrj4Z81BYI6Vwc0LLa6dUdj4hjLq",
            "https://audio.jukehost.co.uk/eln52yZ1gx04Ame4UXj9uN6pQlIRtOfF",
            "https://audio.jukehost.co.uk/v9O76LGvPKZcaj5Xe9MIG9pV6PfEMQSR",
            "https://audio.jukehost.co.uk/jDkM5Tai2aEPpRUDY9y92UolP3NuUH4X",
            "https://audio.jukehost.co.uk/c8QUAwaypVD192JIrNK3xsOjAZPcTvVn"
    };
    //Im using https://jukehost.co.uk/library to host the links to the songs

    private int currentAudioIndex = 0;
    private TextView songNameTextView;
    private boolean prepared = false;

    private SharedViewModel sharedViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom1fragment, container, false);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        songNameTextView = view.findViewById(R.id.songNameTextView);
        play = view.findViewById(R.id.play);
        stop = view.findViewById(R.id.stop);
        pause = view.findViewById(R.id.pause);
        skipBack = view.findViewById(R.id.skipBack);
        skipNext = view.findViewById(R.id.skipNext);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer == null) {
                    setPlayer();
                } else {
                    if (!prepared) {
                        mediaPlayer.prepareAsync();
                    } else {
                        mediaPlayer.start();
                    }
                }
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                    mediaPlayer.prepareAsync();
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Media Player Null", Toast.LENGTH_SHORT).show();
                }
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                }
            }
        });

        skipBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentAudioIndex > 0) {
                    currentAudioIndex--;
                    setPlayer();
                    mediaPlayer.start();
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "No previous track", Toast.LENGTH_SHORT).show();
                }
            }
        });

        skipNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentAudioIndex < audioUrls.length - 1) {
                    currentAudioIndex++;
                    setPlayer();
                    mediaPlayer.start();
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "No next track", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void setPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioAttributes(
                new AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
        );

        try {
            mediaPlayer.setDataSource(audioUrls[currentAudioIndex]);
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) { //only works when you click on the play button, not when you start the app
                    prepared = true;
                }
            });

            mediaPlayer.prepareAsync();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        updateSongName();
    }


    private void updateSongName() {

        String[] songArray = getResources().getStringArray(R.array.Song);
        String[] authorArray = getResources().getStringArray(R.array.Author);

        if (currentAudioIndex >= 0 && currentAudioIndex < songArray.length) {
            String songName = songArray[currentAudioIndex];
            String authorName = authorArray[currentAudioIndex];

            songNameTextView.setText("Song: " + songName + " - " + authorName);
        }
    }
}