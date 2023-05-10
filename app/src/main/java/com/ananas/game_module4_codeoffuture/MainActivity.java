package com.ananas.game_module4_codeoffuture;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView playText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playText = findViewById(R.id.play);

        playText.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), GameActivity.class)));

    }
}