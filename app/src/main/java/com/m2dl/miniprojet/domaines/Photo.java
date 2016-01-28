package com.m2dl.miniprojet.domaines;

import android.graphics.Bitmap;

/**
 * Created by quentin on 28/01/16.
 */
public class Photo {

    private Bitmap image;
    private String nom;
    private Point[][] points;

    private final static int NB_X = 20, NB_Y = 40;

    public Photo(String nom, Bitmap image) {
        this.nom = nom;
        this.image = image;
        initPoints();
    }

    public void initPoints() {
        //TODO peut-etre le faire dans un autre thread
    }

    public Point getPointPlusSombre() {
        for (int j = 0; j < NB_Y; j++) {
            for (int i = 0; i < NB_X; i++) {
                Point point = points[i][j];
            }
        }
    }

}
