package com.m2dl.miniprojet.domaines;

/**
 * Created by quentin on 28/01/16.
 */
public class Point {

    private int x, y;
    private boolean valide;

    public static int LARGEUR_PX, LONGUEUR_PX;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
        valide = true;
    }

    public int getObscurite() {
        //TODO get obscurite
        return 0;
    }

}
