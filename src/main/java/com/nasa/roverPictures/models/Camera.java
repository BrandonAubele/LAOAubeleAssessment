package com.nasa.roverPictures.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Camera {

    private Long id;
    private String name;
    private Integer rover_id;
    private String full_name;

    public Camera() {

    }

    public Camera(Long id, String name, Integer rover_id, String full_name) {
        this.id = id;
        this.name = name;
        this.rover_id = rover_id;
        this.full_name = full_name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRover_id() {
        return rover_id;
    }

    public void setRover_id(Integer rover_id) {
        this.rover_id = rover_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }
}
