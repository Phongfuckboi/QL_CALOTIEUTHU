package com.example.congnghephanmembtl;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

public class SprennActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sprenn);
        @SuppressLint("CommitPrefEdits") Thread thread = new Thread(() ->
        {
            try {
                Thread.sleep(2000);
            }
            catch (Exception e)
            {}
            finally {
                Intent iDangNhap = new Intent(this, DangNhapActivity.class);
                startActivity(iDangNhap);
                finish();
            }

        });
        thread.start();
        }
    }
