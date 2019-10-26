package training.busboard.api;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.glassfish.jersey.jackson.JacksonFeature;
import training.busboard.model.Location;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.Optional;



public class PostcodeGeoApi {

    private Client client;

    public PostcodeGeoApi() {
        this.client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
    }

    public synchronized Optional<Location> findGeoLocation(String postcode){
        Response responseJson = client.target("https://api.postcodes.io/postcodes/" + postcode)
                .request(MediaType.APPLICATION_JSON)
                .get();

        if(responseJson.getStatus() == 200){
            JsonObject jsonObject = JsonParser.parseString(responseJson.readEntity(String.class)).getAsJsonObject();
            JsonObject result = jsonObject.getAsJsonObject("result");

            return Optional.of(new Location(result.get("longitude").getAsDouble(),
                    (result.get("latitude").getAsDouble())));
        }
        return Optional.empty();
    }
}
