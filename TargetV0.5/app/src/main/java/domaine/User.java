package domaine;

import java.util.Date;

/**
 * Created by ottavio on 17/10/2017.
 */

public class User {
    private static String _nom;
    private static String _prenom;
    private static String _email;
    private static int _idUser;
    private static String _pseudo;
    private static String _password;
    private static String _description;
    private static Date _naissance = new Date();
    private static String _genre;
    private static int _taille;
    private static int _poids;
    private static String _emploi;
    private static String _lieu;
    private static String _langue;

    public User(){

    }

    public static String get_nom() {
        return _nom;
    }

    public static String get_prenom() {
        return _prenom;
    }

    public static String get_email() {
        return _email;
    }

    public static int get_idUser() {
        return _idUser;
    }

    public static String get_pseudo() {
        return _pseudo;
    }

    public static String get_password() {
        return _password;
    }

    public static String get_description() {
        return _description;
    }

    public static Date get_naissance(){
        return _naissance;
    }

    public static String get_genre() {
        return _genre;
    }

    public static int get_taille() {
        return _taille;
    }

    public static int get_poids() {
        return _poids;
    }

    public static String get_emploi() {
        return _emploi;
    }

    public static String get_lieu() {
        return _lieu;
    }

    public static String get_langue() {
        return _langue;
    }

    public static void set_nom(String _nom) {
        User._nom = _nom;
    }

    public static void set_prenom(String _prenom) {
        User._prenom = _prenom;
    }

    public static void set_email(String _email) {
        User._email = _email;
    }

    public static void set_idUser(int _idUser) {
        User._idUser = _idUser;
    }

    public static void set_pseudo(String _pseudo) {
        User._pseudo = _pseudo;
    }

    public static void set_password(String _password) {
        User._password = _password;
    }

    public static void set_description(String _description) {
        User._description = _description;
    }

    public static void set_naissance(Date _naissance) {
        User._naissance = _naissance;
    }

    public static void set_genre(String _genre) {
        User._genre = _genre;
    }

    public static void set_taille(int _taille) {
        User._taille = _taille;
    }

    public static void set_poids(int _poids) {
        User._poids = _poids;
    }

    public static void set_emploi(String _emploi) {
        User._emploi = _emploi;
    }

    public static void set_lieu(String _lieu) {
        User._lieu = _lieu;
    }

    public static void set_langue(String _langue) {
        User._langue = _langue;
    }
}
