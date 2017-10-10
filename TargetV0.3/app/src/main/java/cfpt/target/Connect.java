package cfpt.target;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by ottavio on 03/10/2017.
 */

public class Connect {

    private Connection con;
    ResultSet res;
    final  String ADRESSE = "jdbc:mysql://jamalalb.mysql.db.hostpoint.ch";
    final String NOM_BASE = "jamalalb_target";
    final String NOM_UTILISATEUR = "jamalalb_admin";
    final String PASSWORD ="roottoor+7-7";

    public void Connect(String requete){

        // Essai de connexion à la base de donnée
        try {
            // Chargement du driver
            Class.forName("com.mysql.jdbc.Driver");

            // Connecteur à la base avec les paramètres (Adresse+Nom_Base, Nom_Utilisateur, Password)
            con = DriverManager.getConnection(ADRESSE+"/"+NOM_BASE,NOM_UTILISATEUR,PASSWORD);
            System.out.println("Database connection success");

            // Requête sur la base
            Statement st = con.createStatement();
            res = st.executeQuery(requete);
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
    }
    private  ArrayList ResultToList()
    {
        ArrayList rs = new ArrayList();
        try {
            while (res.next()) {
                rs.add(res.getString(1));
            }
        }
        catch(Exception e)
        {
            e.getMessage();
        }
        return rs;
    }
    private void ResulToArray(ResultSet rs)
    {
        /*try {
            while (rs.next()) {
                result.add(rs.getString(1));
            }
        }
        catch(Exception e)
        {
            e.getMessage();
        }*/
    }
    public boolean Login(String email,String password)
    {
        try {
            Connect("SELECT `prenom` FROM `tUtilisateur` WHERE `email`=\""+email+"\"");
            if (!ResultToList().isEmpty()){
                Connect("SELECT `mdp` FROM `tUtilisateur` WHERE `email`=\""+email+"\"");

                if(ResultToList().get(0).equals(password)){
                    con.close();
                    return true;
                }
                else{
                    con.close();
                    return false;
                }
            }
            else{
                con.close();
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}
