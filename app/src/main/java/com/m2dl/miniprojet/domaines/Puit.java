package com.m2dl.miniprojet.domaines;

import android.graphics.Bitmap;

/**
 * Created by quentin on 28/01/16.
 */
public class Puit {

    private Photo photo;
    private Joueur joueur;

    public static int LARGEUR_PX, LONGUEUR_PX;

    public Puit(Photo photo, Joueur joueur) {
        this.photo = photo;
        this.joueur = joueur;
    }

    public int calculerScore(int temps, Difficulte difficulte) {
        return temps * 5 * Difficulte.getValeur(difficulte);
    }

}
