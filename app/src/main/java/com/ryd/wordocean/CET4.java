package com.ryd.wordocean;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;


public class CET4 extends AppCompatActivity {
    private static final String TAG = "CET4";
    private ImageButton mPrevButton;
    private ImageButton mNextButton;
    private TextView mWordView;

    private static final String KEY_INDEX = "index";

    private String wordStr = null;
    private String[] words = null;

    private int mCurrentIndex = 0;

    private void upWord() {
        String word = words[mCurrentIndex];
        mWordView.setText(word);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cet4);


        wordStr = getString(this.getResources().openRawResource(R.raw.cet4));
        words = wordStr.split("\n");

        mWordView = (TextView) findViewById(R.id.word);
        mWordView.setOnClickListener(v -> {
            mCurrentIndex = (mCurrentIndex + 1) % words.length;
            upWord();
        });

        mPrevButton = (ImageButton) findViewById(R.id.prev_button);
        mPrevButton.setOnClickListener(v -> {
            if (mCurrentIndex == 0) {
                mCurrentIndex = words.length - 1;
            } else {
                mCurrentIndex = mCurrentIndex - 1;
            }
            upWord();
        });

        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentIndex == words.length - 1) {
                    mCurrentIndex = 0;
                } else {
                    mCurrentIndex = (mCurrentIndex + 1) % words.length;
                }
                upWord();
            }
        });

        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }

        upWord();

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
    }

    private static String getString(InputStream inputStream) {
        if (inputStream == null) {
            Log.d(TAG, "inputStream==null");
        }
        InputStreamReader inputStreamReader = null;
        try {
            inputStreamReader = new InputStreamReader(inputStream, "gbk");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(inputStreamReader);
        StringBuffer sb = new StringBuffer("");
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
