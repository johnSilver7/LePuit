package com.m2dl.miniprojet.activites;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.m2dl.miniprojet.domaines.Photo;

import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickJouer(View v) {
        //startActivity(new Intent(this, SalonActivity.class));
        //startActivity(new Intent(this, JeuActivity.class));
        startActivity(new Intent(this, PhotoActivity.class));
    }

    public void onClickTestAccesJeu(View v) {
        startActivity(new Intent(this, JeuActivity.class));
    }

    @Override
    public void onBackPressed() {
        // rien
    }
}
