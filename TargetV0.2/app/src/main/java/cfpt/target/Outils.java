package cfpt.target;

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
        props.put("user", "root"); props.put("password", ""); props.put("charSet", "UTF-8");
        return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/" + nomBase, props);
    } // connect

}
