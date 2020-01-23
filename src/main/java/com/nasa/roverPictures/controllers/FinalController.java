package com.nasa.roverPictures.controllers;

import com.nasa.roverPictures.handlers.FinalHandler;
import com.nasa.roverPictures.models.RoverRetrieve;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class FinalController {

    static final Logger logger = LoggerFactory.getLogger(FinalController.class);
    private static final String NASA_IMAGES = "nasaImages";

    private final FinalHandler finalHandler;

    @Autowired
    public FinalController(final FinalHandler finalHandler) {
        this.finalHandler = finalHandler;
    }

    @GetMapping(value = "/")
    public String index(Model model) {
        /**
         * this returns all currently downloaded images
         */
        logger.info("index");
        RoverRetrieve nasaImages = finalHandler.getAllPhotos();
        model.addAttribute(NASA_IMAGES, nasaImages);
        return "index";
    }

    @GetMapping(value = "/picture/{date}")
    public String getPicturesByDate(@PathVariable("date") String date, Model model) {
        /**
         * this downloads all images for the specified date (if not already local) and displays them
         */
        logger.info("getPicturesByDate");
        RoverRetrieve nasaImages = finalHandler.getPicturesByDate(date);
        model.addAttribute(NASA_IMAGES, nasaImages);
        return "index";
    }

    @GetMapping(value = "/acceptance")
    public String runAcceptanceTest(Model model) {
        /**
         * this runs the acceptance criteria (see README.md)
         */
        logger.info("runAcceptanceTest");
        RoverRetrieve nasaImages = finalHandler.runAcceptanceTest();
        model.addAttribute(NASA_IMAGES, nasaImages);
        return "index";
    }
}
