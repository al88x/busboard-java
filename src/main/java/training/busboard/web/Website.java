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
import training.busboard.controller.exception.*;
import training.busboard.logger.Logger;
import training.busboard.model.BusStation;
import training.busboard.model.Location;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Controller
@EnableAutoConfiguration
public class Website {

    PostcodeGeoApi geoApi;
    NearestBusStopApi busStopApi;
    ArrivalPredictionApi predictionApi;

    public Website() {
        this.geoApi = new PostcodeGeoApi();
        this.busStopApi = new NearestBusStopApi();
        this.predictionApi = new ArrivalPredictionApi();
    }

    @RequestMapping("/")
    ModelAndView home() {
        return new ModelAndView("index");
    }

    @RequestMapping("/busInfo")
    ModelAndView busInfo(@RequestParam("postcode") String postcode) throws IOException {
        List<BusStation> busStations = new ArrayList<>();
        Logger.info("Client is searching for bus stops with postcode: " + postcode);

        try {
            Location geoLocation = geoApi.findGeoLocation(postcode);
            List<String> busStopIds = busStopApi.nearest2BusStopIds(geoLocation.getLongitude(),
                    geoLocation.getLatitude());
            for (String busStop : busStopIds) {
                busStations.add(predictionApi.getArrivalPrediction(busStop, 5));
            }
        } catch (PostcodeNotFoundException | InvalidPostCodeException | BusPredictionNotFound | BusStopIdNotFoundException e) {
            Logger.debug(e);
            return new ModelAndView("index", "apiException", e);
        }
        Logger.info("Successfully found bus arrival prediction for postcode: " + postcode);
        return new ModelAndView("info", "busInfo", new BusInfo(busStations));
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Website.class, args);
    }

}