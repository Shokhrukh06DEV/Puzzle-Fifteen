package com.example.puzzlefifteen.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.puzzlefifteen.R;

public class StartActivity extends AppCompatActivity {
    private CardView start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        start = findViewById(R.id.start_game);
        start.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(StartActivity.this, ChooseLevelActivity.class);
                        startActivity(intent);
                    }
                }
        );
    }
}