package com.sergiocasero.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.sergiocasero.R;
import com.sergiocasero.revealfab.RevealFAB;

/**
 * Created by sergiocasero on 15/4/16.
 */
public class FirstActivity extends AppCompatActivity {

    private RevealFAB revealFAB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        revealFAB = (RevealFAB) findViewById(R.id.reveal_fab);


        revealFAB.setIntent(new Intent(FirstActivity.this, SecondActivity.class));

        revealFAB.setOnClickListener(new RevealFAB.OnClickListener() {
            @Override
            public void onClick(RevealFAB button, View v) {
                button.startActivityWithAnimation();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        revealFAB.onResume();
    }
}
