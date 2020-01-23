package com.nasa.roverPictures.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RoverRetrieve {
    private List<Photo> photos;

    public RoverRetrieve() {
        this.photos = new ArrayList<>();
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public void addPhotos(List<Photo> photos) {
        this.photos.addAll(photos);
    }


}
