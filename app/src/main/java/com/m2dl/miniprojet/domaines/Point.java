package com.m2dl.miniprojet.domaines;


import android.graphics.Color;
import android.util.Log;

/**
 * Created by quentin on 28/01/16.
 */
public class Point {

    public final int x, y;
    private boolean valide;
    private int[] pixels;

    public static int LARGEUR_PX, LONGUEUR_PX;

    public Point(int x, int y, int[] pixels) {
        this.x = x;
        this.y = y;
        this.valide = true;
        this.pixels = pixels;
    }

    public int getObscurite() {
        double somme = 0.0;
        for (int i = 0; i < LARGEUR_PX * LONGUEUR_PX; i++) {
            int red = (pixels[i] >> 16) & 0xFF;
            int green = (pixels[i] >> 8) & 0xFF;
            int blue = pixels[i] & 0xFF;
            int grayLevel = (red + green + blue) / 3;
            double gray = (grayLevel << 16) + (grayLevel << 8) + grayLevel;
            somme += (gray / (double) (LARGEUR_PX * LONGUEUR_PX));
        }
        Log.d("obscurite(" + x + ";" + y + ")", "" + somme);
        return (int) somme;
    }

    public boolean isValide() {
        return valide;
    }

    public void setValide(boolean valide) {
        this.valide = valide;
    }

}
