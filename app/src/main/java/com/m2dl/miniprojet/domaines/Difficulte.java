package com.m2dl.miniprojet.domaines;

/**
 * Created by quentin on 28/01/16.
 */
public enum Difficulte {

    FACILE, MOYEN, DIFFICILE;

    public static int getValeur(Difficulte difficulte) {
        switch (difficulte) {
            case FACILE: return 1;
            case MOYEN: return 2;
            case DIFFICILE: return 3;
        }
        return 0;
    }

}
