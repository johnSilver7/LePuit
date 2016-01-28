package com.m2dl.miniprojet.activites;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
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
public class JeuActivity extends Activity implements SensorEventListener {

    private int TEMPS_ENTRE_PLUIE_METEORITE = 1000;

    private static Photo photo;
    private static Difficulte difficulte;

    private ImageView iPhoto;
    private Button bPause, bQuitter;
    private TextView tScore;
    private RelativeLayout layoutPere;
    private ImageView imageBille;

    public static int largeurEcran, longueurEcran;

    public static int marginImageX, marginImageY;

    private SensorManager sm;

    private int dYPrev, dYCour, dZPrev, dZCour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeu);

        iPhoto = (ImageView) findViewById(R.id.activity_jeu_image_photo);
        bPause = (Button) findViewById(R.id.activity_jeu_bouton_pause);
        bQuitter = (Button) findViewById(R.id.activity_jeu_bouton_quitter);
        tScore = (TextView) findViewById(R.id.activity_jeu_texte_score);
        layoutPere = (RelativeLayout) findViewById(R.id.activity_jeu_layout_pere);
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);

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
        initImageBille();

        dYCour = dYPrev = dZPrev = dZCour = 0;

        verifPerdu();
    }

    private void verifPerdu() {
        boolean perdu = photo.aPerdu((int) imageBille.getX(), (int) imageBille.getY());
        if (perdu) {
            startActivity(new Intent(this, FiniActivity.class));
            finish();
        }
    }

    private void initImageBille() {
        imageBille = new ImageView(this);
        layoutPere.addView(imageBille);
        imageBille.setBackgroundDrawable(getResources().getDrawable(R.drawable.grosse_bille));
        imageBille.setX(largeurEcran / 2);
        imageBille.setY(largeurEcran / 2);
        imageBille.getLayoutParams().width = Point.LARGEUR_PX;
        imageBille.getLayoutParams().height = Point.LONGUEUR_PX;
    }

    private void initPluieMeteorite() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                JeuActivity.this.faireTomberMeteorite();
                TEMPS_ENTRE_PLUIE_METEORITE = TEMPS_ENTRE_PLUIE_METEORITE - 1;
                if (TEMPS_ENTRE_PLUIE_METEORITE < 0) {
                    TEMPS_ENTRE_PLUIE_METEORITE = 0;
                }
                handler.postDelayed(this, TEMPS_ENTRE_PLUIE_METEORITE);
            }
        }, 3000);
    }

    public void faireTomberMeteorite() {
        ImageView imageView = new ImageView(this);
        layoutPere.addView(imageView);
        //new Meteorite(imageView, getResources().getDrawable(R.drawable.meteorite_2),
        //        photo.getPointPlusSombre());
        Point pos = photo.getPointPlusSombre();
        imageView.setBackgroundDrawable(getResources().getDrawable(R.drawable.meteorite_2));
        imageView.setX(marginImageX + (pos.x * Point.LARGEUR_PX));
        imageView.setY(marginImageY + 72 + (pos.y * Point.LONGUEUR_PX));
        imageView.getLayoutParams().width = Point.LARGEUR_PX;
        imageView.getLayoutParams().height = Point.LONGUEUR_PX;
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

    @Override
    protected void onResume() {
        super.onResume();
        Sensor mMagneticField = sm.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        sm.registerListener(this, mMagneticField, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onStop() {
        sm.unregisterListener(this, sm.getDefaultSensor(Sensor.TYPE_ORIENTATION));
        super.onStop();
    }

    public void onSensorChanged(SensorEvent event) {
        int sensor = event.sensor.getType();

        synchronized (this) {
            if (sensor == Sensor.TYPE_ORIENTATION) {
                dYPrev = dYCour;
                dZPrev = dZCour;

                dYCour = (int) event.values[1];
                dZCour = (int) event.values[2];

                bougerBille();
            }
        }
    }

    private void bougerBille() {
        int decaleY = dYCour - dYPrev; // hauteur
        int decaleZ = dZCour - dZPrev; // largeur

        decaleY = (decaleY * longueurEcran) / 90;
        decaleZ = (decaleZ * largeurEcran) / 90;

        int y = (int) imageBille.getY() - decaleY;
        int x = (int) imageBille.getX() - decaleZ;

        if (x < marginImageX) x = marginImageX;
        if (x > largeurEcran - marginImageX - Point.LARGEUR_PX)
            x = largeurEcran - marginImageX - Point.LARGEUR_PX;

        if (y < marginImageY + 72) y = marginImageY + 72;
        if (y > longueurEcran - marginImageY - Point.LONGUEUR_PX - 124)
            y = longueurEcran - marginImageY - Point.LONGUEUR_PX - 124;

        imageBille.setX(x);
        imageBille.setY(y);

        verifPerdu();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Rien
    }


}
