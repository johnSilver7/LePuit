package com.m2dl.miniprojet.domaines;
import android.graphics.Bitmap;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.util.Log;
import com.m2dl.miniprojet.activites.JeuActivity;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * Created by quentin on 28/01/16.
 */
public class Photo {
    private Bitmap image;
    private String nom;
    private Point[] points;
    public static String PATH = "";
    public static int NB_X = 10, NB_Y = 13;
    private static List<Photo> listePhoto = new ArrayList<>();
    public Photo(String nom, Bitmap image) {
        this.nom = nom;
        this.image = image;
        initPoints();
    }
    public void initPoints() {
        Point.LARGEUR_PX = Puit.LARGEUR_PX / NB_X;
        Point.LONGUEUR_PX = Puit.LONGUEUR_PX / NB_Y;
        points = new Point[NB_X * NB_Y];
        for (int j = 0; j < NB_Y; j++) {
            for (int i = 0; i < NB_X; i++) {
                Log.d("pixels", "(" + i + ";" + j + ")");
                int[] pixels = new int[Point.LARGEUR_PX * Point.LONGUEUR_PX];
                image.getPixels(pixels, 0, Point.LARGEUR_PX, i * Point.LARGEUR_PX,
                        j * Point.LONGUEUR_PX, Point.LARGEUR_PX, Point.LONGUEUR_PX);
                points[j * NB_X + i] = new Point(i, j, pixels);
            }
        }
    }
    public Point getPointPlusSombre() {
/*Point pointPlusSombre = null;
for (int i = 0 ; i < NB_X * NB_Y; i++) {
if (points[i].isValide()) {
pointPlusSombre = points[i];
break;
}
}
for (int j = 0; j < NB_Y; j++) {
for (int i = 0; i < NB_X; i++) {
Point point = points[j * NB_X + i];
if (point.isValide() && point.getObscurite() < pointPlusSombre.getObscurite()) {
pointPlusSombre = point;
}
}
}
pointPlusSombre.setValide(false);
return pointPlusSombre;*/
        Point point = null;
        for (int i = 0; point == null && i < 1000; i++) {
            int xAleat = new Random().nextInt(NB_X);
            int yAleat = new Random().nextInt(NB_Y);
            if (points[yAleat * NB_X + xAleat].isValide()) {
                point = points[yAleat * NB_X + xAleat];
                point.setValide(false);
            }
        }
        return point;
    }
    public Bitmap getImage() {
        return image;
    }
    public static List<Photo> getListePhoto() {
        return listePhoto;
    }
    public static void addListePhoto(Photo photo) {
        if (!listePhoto.contains(photo)) {
            listePhoto.add(photo);
        }
    }
    public boolean aPerdu(int x, int y) {
        int xImage = x - JeuActivity.marginImageX;
        int yImage = y - JeuActivity.marginImageY - 72;
        int x1ImageTab = (xImage * NB_X) / Puit.LARGEUR_PX;
        int y1ImageTab = (yImage * NB_Y) / Puit.LONGUEUR_PX;
        if (!points[y1ImageTab * NB_X + x1ImageTab].isValide()) {
            return true;
        } else {
            return false;
        }
    }
}