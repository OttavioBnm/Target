package data.outils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by ottavio on 26/09/2017.
 */

public class Outils {
    /** Retourne une connexion avec une base de données MySQL. */
    public static Connection connect (String nomBase) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Properties props = new Properties();
        props.put("user", "jamalalb_admin"); props.put("password", "roottoor+7-7"); props.put("charSet", "UTF-8");
        return DriverManager.getConnection("jdbc:mysql://jamalalb.mysql.db.hostpoint.ch/" + nomBase, props);
    } // connect

}