package com.m2dl.miniprojet.domaines;

import java.util.ArrayList;
import java.util.List;

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

    public static List<String> getListeString() {
        List<String> liste = new ArrayList<>();
        for (Difficulte diff : values()) {
            liste.add(toString(diff));
        }
        return liste;
    }

    public static String toString(Difficulte difficulte) {
        switch (difficulte) {
            case FACILE: return "Facile";
            case MOYEN: return "Moyen";
            case DIFFICILE: return "Difficile";
        }
        return null;
    }

    public static Difficulte getDifficulte(String string) {
        switch (string) {
            case "Facile": return FACILE;
            case "Moyen": return MOYEN;
            case "Difficile": return DIFFICILE;
        }
        return null;
    }

}
