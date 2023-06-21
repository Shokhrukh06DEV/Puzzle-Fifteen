package com.example.puzzlefifteen.ui;

import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.puzzlefifteen.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;
public class MainActivity4x4 extends AppCompatActivity {
    private TextView timeView;
    private TextView stepView;
    Timer timer;
    private int timeCount = 0;
    private int stepCount = 0;
    private int emptyI = 3;
    private int emptyJ = 3;
    private RelativeLayout btnGroup;
    private MediaPlayer tap_sound;
    private final TextView[][] buttons = new TextView[4][4];
    private final CardView[][] cards = new CardView[4][4];
    private ArrayList<Integer> numbers = new ArrayList<>();
    private ImageButton pauseButton;
    private ImageButton refreshButton;
    private FrameLayout pauseView;
    private CardView resumeButton;
    private RelativeLayout mainGame;
    private CardView exitButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadView();
        loadData();
        loadDataToView();
        loadActions();


        pauseButton = findViewById(R.id.pause_button);
        exitButton = findViewById(R.id.exit_button);
        refreshButton = findViewById(R.id.refresh_button);
        resumeButton = findViewById(R.id.resume_button);
        tap_sound = MediaPlayer.create(this,R.raw.click_sonud);
        pauseButton.setOnClickListener(this::onPauseClick);
        resumeButton.setOnClickListener(this::onResumeClick);
        refreshButton.setOnClickListener(this::onRefreshClick);
        exitButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MainActivity4x4.this, StartActivity.class);
                        startActivity(intent);
                    }
                }
        );

    }
    private void onRefreshClick(View view) {

        stepCount = 0;
        timeCount = 0;

        cards[emptyI][emptyJ].setVisibility(View.VISIBLE);

        emptyI = 3;
        emptyJ = 3;

        loadData();
        loadDataToView();

    }

    private void onResumeClick(View view) {
        mainGame = findViewById(R.id.main_game);
        mainGame.setVisibility(View.VISIBLE);
        pauseView = findViewById(R.id.pause_view);
        refreshButton = findViewById(R.id.refresh_button);
        refreshButton.setVisibility(View.VISIBLE);
        pauseButton.setVisibility(View.VISIBLE);
        pauseView.setVisibility(View.GONE);
        if (timer != null){
            timer.cancel();
            timer = null;
        }
        timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                countTimer();
            }
        };
        timer.schedule(timerTask,1000,1000);

    }

    private void onPauseClick(View view) {
        pauseView = findViewById(R.id.pause_view);
        mainGame = findViewById(R.id.main_game);
        refreshButton = findViewById(R.id.refresh_button);
        pauseView.setVisibility(View.VISIBLE);
        refreshButton.setVisibility(View.GONE);
        pauseButton.setVisibility(View.GONE);
        mainGame.setVisibility(View.GONE);
        timer.cancel();
    }


    private void loadActions() {
        for (int i = 0; i < 16; i++) {
            buttons[i/4][i%4].setOnClickListener(this::onButtonClick);
        }
    }
    private void onButtonClick(View view){
        TextView textView = (TextView) view;
        String hint = textView.getHint().toString();

        int i = Integer.valueOf(hint.split(":")[0]);
        int j = Integer.valueOf(hint.split(":")[1]);

        int deltaI = Math.abs(i-emptyI);
        int deltaJ = Math.abs(j-emptyJ);
        if(deltaI+deltaJ == 1){
            stepCount++;
            buttons[emptyI][emptyJ].setText(textView.getText());
            cards[emptyI][emptyJ].setVisibility(View.VISIBLE);

            buttons[i][j].setText("");
            cards[i][j].setVisibility(View.INVISIBLE);
            emptyJ = j;
            emptyI = i;
            stepView.setText("Steps:"+ String.valueOf(stepCount));
            soundPlayer(tap_sound);
            if(emptyJ == 3 && emptyI == 3){
                checkWin();
            }
        }
    }
    private void checkWin() {
        for (int i = 0; i < 14; i++) {
            int current = Integer.valueOf( buttons[i/4][i%4].getText().toString());
            int next = Integer.valueOf( buttons[(i+1)/4][(i+1)%4].getText().toString());
            if (current>next) {
                return;
            }
        }
        Intent intent = new Intent(MainActivity4x4.this,WinActivity.class);
        startActivity(intent);
    }

    private void soundPlayer(MediaPlayer sound) {
        sound.start();
    }

    private void loadDataToView() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                countTimer();
            }
        };
        timer.schedule(timerTask, 1000, 1000);

        cards[emptyI][emptyJ].setVisibility(View.INVISIBLE);

        for (int i = 0; i < 15; i++) {
            buttons[i / 4][i % 4].setText(numbers.get(i).toString());

        }
    }
    private void loadData() {
        for (int i = 1; i < 16; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
    }

    private void loadView() {
        timeView = findViewById(R.id.time_text);
        stepView = findViewById(R.id.step_text);
        btnGroup = findViewById(R.id.main_game);

        for (int i = 0; i < 16; i++) {
            CardView cardView = (CardView) btnGroup.getChildAt(i);
            cards[i/4][i%4] = cardView;
            TextView textView = (TextView) cardView.getChildAt(1);
            buttons[i/4][i%4] = textView;
        }
        cards[emptyI][emptyJ].setVisibility(View.INVISIBLE);
    }
    private void countTimer() {
        timeCount++;
        int second = timeCount % 60;
        int minute = timeCount /60 % 60;
        String timeFormat = String.format("%02d:%02d",minute,second);
        timeView.setText("Time:"+timeFormat);
    }
}
