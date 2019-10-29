package training.busboard.model;

import java.util.List;

public class BusStation {

    private String busStationId;
    private String stationName;
    private String serverTime;
    private List<ArrivalPrediction> nextBusPredictions;


    public BusStation(List<ArrivalPrediction> nextBusPredictions) {
        this.nextBusPredictions = nextBusPredictions;
        this.busStationId = nextBusPredictions.get(0).getNaptanId();
        this.stationName = nextBusPredictions.get(0).getStationName();
        this.serverTime = nextBusPredictions.get(0).getTimestamp();
    }

    public String getServerTime() {
        return serverTime;
    }

    public void setServerTime(String serverTime) {
        this.serverTime = serverTime;
    }

    public String getBusStationId() {
        return busStationId;
    }

    public void setBusStationId(String busStationId) {
        this.busStationId = busStationId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public List<ArrivalPrediction> getNextBusPredictions() {
        return nextBusPredictions;
    }

    public void setNextBusPredictions(List<ArrivalPrediction> nextBusPredictions) {
        this.nextBusPredictions = nextBusPredictions;
    }

    @Override
    public String toString() {
        return "BusStation{" +
                "busStationId='" + busStationId + '\'' +
                ", stationName='" + stationName + '\'' +
                ", nextBusPredictions=" + nextBusPredictions +
                '}';
    }
}
