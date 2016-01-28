package com.m2dl.miniprojet.domaines;

import android.graphics.Bitmap;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.util.Log;

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

    public static int NB_X = 20, NB_Y = 40;


    private static List<Photo> listePhoto = new ArrayList<>();

    public Photo(String nom, Bitmap image) {
        this.nom = nom;
        this.image = image;
        initPoints();
    }

    public void initPoints() {
        Point.LARGEUR_PX = Puit.LARGEUR_PX / NB_X;
        Point.LONGUEUR_PX = Puit.LONGUEUR_PX / NB_Y;

        points = new Point[NB_X * NB_Y];
        for (int j = 0; j < NB_Y; j++) {
            for (int i = 0; i < NB_X; i++) {
                Log.d("pixels", "(" + i + ";" + j + ")");
                int[] pixels = new int[Point.LARGEUR_PX * Point.LONGUEUR_PX];
                image.getPixels(pixels, 0, Point.LARGEUR_PX, i * Point.LARGEUR_PX,
                        j * Point.LONGUEUR_PX, Point.LARGEUR_PX, Point.LONGUEUR_PX);
                points[j * NB_X + i] = new Point(i, j, pixels);
            }
        }
    }

    public Point getPointPlusSombre() {
        Point pointPlusSombre = points[0];
        for (int j = 0; j < NB_Y; j++) {
            for (int i = 0; i < NB_X; i++) {
                Point point = points[j * NB_X + i];
                if (point.isValide() && point.getObscurite() < pointPlusSombre.getObscurite()) {
                    pointPlusSombre = point;
                }
            }
        }
        pointPlusSombre.setValide(false);
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
