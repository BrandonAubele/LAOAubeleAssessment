package com.nasa.roverPictures.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(locations = "classpath:application.properties")
public class PictureServiceTest {

    @Value("${DemoFile.datesFile}")
    private String datesFile;

    @Test
    public void parseDateTest1() {
        String input = "January 23, 2020";
        String expected = "2020-01-23";
        assertEquals("date parsing test 1", expected, PictureService.parseDate(input));

    }

    @Test
    public void parseDateTest2() {
        String input = "01/23/2020";
        String expected = "2020-01-23";
        assertEquals("date parsing test 2", expected, PictureService.parseDate(input));
    }

    @Test
    public void parseDateTest3() {
        String input = "Apr-16-2005";
        String expected = "2005-04-16";
        assertEquals("date parsing test 3", expected, PictureService.parseDate(input));
    }

    @Test
    public void parseDateTestReturnsEmptyStringOnIncorrectDate() {
        String input = "April 31, 2018";
        String expected = "";
        assertEquals("date parsing test 4", expected, PictureService.parseDate(input));
    }


}