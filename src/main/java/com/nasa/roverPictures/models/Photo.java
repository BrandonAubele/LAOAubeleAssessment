package com.nasa.roverPictures.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Photo {

    private Long id;
    private Long sol;
    private Camera camera;
    private String img_src;
    private Rover rover;

    private String local_filename;

    public Photo() {

    }

    public Photo(String img_src) {
        this.img_src = img_src;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSol() {
        return sol;
    }

    public void setSol(Long sol) {
        this.sol = sol;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public String getImg_src() {
        return img_src;
    }

    public void setImg_src(String img_src) {
        this.img_src = img_src;
    }

    public Rover getRover() {
        return rover;
    }

    public void setRover(Rover rover) {
        this.rover = rover;
    }

    public String getImg_name() {
        int index = img_src.lastIndexOf('/') + 1;
        return img_src.substring(index);
    }

    public String getLocal_filename() {
        return local_filename;
    }

    public void setLocal_filename(String local_filename) {
        this.local_filename = local_filename;
    }
}
