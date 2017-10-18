package metier;

import java.util.Calendar;

import domaine.User;

/**
 * Created by ottavio on 17/10/2017.
 */

public class HomeManagement {

    public static String[][] elementsProfil(){
        String[][] profil = new String[13][2];
        profil[0][0] = "Pseudo";
        profil[0][1] = User.get_pseudo();
        profil[1][0] = "Description";
        profil[1][1] = User.get_description();
        profil[2][0] = "Lieu";
        profil[2][1] = User.get_lieu();
        profil[3][0] = "Genre";
        profil[3][1] = User.get_genre();
        profil[4][0] = "Âge";
        profil[4][1] = String.valueOf(Calendar.getInstance().get(Calendar.YEAR)-1900-User.get_naissance().getYear());
        profil[5][0] = "Langue";
        profil[5][1] = User.get_langue();
        profil[6][0] = "Taille";
        profil[6][1] = String.valueOf(User.get_taille());
        profil[7][0] = "Poids";
        profil[7][1] = String.valueOf(User.get_poids());
        profil[8][0] = "Emploi";
        profil[8][1] = User.get_emploi();
        return profil;
    }
}
