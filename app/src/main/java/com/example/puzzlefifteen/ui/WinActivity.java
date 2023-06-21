package com.example.puzzlefifteen.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.puzzlefifteen.R;

import java.util.Locale;
import java.util.Scanner;

public class WinActivity extends AppCompatActivity {

    private MediaPlayer win_sound;
    private TextView steps,time;
    private CardView newGame,goMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);

        steps = findViewById(R.id.step_score);
        time = findViewById(R.id.time_score);
        newGame = findViewById(R.id.new_game);
        goMenu = findViewById(R.id.go_menu);

        win_sound = MediaPlayer.create(this,R.raw.win_sound);
        soundPlayer(win_sound);

        newGame.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(WinActivity.this, ChooseLevelActivity.class);
                        startActivity(intent);
                    }
                }
        );
        goMenu.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(WinActivity.this, StartActivity.class);
                        startActivity(intent);
                    }
                }
        );
    }
    private void soundPlayer(MediaPlayer win_sound) {
        win_sound.start();
    }
}