package training.busboard.api;

import org.glassfish.jersey.jackson.JacksonFeature;
import training.busboard.model.ArrivalPrediction;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ArrivalPredictionApi {

    private Client client;

    public ArrivalPredictionApi() {
        this.client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
    }

    public Optional<List<ArrivalPrediction>> getArrivalPrediction(String stopId, int numberOfPredictions){
        Response response = client.target("https://api.tfl.gov.uk/StopPoint/" + stopId + "/Arrivals")
                .request(MediaType.APPLICATION_JSON)
                .get();
        if(response.getStatus() == 200){
            return Optional.of(
                    response.readEntity(new GenericType<List<ArrivalPrediction>>(){})
                    .stream()
                    .limit(numberOfPredictions)
                    .collect(Collectors.toList()));
        }
        return Optional.empty();
    }
}
