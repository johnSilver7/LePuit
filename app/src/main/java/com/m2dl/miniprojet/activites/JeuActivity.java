package com.m2dl.miniprojet.activites;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by yan on 28/01/16.
 */
public class JeuActivity extends Activity {

    private ImageView iPhoto;
    private Button bPause;
    private TextView tScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeu);
    }
}
