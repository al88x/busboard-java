package training.busboard.web;

import training.busboard.model.BusStation;

import java.util.List;

public class BusInfo {
    private List<BusStation> busStationList;

    public BusInfo(List<BusStation> busStationList) {
        this.busStationList = busStationList;
    }

    public List<BusStation> getBusStationList() {
        return busStationList;
    }


}
