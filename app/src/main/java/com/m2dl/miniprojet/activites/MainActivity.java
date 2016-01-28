package com.m2dl.miniprojet.activites;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;


import com.m2dl.miniprojet.domaines.Photo;
import com.m2dl.miniprojet.domaines.Point;
import com.m2dl.miniprojet.domaines.Puit;

import java.io.File;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Recuperation des dimensions de l'ecran
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        Puit.LARGEUR_PX = dm.widthPixels * 9 / 10;
        Puit.LONGUEUR_PX = dm.heightPixels * 9 / 10;

        chargerListePhoto();

    }

    public void onClickJouer(View v) {
        //startActivity(new Intent(this, SalonActivity.class));
        //startActivity(new Intent(this, JeuActivity.class));
        startActivity(new Intent(this, SalonActivity.class));
    }

    public void onClickTestAccesJeu(View v) {
        startActivity(new Intent(this, JeuActivity.class));
    }

    @Override
    public void onBackPressed() {
        // rien
    }

    public void chargerListePhoto() {

        Photo.PATH = getExternalFilesDir(null) + "/";

        File folder = new File(Photo.PATH);
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                String nom = stripExtension(listOfFiles[i].getName());
                Bitmap image = BitmapFactory.decodeFile(listOfFiles[i].getAbsolutePath());
                Photo photo = new Photo(nom, image);
                Photo.addListePhoto(photo);
            }
        }
    }

    static String stripExtension(String str) {
        // Handle null case specially.

        if (str == null) return null;

        // Get position of last '.'.

        int pos = str.lastIndexOf(".");

        // If there wasn't any '.' just return the string as is.

        if (pos == -1) return str;

        // Otherwise return the string, up to the dot.

        return str.substring(0, pos);
    }
}
