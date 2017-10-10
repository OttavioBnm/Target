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

    Connection con;
    String requete;
    ResultSet res;
    ArrayList result = new ArrayList();

    public void Connect(){
        // Essai de connexion à la base de donnée
        try {
            // Chargement du driver
            Class.forName("com.mysql.jdbc.Driver");

            // Connecteur à la base avec les paramètres (Adresse+Nom_Base, Nom_Utilisateur, Password)
            con = DriverManager.getConnection("jdbc:mysql://192.168.43.165:3306/db_target", "mbp-di-ottavio", "root");
            System.out.println("Database connection success");

            // Requête sur la base
            Statement st = con.createStatement();
            res = st.executeQuery(requete);

            while (res.next()) {
                result.add(res.getString(1));
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }
    }
    public void SetRequete(String param) {
        requete = param;
    }

    public ResultSet GetResultat() {
        return res;
    }

    public ArrayList getListeRes(){
        return result;
    }
}
