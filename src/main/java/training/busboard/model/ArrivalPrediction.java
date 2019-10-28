package training.busboard.model;

public class ArrivalPrediction {

    private String naptanId;
    private String stationName;
    private String lineName;
    private String platformName;
    private String destinationName;
    private String timestamp;
    private String timeToStation;


    public ArrivalPrediction() {
    }


    public String getNaptanId() {
        return naptanId;
    }

    public void setNaptanId(String naptanId) {
        this.naptanId = naptanId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTimeToStation() {
        return timeToStation;
    }

    public void setTimeToStation(String timeToStation) {
        this.timeToStation = timeToStation;
    }

    @Override
    public String toString() {
        return "ArrivalPrediction{" +
                "naptanId='" + naptanId + '\'' +
                ", stationName='" + stationName + '\'' +
                ", lineName='" + lineName + '\'' +
                ", platformName='" + platformName + '\'' +
                ", destinationName='" + destinationName + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", timeToStation='" + timeToStation + '\'' +
                '}';
    }
}