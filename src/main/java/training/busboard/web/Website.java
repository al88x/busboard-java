package training.busboard.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import training.busboard.controller.ArrivalPredictionApi;
import training.busboard.controller.NearestBusStopApi;
import training.busboard.controller.PostcodeGeoApi;
import training.busboard.model.BusStation;
import training.busboard.model.Location;
import training.busboard.service.ServiceApi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@EnableAutoConfiguration
public class Website {



    @RequestMapping("/")
    ModelAndView home() {
        return new ModelAndView("index");
    }

    @RequestMapping("/busInfo")
    ModelAndView busInfo(@RequestParam("postcode") String postcode) throws IOException {
        PostcodeGeoApi geoApi = new PostcodeGeoApi();
        NearestBusStopApi busStopApi = new NearestBusStopApi();
        ArrivalPredictionApi predictionApi = new ArrivalPredictionApi();

        Optional<Location> geoLocation = geoApi.findGeoLocation(postcode);
        if(!geoLocation.isPresent()){
            return new ModelAndView("index", "geoApi", geoApi);
        }

        Optional<List<String>> busStopIds = busStopApi.nearest2BusStopIds(geoLocation.get().getLongitude(), geoLocation.get().getLatitude());
        List<BusStation> busStations = new ArrayList<>();
        for(String busStop : busStopIds.get()){
            busStations.add(predictionApi.getArrivalPrediction(busStop, 5).get());
        }

        return new ModelAndView("info", "busInfo", new BusInfo(busStations)) ;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Website.class, args);
    }

}