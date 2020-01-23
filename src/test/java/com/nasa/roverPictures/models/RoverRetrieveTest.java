package com.nasa.roverPictures.models;


import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

public class RoverRetrieveTest {

    public static final String TEST = "test";
    private RoverRetrieve roverRetrieve;
    private List<Photo> photoList;

    @Test
    public void getPhotos() {
        photoList.add(new Photo(TEST));
        roverRetrieve.addPhotos(photoList);
        assertNotNull("Did something Comeback?", roverRetrieve.getPhotos());
        assertEquals("Papers Please", photoList, roverRetrieve.getPhotos());
        assertEquals("Do the Papers match", TEST, roverRetrieve.getPhotos().get(0).getImg_name());
    }

    @Test
    public void setPhotos() {
        photoList.add(new Photo(TEST));
        roverRetrieve.setPhotos(photoList);
        assertEquals("Got the photo list", photoList, roverRetrieve.getPhotos());
    }

    @Test
    public void addPhotos() {
        photoList.add(new Photo(TEST));
        roverRetrieve.addPhotos(photoList);
        assertEquals("Added", 1, roverRetrieve.getPhotos().size());
        List<Photo> newPhotoList = new ArrayList<>();
        newPhotoList.add(new Photo("Here again"));
        roverRetrieve.addPhotos(newPhotoList);
        assertEquals("More Papers", 2, roverRetrieve.getPhotos().size());
    }

    @Before
    public void setUp() throws Exception {
        roverRetrieve = new RoverRetrieve();
        photoList = new ArrayList<>();
    }
}