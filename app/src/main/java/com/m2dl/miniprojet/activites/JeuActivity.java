package com.m2dl.miniprojet.activites;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.m2dl.miniprojet.domaines.Photo;
import com.m2dl.miniprojet.domaines.Point;
import com.m2dl.miniprojet.domaines.Puit;

/**
 * Created by yan on 28/01/16.
 */
public class JeuActivity extends Activity {

    private Photo photo;

    private ImageView iPhoto;
    private Button bPause, bQuitter;
    private TextView tScore;
    private RelativeLayout layoutPere;

    private int largeurEcran, longueurEcran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeu);

        iPhoto = (ImageView) findViewById(R.id.activity_jeu_image_photo);
        bPause = (Button) findViewById(R.id.activity_jeu_bouton_pause);
        bQuitter = (Button) findViewById(R.id.activity_jeu_bouton_quitter);
        tScore = (TextView) findViewById(R.id.activity_jeu_texte_score);
        layoutPere = (RelativeLayout) findViewById(R.id.activity_jeu_layout_pere);

        // Recuperation des dimensions de l'ecran
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        largeurEcran = dm.widthPixels;
        longueurEcran = dm.heightPixels;

        initPhoto();
        initImage();
    }

    private void initPhoto() {
        //TODO a modifier
        photo = Photo.getListePhoto().get(0);
    }

    private void initImage() {
        int largeurPhoto = Puit.LARGEUR_PX;
        int longueurPhoto = Puit.LONGUEUR_PX;

        iPhoto.getLayoutParams().width = largeurPhoto;
        iPhoto.getLayoutParams().height = longueurPhoto;

        iPhoto.setImageBitmap(photo.getImage());

        // test obscurite
        Point pObscur = photo.getPointPlusSombre();
        ImageView iObscur = new ImageView(this);
        layoutPere.addView(iObscur);
        iObscur.getLayoutParams().width = Point.LARGEUR_PX;
        iObscur.getLayoutParams().height = Point.LONGUEUR_PX;
        iObscur.setX((largeurEcran / 20) + (pObscur.x * Point.LARGEUR_PX));
        iObscur.setY((longueurEcran / 20) + (pObscur.y * Point.LONGUEUR_PX));
        iObscur.setBackgroundDrawable(getResources().getDrawable(R.drawable.meteorite_1));
        Log.d("pObscur x y", pObscur.x + ";" + pObscur.y);
    }


}
