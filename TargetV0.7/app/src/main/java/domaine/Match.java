package domaine;

/**
 * Created by ottavio on 14/11/2017.
 */

public class Match {
    public String get_pseudo() {
        return _pseudo;
    }

    public void set_pseudo(String _pseudo) {
        this._pseudo = _pseudo;
    }

    public int get_idUser() {
        return _idUser;
    }

    public void set_idUser(int _idUser) {
        this._idUser = _idUser;
    }

    public double get_lat() {
        return _lat;
    }

    public void set_lat(double _lat) {
        this._lat = _lat;
    }

    public double get_long() {
        return _long;
    }

    public void set_long(double _long) {
        this._long = _long;
    }

    private String _pseudo;
    private int _idUser;
    private double _lat;
    private double _long;

    public Match(int aId){
        this._idUser = aId;
    }
}
