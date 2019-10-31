package training.busboard.web;

import training.busboard.model.BusStation;

import java.util.List;

public class BusInfo {
    private BusStation busStation;
    private List<BusStation> busStationList;

    public BusInfo(List<BusStation> busStationList) {
        this.busStationList = busStationList;
    }

    public BusInfo(BusStation busStation) {
        this.busStation = busStation;
    }

    public List<BusStation> getBusStationList() {
        return busStationList;
    }

    public BusStation getBusStation() {
        return busStation;
    }
}
