package com.example.puzzlefifteen.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.puzzlefifteen.R;

public class ChooseLevelActivity extends AppCompatActivity {
    private CardView easy,medium,hard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_level);
        easy = findViewById(R.id.easy_view);
        medium = findViewById(R.id.medium_view);
        hard = findViewById(R.id.hard_view);

        easy.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(ChooseLevelActivity.this, MainActivity3x3.class);
                        startActivity(intent);
                    }
                }
        );
        medium.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(ChooseLevelActivity.this, MainActivity4x4.class);
                        startActivity(intent);
                    }
                }
        );
        hard.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(ChooseLevelActivity.this, MainActivity5x5.class);
                        startActivity(intent);
                    }
                }
        );
    }
}