package com.m2dl.miniprojet.activites;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.m2dl.miniprojet.domaines.Photo;

import java.io.File;

/**
 * Created by yan on 28/01/16.
 */
public class PhotoActivity extends Activity {

    private static int largeurEcran, longueurEcran;
    private static File photoPrise;
    private static Bitmap imagePhotoPrise;
    private int largeurPhoto = 0, longueurPhoto = 0;

    private TextView tPhoto;
    private EditText eNom;

    private final static int REQUETE_CAPTURE = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        Photo.PATH = getExternalFilesDir(null) + "/";


        // Met a jour la base de donnees
        // TODO charger la base dans un autre thread
        //ServeurService.chargerBaseDeDonnees();

        // Recuperation des dimensions de l'ecran
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        largeurEcran = dm.widthPixels;
        longueurEcran = dm.heightPixels;

        // Recuperation views
        tPhoto = (TextView) findViewById(R.id.activity_photo_image);
        eNom = (EditText) findViewById(R.id.activite_photo_name);

        afficherPhoto();
    }

    private void afficherPhoto() {
        if (largeurPhoto == 0) {
            largeurPhoto = largeurEcran * 7 / 12;
            longueurPhoto = longueurEcran * 7 / 12;
            tPhoto.getLayoutParams().height = longueurPhoto;
            tPhoto.getLayoutParams().width = largeurPhoto;
            tPhoto.setText("");
        }

        if (imagePhotoPrise == null) {
            tPhoto.setBackgroundDrawable(getResources().getDrawable(R.drawable.camera));
        } else {
            tPhoto.setBackgroundDrawable(new BitmapDrawable(imagePhotoPrise));
        }
    }

    public void onClickPrendrePhoto(View v) {

        String nom = eNom.getText().toString();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        if (nom != null) {
            photoPrise = new File(Photo.PATH + nom);
            Uri fileUri = Uri.fromFile(photoPrise);
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
            startActivityForResult(intent, REQUETE_CAPTURE);
        } else {
            builder.setMessage("Veuillez donner un nom à votre niveau avant de prendre une photo");
            builder.setNeutralButton("ok", null);
            builder.show();
        }

    }

    public void onClickAnnuler(View v) {
        imagePhotoPrise = null;
        finish();
    }

    public void onClickEnregistrer(View v) {


        finish();
    }


}
