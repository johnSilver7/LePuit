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
    private int xCourant, yCourant;
    private boolean active = true;

    private final static int NB_FOIS = 50;
    private final static int TEMPS_ENTRE_MOUVEMENT = 100;

    public Meteorite(ImageView imageView, Drawable drawable, Point destination) {
        this.imageView = imageView;
        this.destination = destination;
        this.image = drawable;

        init();
    }

    private void init() {
        imageView.setBackgroundDrawable(image);

        xApparition = new Random().nextInt(2) * Photo.NB_X;
        yApparition = new Random().nextInt(Photo.NB_Y);

        imageView.getLayoutParams().width = Point.LARGEUR_PX;
        imageView.getLayoutParams().height = Point.LONGUEUR_PX;

        bougerVers(xApparition, yApparition);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (active) {
                    xCourant = (xCourant - destination.x) / NB_FOIS;
                    yCourant = (yCourant - destination.y) / NB_FOIS;
                    Meteorite.this.bougerVers(xCourant, yCourant);
                }
                handler.postDelayed(this, TEMPS_ENTRE_MOUVEMENT);
            }
        }, 0);
    }

    private void bougerVers(int xImage, int yImage) {
        imageView.setX(JeuActivity.marginImageX + (xImage * Point.LARGEUR_PX));
        imageView.setY(JeuActivity.marginImageY + (yImage * Point.LONGUEUR_PX));
    }
}
