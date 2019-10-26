package training.busboard.model;

public class Location {

    private double lon;
    private double lat;

    public Location(double lon, double lat) {
        this.lon = lon;
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public double getLat() {
        return lat;
    }
}
