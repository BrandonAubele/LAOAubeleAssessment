package com.nasa.roverPictures.handlers;

import com.nasa.roverPictures.models.RoverRetrieve;
import com.nasa.roverPictures.models.Photo;
import com.nasa.roverPictures.service.PictureService;
import com.nasa.roverPictures.service.RoverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FinalHandler {

    static final Logger logger = LoggerFactory.getLogger(FinalHandler.class);
    private final RoverService roverService;

    @Autowired
    public FinalHandler(final RoverService roverService) {
        this.roverService = roverService;
    }

    public RoverRetrieve getAllPhotos() {
        RoverRetrieve response = new RoverRetrieve();
        List<Photo> localImages = PictureService.getLocalImages();
        response.setPhotos(localImages);
        return response;
    }

    public RoverRetrieve getPicturesByDate(String date) {
        // check date string
        String parseDate = PictureService.parseDate(date);
        if (parseDate == "") {
            logger.error("invalid date string " + date);
            return new RoverRetrieve();
        }
        // get pictures from rover service
        return this.roverService.getPicturesByDate(parseDate);
    }

    public RoverRetrieve runAcceptanceTest() {
        // run acceptance test
        RoverRetrieve response = new RoverRetrieve();
        List<String> acceptanceDates = PictureService.getDatesFromFile(PictureService.DATES_FILE);
        for (String date : acceptanceDates) {
            RoverRetrieve dateResponse = getPicturesByDate(date);
            response.addPhotos(dateResponse.getPhotos());
        }
        return response;
    }
}
