package com.nasa.roverPictures.models;

import org.junit.Test;

import static org.junit.Assert.*;

public class PhotoTest {

    @Test
    public void testConstructor() {
        Photo test = new Photo();
        assertNotNull(test);
    }

    @Test
    public void testNameConstructor() {
        String testName = "TEST";
        Photo test = new Photo(testName);
        assertNotNull(test);
        assertEquals(testName, test.getImg_name());

    }
}