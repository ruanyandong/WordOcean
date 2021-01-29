package com.ryd.wordocean;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = (Button) findViewById(R.id.cet4);
        button1.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CET4.class);
            startActivity(intent);
        });

        Button button2 = (Button) findViewById(R.id.cet6);
        button2.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CET6.class);
            startActivity(intent);
        });
    }
}