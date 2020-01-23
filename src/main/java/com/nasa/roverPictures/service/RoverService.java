package com.nasa.roverPictures.service;

import com.nasa.roverPictures.models.RoverRetrieve;
import com.nasa.roverPictures.models.Photo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class RoverService {

    @Value("${roverService.url}")
    private String url;
    @Value("${roverService.key}")
    private String urlKey;

    private static final Logger log = LoggerFactory.getLogger(RoverService.class);

    public RoverService() {

    }

    //Create
    public String savePictureFromUrl(final Photo photo) throws IOException {
        String destFileName = PictureService.getFullDestinationFolder() + "/" + photo.getImg_name();
        File destFolder = new File(PictureService.getFullDestinationFolder());
        //if folder doesn't exist create it
        if (!destFolder.exists()) {
            destFolder.mkdir();
            if (!destFolder.exists()) {
                log.error("Could not create folder " + PictureService.getFullDestinationFolder());
                return "";
            }
        }
        File file = new File(destFileName);

        //If file doesn't exist get and save it
        if (!file.exists()) {
            log.debug(Paths.get(destFileName).toString());
            try(InputStream in = new URL(photo.getImg_src()).openStream()) {
                Files.copy(in, Paths.get(destFileName));
            }
        }

        return destFileName;
    }
    //retrieve
    public RoverRetrieve getPicturesByDate(String dateStr) {
        RoverRetrieve result = new RoverRetrieve();
        int page = 1;
        RoverRetrieve data = new RoverRetrieve();
        while (!data.getPhotos().isEmpty() || page == 1) {
            data = getNasaRoverApiData(dateStr, page);
            result.addPhotos(data.getPhotos());
            page += 1;
        }

        if (!result.getPhotos().isEmpty()) {
            for(Photo photo : result.getPhotos()) {
                String fileName = "";
                try {
                    fileName = savePictureFromUrl(photo);
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
                photo.setLocal_filename(fileName);
            }
        }
        return result;
    }
    //grab api information
    private RoverRetrieve getNasaRoverApiData(final String dateString, final int page) {
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format(this.url, dateString, page, this.urlKey);
        log.debug(url);
        RoverRetrieve response = new RoverRetrieve();
        try {
            response = restTemplate.getForObject(url, RoverRetrieve.class);
        } catch (RestClientException e) {
            log.error(e.getMessage());
        }
        return response;
    }

}
