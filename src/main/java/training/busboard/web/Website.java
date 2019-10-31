package training.busboard.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
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
import java.util.Set;


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

    @PostMapping("/")
    ModelAndView listAvailableStations(@RequestParam("postcode") String postcode) throws IOException {
        Logger.info("Client is searching for bus stops with postcode: " + postcode);
        Set<BusStation> busStopIds= null;
        try {
            Location geoLocation = geoApi.findGeoLocation(postcode);
            busStopIds = busStopApi.findBusStationsWithinArea(geoLocation.getLongitude(),
                    geoLocation.getLatitude());
        } catch (PostcodeNotFoundException | InvalidPostCodeException | BusStopIdNotFoundException e) {
            return new ModelAndView("index", "apiException", e);
        }
        return new ModelAndView("index", "busStopIds", busStopIds);
    }


    @RequestMapping("/busInfo")
    ModelAndView busInfo(@RequestParam("busStopId") String busStopId) throws IOException {
        BusStation updatedBusStation;
        try {
             updatedBusStation = predictionApi.getArrivalPrediction(busStopId, 10);

        } catch (PostcodeNotFoundException | InvalidPostCodeException | BusPredictionNotFound | BusStopIdNotFoundException e) {
            return new ModelAndView("index", "apiException", e);
        }
        Logger.info("Successfully found bus arrival prediction for bus Stop: " + updatedBusStation.getStationName());
        return new ModelAndView("info", "busInfo", new BusInfo(updatedBusStation));
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Website.class, args);
    }

}