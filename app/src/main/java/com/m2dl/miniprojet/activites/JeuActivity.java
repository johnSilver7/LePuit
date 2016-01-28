package com.m2dl.miniprojet.activites;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.m2dl.miniprojet.domaines.Photo;

/**
 * Created by yan on 28/01/16.
 */
public class JeuActivity extends Activity {

    private Photo photo;

    private ImageView iPhoto;
    private Button bPause, bQuitter;
    private TextView tScore;

    private int largeurEcran, longueurEcran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeu);

        iPhoto = (ImageView) findViewById(R.id.activity_jeu_image_photo);
        bPause = (Button) findViewById(R.id.activity_jeu_bouton_pause);
        bQuitter = (Button) findViewById(R.id.activity_jeu_bouton_quitter);
        tScore = (TextView) findViewById(R.id.activity_jeu_texte_score);

        // Recuperation des dimensions de l'ecran
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        largeurEcran = dm.widthPixels;
        longueurEcran = dm.heightPixels;

        initImage();
    }

    private void initImage() {
        int largeurPhoto = largeurEcran * 9 / 10;
        int longueurPhoto = longueurEcran * 9 / 10;

        iPhoto.getLayoutParams().width = largeurPhoto;
        iPhoto.getLayoutParams().height = longueurPhoto;

        //TODO a enlever
        //photo = new Photo();

        iPhoto.setImageBitmap(photo.getImage());
    }


}
