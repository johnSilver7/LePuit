package com.m2dl.miniprojet.activites;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by yan on 28/01/16.
 */
public class FiniActivity extends Activity {

    private TextView score, difficulte, temps;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fini);

        score = (TextView) findViewById(R.id.activite_fini_score);
        difficulte = (TextView) findViewById(R.id.activite_fini_difficulte);
        temps = (TextView) findViewById(R.id.activite_fini_temps);


        int scoreNumber = JeuActivity.calculerScore(JeuActivity.getScore(), JeuActivity.getDifficulte());
        score.setText(scoreNumber + "");
        temps.setText(JeuActivity.getTemps() + "");
        difficulte.setText(JeuActivity.getDifficulte().toString());

    }

    public void onClickSortir(View v) {
        startActivity(new Intent(this, MainActivity.class));
    }




}
