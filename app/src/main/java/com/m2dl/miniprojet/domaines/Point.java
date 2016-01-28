package com.m2dl.miniprojet.domaines;

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
        //TODO get obscurite
        return 0;
    }

}
