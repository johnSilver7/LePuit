package com.m2dl.miniprojet.domaines;

import android.graphics.Bitmap;
import android.telephony.PhoneNumberFormattingTextWatcher;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by quentin on 28/01/16.
 */
public class Photo {

    private Bitmap image;
    private String nom;
    private Point[] points;
    public static String PATH = "";

    private final static int NB_X = 20, NB_Y = 40;

    private static List<Photo> listePhoto = new ArrayList<>();

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

    public Bitmap getImage() {
        return image;
    }

    public static List<Photo> getListePhoto() {
        return listePhoto;
    }

    public static void addListePhoto(Photo photo) {

        if (!listePhoto.contains(photo)) {
            listePhoto.add(photo);
        }
    }

}
