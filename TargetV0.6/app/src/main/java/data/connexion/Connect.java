package data.connexion;

import java.sql.Connection;
import java.sql.SQLException;

import data.outils.Outils;

/**
 * Created by ottavio on 03/10/2017.
 */

public class Connect {

    private static final String NOM_BASE = "ottaviob_target"; /* Nom de la base de données ==> RENTREZ ICI LE NOM DE VOTRE BASE DE DONNEE */

    private static Connection con = null; /* La connexion avec la base */

    /**
     * Établit la connexion et affecte con.
     */
    private static void connect() {
        try {
            con = Outils.connect(NOM_BASE);
        } catch (SQLException e) {
            System.out.println("ConnexionBase.connect(): " + e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("ConnexionBase.connect(): " + e.getMessage());
            e.printStackTrace();
        }
    } // Constructeur

    /**
     * Retourne la connexion
     */
    public static Connection get() {
        if (con == null) {
            connect();
        }
        return con;
    } // get

    /**
     * Ferme la connexion
     */
    public static void close() {
        if (con == null) {
            return;
        }
        try {
            con.close();
            con = null;
        } catch (SQLException e) {
            System.out.println("ConnexionBase.close(): " + e.getMessage());
            e.printStackTrace();
        }
    } // close
}