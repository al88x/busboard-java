package training.busboard.model;


public class ArrivalPrediction {

    private String $type;
    private String id;
    private String operationType;
    private String vehicleId;
    private String naptanId;
    private String stationName;
    private String lineId;
    private String lineName;
    private String platformName;
    private String direction;
    private String bearing;
    private String destinationNaptanId;
    private String destinationName;
    private String timestamp;
    private String timeToStation;
    private String currentLocation;
    private String towards;
    private String expectedArrival;
    private String timeToLive;
    private String modeName;
    private Timing timing;


    public ArrivalPrediction() {
    }

    public String get$type() {
        return $type;
    }

    public void set$type(String $type) {
        this.$type = $type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
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

    public String getLineId() {
        return lineId;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId;
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

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getBearing() {
        return bearing;
    }

    public void setBearing(String bearing) {
        this.bearing = bearing;
    }

    public String getDestinationNaptanId() {
        return destinationNaptanId;
    }

    public void setDestinationNaptanId(String destinationNaptanId) {
        this.destinationNaptanId = destinationNaptanId;
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

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public String getTowards() {
        return towards;
    }

    public void setTowards(String towards) {
        this.towards = towards;
    }

    public String getExpectedArrival() {
        return expectedArrival;
    }

    public void setExpectedArrival(String expectedArrival) {
        this.expectedArrival = expectedArrival;
    }

    public String getTimeToLive() {
        return timeToLive;
    }

    public void setTimeToLive(String timeToLive) {
        this.timeToLive = timeToLive;
    }

    public String getModeName() {
        return modeName;
    }

    public void setModeName(String modeName) {
        this.modeName = modeName;
    }

    public Timing getTiming() {
        return timing;
    }

    public void setTiming(Timing timing) {
        this.timing = timing;
    }

    @Override
    public String toString() {
        return "ArrivalPrediction{" +
                "$type='" + $type + '\'' +
                ", id='" + id + '\'' +
                ", operationType='" + operationType + '\'' +
                ", vehicleId='" + vehicleId + '\'' +
                ", naptanId='" + naptanId + '\'' +
                ", stationName='" + stationName + '\'' +
                ", lineId='" + lineId + '\'' +
                ", lineName='" + lineName + '\'' +
                ", platformName='" + platformName + '\'' +
                ", direction='" + direction + '\'' +
                ", bearing='" + bearing + '\'' +
                ", destinationNaptanId='" + destinationNaptanId + '\'' +
                ", destinationName='" + destinationName + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", timeToStation='" + timeToStation + '\'' +
                ", currentLocation='" + currentLocation + '\'' +
                ", towards='" + towards + '\'' +
                ", expectedArrival='" + expectedArrival + '\'' +
                ", timeToLive='" + timeToLive + '\'' +
                ", modeName='" + modeName + '\'' +
                ", timing=" + timing +
                '}';
    }

    class Timing{
        private String $type;
        private String countdownServerAdjustment;
        private String source;
        private String insert;
        private String read;
        private String sent;
        private String received;


        public Timing() {
        }


        public String get$type() {
            return $type;
        }

        public void set$type(String $type) {
            this.$type = $type;
        }

        public String getCountdownServerAdjustment() {
            return countdownServerAdjustment;
        }

        public void setCountdownServerAdjustment(String countdownServerAdjustment) {
            this.countdownServerAdjustment = countdownServerAdjustment;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getInsert() {
            return insert;
        }

        public void setInsert(String insert) {
            this.insert = insert;
        }

        public String getRead() {
            return read;
        }

        public void setRead(String read) {
            this.read = read;
        }

        public String getSent() {
            return sent;
        }

        public void setSent(String sent) {
            this.sent = sent;
        }

        public String getReceived() {
            return received;
        }

        public void setReceived(String received) {
            this.received = received;
        }

        @Override
        public String toString() {
            return "Timing{" +
                    "$type='" + $type + '\'' +
                    ", countdownServerAdjustment='" + countdownServerAdjustment + '\'' +
                    ", source='" + source + '\'' +
                    ", insert='" + insert + '\'' +
                    ", read='" + read + '\'' +
                    ", sent='" + sent + '\'' +
                    ", received='" + received + '\'' +
                    '}';
        }
    }
}
