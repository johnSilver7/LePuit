package com.m2dl.miniprojet.domaines;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.widget.ImageView;

import com.m2dl.miniprojet.activites.JeuActivity;

import java.util.Random;

/**
 * Created by quentin on 28/01/16.
 */
public class Meteorite {

    private ImageView imageView;
    private Point destination;
    private Drawable image;
    private int xApparition, yApparition;
    private float xCourant, yCourant;
    private boolean active = true;

    private final static int NB_FOIS = 1000;
    private final static int TEMPS_ENTRE_MOUVEMENT = 100;

    public Meteorite(ImageView imageView, Drawable drawable, Point destination) {
        this.imageView = imageView;
        this.destination = destination;
        this.image = drawable;

        init();
    }

    private void init() {
        imageView.setBackgroundDrawable(image);

        xApparition = new Random().nextInt(2) * (Photo.NB_X * Point.LARGEUR_PX);
        yApparition = new Random().nextInt(Photo.NB_Y) * Point.LONGUEUR_PX;

        imageView.getLayoutParams().width = Point.LARGEUR_PX;
        imageView.getLayoutParams().height = Point.LONGUEUR_PX;

        bougerVers(xCourant = xApparition, yCourant = yApparition);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                /*active = Math.abs(xCourant - destination.x)
                        > Math.abs(xApparition - destination.x) / NB_FOIS;*/
                if (active) {
                    xCourant += (xCourant - (float) destination.x) / (float) NB_FOIS;
                    yCourant += (yCourant - (float) destination.y) / (float) NB_FOIS;
                    Meteorite.this.bougerVers(xCourant, yCourant);
                }
                handler.postDelayed(this, TEMPS_ENTRE_MOUVEMENT);
            }
        }, 0);
    }

    private void bougerVers(float xImage, float yImage) {
        imageView.setX(JeuActivity.marginImageX + (xImage * Point.LARGEUR_PX));
        imageView.setY(JeuActivity.marginImageY + 72 + (yImage * Point.LONGUEUR_PX));
    }
}
