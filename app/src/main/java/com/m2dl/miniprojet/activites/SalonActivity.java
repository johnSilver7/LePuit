package com.m2dl.miniprojet.activites;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.m2dl.miniprojet.domaines.Difficulte;
import com.m2dl.miniprojet.domaines.Photo;

/**
 * Created by yan on 28/01/16.
 */
public class SalonActivity extends Activity {
    private Button bPhotoPrec, bPhotoSuiv, bJouer;
    private ImageView tPhotoPrise;
    private TextView tNumPhoto;
    private Photo photo;

    private Spinner sDifficulte;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salon);
        tPhotoPrise = (ImageView) findViewById(R.id.activite_salon_image);
        bPhotoPrec = (Button) findViewById(R.id.activite_salon_photo_precedent);
        bPhotoSuiv = (Button) findViewById(R.id.activite_salon_photo_suivante);
        tNumPhoto = (TextView) findViewById(R.id.activite_salon_num_photo);
        sDifficulte = (Spinner) findViewById(R.id.activite_salon_spinner_difficulte);

        sDifficulte.setAdapter(new ArrayAdapter<>(
                this, R.layout.spinner_layout, Difficulte.getListeString()));
        if (Photo.getListePhoto().isEmpty()) {
            onClickNouveauNiveau(null);
        } else {
            photo = Photo.getListePhoto().get(0);
            initPhoto();
            afficherInformationPhoto(photo);
            actualiserBoutons();
        }


    }

    @Override
    public void onResume() {
        super.onResume();
        if (photo != null) {
            afficherInformationPhoto(photo);
            actualiserBoutons();
        }
    }

    private void initPhoto() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        tPhotoPrise.getLayoutParams().height = dm.heightPixels * 7 / 12;
        tPhotoPrise.getLayoutParams().width = dm.widthPixels * 7 / 12;
    }

    private void actualiserBoutons() {
        bPhotoPrec.setEnabled(Photo.getListePhoto().size() > 1);
        bPhotoSuiv.setEnabled(Photo.getListePhoto().size() > 1);
    }

    private void afficherInformationPhoto(Photo photo) {
        tPhotoPrise.setBackgroundColor(Color.GRAY);
        tPhotoPrise.setBackgroundDrawable(new BitmapDrawable(photo.getImage()));
        tNumPhoto.setText((Photo.getListePhoto().indexOf(photo) + 1) + "/" + Photo.getListePhoto().size());
        actualiserBoutons();
    }

    public void getPhotoPrecedente(View v) {
        int idPhoto = Photo.getListePhoto().indexOf(photo);
        if (idPhoto - 1 >= 0) {
            photo = Photo.getListePhoto().get(idPhoto - 1);
        } else {
            photo = Photo.getListePhoto().get(Photo.getListePhoto().size() - 1);
        }
        afficherInformationPhoto(photo);
    }

    public void getPhotoSuivante(View v) {
        int idPhoto = Photo.getListePhoto().indexOf(photo);

        if (idPhoto + 1 < Photo.getListePhoto().size()) {
            photo = Photo.getListePhoto().get(idPhoto + 1);
        } else {
            photo = Photo.getListePhoto().get(0);
        }
        afficherInformationPhoto(photo);
    }

    public void onClickJouer(View v) {
        JeuActivity.setPhoto(photo);
        startActivity(new Intent(this, JeuActivity.class));
    }

    public void onClickNouveauNiveau(View v) {
        startActivity(new Intent(this, PhotoActivity.class));
    }
}
