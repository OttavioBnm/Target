package data.outils;

import android.os.AsyncTask;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import data.connexion.Connect;
import domaine.Match;
import domaine.User;

/**
 * Created by ottavio on 17/10/2017.
 */

public class DBManager {
    public DBManager(){

    }

    /**
     * Essai de connexion et login
     * @param mPassword mot de passe de l'utilisateur
     * @param mEmail email de l'utilisateur
     * @return utilisateur existant ou pas
     */
    public static boolean tryLogin(String mPassword, String mEmail){
        String mdp="VIDE";
        String email = "VIDE";

        try {
            Statement st = Connect.get().createStatement();
            ResultSet res = st.executeQuery("SELECT * FROM `tutilisateur` where `email`=\""+mEmail+"\"");

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

    /**
     * Récupère les informations de l'utilisateur qui s'est connecté
     * @param mEmail email de l'utilisateur connecté
     * @return l'utilisateur a été retourné ou pas
     */
    public static boolean getUser(String mEmail){
        try {
            Statement st = Connect.get().createStatement();
            ResultSet res = st.executeQuery("SELECT tutilisateur.idUtilisateur, `nomCompte`, `eMail`, `mdp`, `nom`, `prenom`, `description`, `dateNaissance`, tcaracteristique.genre,tcaracteristique.taille,tcaracteristique.poids,tcaracteristique.emplois,tlieu.lieu,langue.langue " +
                    "FROM`tutilisateur`,tcaracteristique,tlieu,langue " +
                    "WHERE tutilisateur.idCaracteristique = tcaracteristique.idCaracteristique " +
                    "AND tcaracteristique.langue = langue.idLangue " +
                    "AND tcaracteristique.idLieu = tlieu.idLieu " +
                    "AND eMail =\"" + mEmail + "\"");

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

    public static void setLocation(int aIdUser, double aLat, double aLong){
        try {
            Statement st = Connect.get().createStatement();
            System.out.println("Lat : " + aLat + "  Long : " + aLong);
            st.executeUpdate("UPDATE `tposition` SET `latitude`="+aLat+",`longitude`="+aLong+" WHERE `idUtilisateur` = "+aIdUser+"");
            Connect.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
            Connect.close();
        }
    }

    public static void getMatches(int idCurrentUser){
        try {
            Statement st = Connect.get().createStatement();
            ResultSet res = st.executeQuery("SELECT `matchUserOne` FROM `match` WHERE `matchUserTwo`='" + idCurrentUser +"'");
            while (res.next()){
                if (!User.matchExist(res.getInt("matchUserOne"))){
                    User.add_matchs(new Match(res.getInt("matchUserOne")));
                }

            }
            Connect.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
            Connect.close();
        }

        try {
            Statement st = Connect.get().createStatement();
            ResultSet res = st.executeQuery("SELECT `matchUserTwo` FROM `match` WHERE `matchUserOne`='" + idCurrentUser +"'");
            while (res.next()){
                if (!User.matchExist(res.getInt("matchUserTwo"))){
                    User.add_matchs(new Match(res.getInt("matchUserTwo")));
                }
            }
            Connect.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
            Connect.close();
        }
    }

    public static void getPosition(){
        try {
            Statement st = Connect.get().createStatement();
            for (Match m: User.get_Matchs()) {
                ResultSet res = st.executeQuery("SELECT `latitude`, `longitude`, `tutilisateur`.`nomCompte` FROM `tposition`, `tutilisateur` WHERE `tposition`.`idUtilisateur` = '"+ m.get_idUser()+"' AND `tutilisateur`.`idUtilisateur` = '"+ m.get_idUser()+"'");
                while (res.next()){
                    m.set_lat(res.getDouble("latitude"));
                    m.set_long(res.getDouble("longitude"));
                    m.set_pseudo(res.getString("nomCompte"));
                }
            }
            Connect.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
            Connect.close();
        }
    }

    public static void checkIfNewMatch(int idCurrentUser){
        try {
            User.set_Matchs(new ArrayList());
            Statement st = Connect.get().createStatement();
            ResultSet res = st.executeQuery("SELECT COUNT(`matchUserOne`) FROM `match` WHERE `matchUserTwo`='" + idCurrentUser +"'");
            while (res.next()){
                int t = res.getInt(1);
            }
            Connect.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
            Connect.close();
        }

        try {
            Statement st = Connect.get().createStatement();
            ResultSet res = st.executeQuery("SELECT COUNT(`matchUserTwo`) FROM `match` WHERE `matchUserOne`='" + idCurrentUser +"'");
            while (res.next()){
                    int t = res.getInt(1);
                }
            Connect.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
            Connect.close();
        }
    }
}


