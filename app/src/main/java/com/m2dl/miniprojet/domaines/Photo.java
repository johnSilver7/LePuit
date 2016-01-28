package com.m2dl.miniprojet.domaines;

import android.graphics.Bitmap;

/**
 * Created by quentin on 28/01/16.
 */
public class Photo {

    private Bitmap image;
    private String nom;
    private Point[] points;

    private final static int NB_X = 20, NB_Y = 40;

    public Photo(String nom, Bitmap image) {
        this.nom = nom;
        this.image = image;
        initPoints();
    }

    public void initPoints() {
        points = new Point[NB_X * NB_Y];
        for (int j = 0; j < NB_Y; j++) {
            for (int i = 0; i < NB_X; i++) {
                int[] pixels = new int[Point.LARGEUR_PX * Point.LONGUEUR_PX];
                image.getPixels(pixels, 0, 0, i * Point.LARGEUR_PX, j * Point.LONGUEUR_PX,
                        Point.LARGEUR_PX, Point.LONGUEUR_PX);
            }
        }
    }

    public Point getPointPlusSombre() {
        Point pointPlusSombre = points[0];
        for (int j = 0; j < NB_Y; j++) {
            for (int i = 0; i < NB_X; i++) {
                Point point = points[j * NB_X + i];
                if (point.getObscurite() > pointPlusSombre.getObscurite()) {
                    pointPlusSombre = point;
                }
            }
        }
        return pointPlusSombre;
    }

}
