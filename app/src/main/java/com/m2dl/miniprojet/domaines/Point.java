package com.m2dl.miniprojet.domaines;


import android.graphics.Color;

/**
 * Created by quentin on 28/01/16.
 */
public class Point {

    private int x, y;
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
        int somme = 0;
        for(int i=0; i < LARGEUR_PX * LONGUEUR_PX; i++) {
            int red = (pixels[i] >> 16) & 0xFF;
            int green = (pixels[i] >> 8) & 0xFF;
            int blue = pixels[i] & 0xFF;
            somme += Color.rgb(red, green, blue);
        }
        return somme;
    }

}
