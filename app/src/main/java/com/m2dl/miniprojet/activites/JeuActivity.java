package com.m2dl.miniprojet.activites;

import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.m2dl.miniprojet.domaines.Difficulte;
import com.m2dl.miniprojet.domaines.Meteorite;
import com.m2dl.miniprojet.domaines.Photo;
import com.m2dl.miniprojet.domaines.Point;
import com.m2dl.miniprojet.domaines.Puit;

/**
 * Created by yan on 28/01/16.
 */
public class JeuActivity extends Activity {

    private int TEMPS_ENTRE_PLUIE_METEORITE = 1000;

    private static Photo photo;
    private static Difficulte difficulte;

    private ImageView iPhoto;
    private Button bPause, bQuitter;
    private TextView tScore;
    private RelativeLayout layoutPere;

    private int largeurEcran, longueurEcran;

    public static int marginImageX, marginImageY;

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

        marginImageX = largeurEcran / 20;
        marginImageY = longueurEcran / 20;

        initPhoto();
        initImage();
        initPluieMeteorite();

    }

    private void initPluieMeteorite() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                JeuActivity.this.faireTomberMeteorite();
                handler.postDelayed(this, TEMPS_ENTRE_PLUIE_METEORITE =
                        TEMPS_ENTRE_PLUIE_METEORITE - 1);
            }
        }, 3000);
    }

    public void faireTomberMeteorite() {
        ImageView imageView = new ImageView(this);
        layoutPere.addView(imageView);
        new Meteorite(imageView, getResources().getDrawable(R.drawable.meteorite_2),
                photo.getPointPlusSombre());
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
    }

    public static void setPhoto(Photo photo) {
        JeuActivity.photo = photo;
    }

    public static void setDifficulte(Difficulte difficulte) {
        JeuActivity.difficulte = difficulte;
    }


}
