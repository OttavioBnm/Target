package data.outils;

import android.os.AsyncTask;

import java.sql.ResultSet;
import java.sql.Statement;

import data.connexion.Connect;
import domaine.User;

/**
 * Created by ottavio on 17/10/2017.
 */

public class DBManager {
    public DBManager(){

    }

    public static boolean tryLogin(String mPassword, String mEmail){
        String mdp="VIDE";
        String email = "VIDE";

        try {
            Statement st = Connect.get().createStatement();
            ResultSet res = st.executeQuery("SELECT * FROM `tUtilisateur` where `email`=\""+mEmail+"\"");

            while (res.next()) {
                email = res.getString("email");
                mdp = res.getString("mdp");

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Connect.close();
            return false;
        }

        if(!email.isEmpty())
        {

            if(mdp.equals(mPassword))
            {
                Connect.close();
                return true;
            }
            else{
                Connect.close();
                return false;
            }
        }
        else{
            Connect.close();
            return false;
        }
    }

    public static boolean getUser(String mEmail){
        try {
            Statement st = Connect.get().createStatement();
            ResultSet res = st.executeQuery("SELECT  `genre`, `taille`, `poids`, `emplois` , tUtilisateur.idUtilisateur, `nomCompte`, `eMail`, `mdp`, `nom`, `prenom`, `description`, `dateNaissance`,`langue`,`lieu` FROM `tCaracteristique`,`tUtilisateur`,`langue`,`tLieu` WHERE tCaracteristique.idCaracteristique=tUtilisateur.idCaracteristique AND\n" +
                    "tLieu.idLieu = tCaracteristique.idLieu AND\n" +
                    "langue.idLangue = tCaracteristique.idLangue AND\n" +
                    "eMail = \""+mEmail+"\"");
            while (res.next()){
                User.set_genre(res.getString("genre"));
                User.set_taille(res.getInt("taille"));
                User.set_poids(res.getInt("poids"));
                User.set_emploi(res.getString("emplois"));
                User.set_idUser(res.getInt("idUtilisateur"));
                User.set_pseudo(res.getString("nomCompte"));
                User.set_email(res.getString("eMail"));
                User.set_password(res.getString("mdp"));
                User.set_nom(res.getString("nom"));
                User.set_prenom(res.getString("prenom"));
                User.set_description(res.getString("description"));
                User.set_naissance(res.getDate("dateNaissance"));
                User.set_langue(res.getString("langue"));
                User.set_lieu(res.getString("lieu"));
            }
            Connect.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Connect.close();
            return false;
        }
    }
}


