package training.busboard.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static training.busboard.api.Constants.APP_ID;
import static training.busboard.api.Constants.APP_KEY;

public class NearestBusStopApi {

    private Client client;

    public NearestBusStopApi() {
        this.client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
    }

    public synchronized Optional<List<String>> nearest2BusStopIds(double lon, double lat) {
        Response responseJson = client.target("https://api.tfl.gov.uk/StopPoint?stopTypes=NaptanPublicBusCoachTram&" +
                "lat=" + lat +
                "&lon=" + lon +
                "&" + APP_ID +
                "&" + APP_KEY)
                .request(MediaType.APPLICATION_JSON)
                .get();

        if (responseJson.getStatus() == 200) {
            List<String> busStops = new ArrayList<>();

            //extracting stopPointId from json
            JsonArray stopPoints = JsonParser.parseString(responseJson.readEntity(String.class)).getAsJsonObject().getAsJsonArray("stopPoints");
            for (Object o : stopPoints) {
                JsonArray lineGroup = JsonParser.parseString(o.toString()).getAsJsonObject().getAsJsonArray("lineGroup");

                for (Object obj : lineGroup) {
                    //add stopPointId to busStop list
                    busStops.add(JsonParser.parseString(obj.toString()).getAsJsonObject().get("naptanIdReference").getAsString());
                    break;
                }
            }
            return Optional.of(busStops.stream().limit(2).collect(Collectors.toList()));
        }
        return Optional.empty();
    }
}
