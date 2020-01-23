package com.nasa.roverPictures.service;

import com.nasa.roverPictures.models.Photo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public final class PictureService {

    public static String DATES_FILE;
    public static String PICTURE_FOLDER;
    public static String STANDARD_DATE_FORMAT = "yyyy-MM-dd";

    static final Logger logger = LoggerFactory.getLogger(PictureService.class);

    @Value("${roverService.pictureFolder}")
    public void setPictureFolder(String pictureFolder) {
        PictureService.PICTURE_FOLDER = pictureFolder;
    }

    @Value("${DemoFile.datesFile}")
    public void setDateFile(String dateFile) {
        PictureService.DATES_FILE = dateFile;
    }

    private PictureService() {

    }

    public static String parseDate(final String dateString) {
        String dateFormat = determineDateFormat(dateString);
        if (dateFormat != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
            try {
                simpleDateFormat.setLenient(false);
                Date parsedDate = simpleDateFormat.parse(dateString);

                SimpleDateFormat resultFormat = new SimpleDateFormat(STANDARD_DATE_FORMAT);
                String result = resultFormat.format(parsedDate);
                return result;
            } catch (ParseException e) {
                logger.error(e.getMessage());
            }
        }
        return "";
    }

    public static List<String> getDatesFromFile(final String fileName) {
        String dateFile = Paths.get("").toAbsolutePath().toString() + "/" + fileName;
        List<String> result = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(dateFile))) {
            result = stream.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static List<Photo> getLocalImages() {
        List<Photo> result = new ArrayList<>();
        File folder = new File(getFullDestinationFolder());
        String[] files = folder.list();
        if (files == null || files.length == 0) {
            return result;
        }
        for (String pic : files) {
            result.add(new Photo(pic));
        }
        return result;
    }

    public static String getFullDestinationFolder() {
        return Paths.get("").toAbsolutePath().toString() + "/" + PICTURE_FOLDER;
    }
    //Regex for SimpleDate pattern
    //More combinations can be added
    private static final Map<String, String> DATE_FORMAT_REGEXPS = new HashMap<String, String>() {{
        //Basic year/month/day variations
        put("^\\d{8}$", "yyyyMMdd");
        put("^\\d{1,2}-\\d{1,2}-\\d{4}$", "dd-MM-yyyy");
        put("^\\d{4}-\\d{1,2}-\\d{1,2}$", "yyyy-MM-dd");
        put("^\\d{1,2}/\\d{1,2}/\\d{4}$", "MM/dd/yyyy");
        put("^\\d{1,2}/\\d{1,2}/\\d{2}$", "MM/dd/yy");
        put("^\\d{4}/\\d{1,2}/\\d{1,2}$", "yyyy/MM/dd");
        put("^\\d{1,2}\\s[a-z]{3}\\s\\d{4}$", "dd MMM yyyy");
        put("^\\d{1,2}\\s[a-z]{4,}\\s\\d{4}$", "dd MMMM yyyy");
        //If the month is abbreviated
        put("^[a-z]{3}-\\d{1,2}-\\d{4}$", "MMM-dd-yyyy");
        put("^[a-z]{3,}\\s\\d{1,2},\\s\\d{4}$", "MMMM dd, yyyy");
        //added hours and minutes
        put("^\\d{12}$", "yyyyMMddHHmm");
        put("^\\d{8}\\s\\d{4}$", "yyyyMMdd HHmm");
        put("^\\d{1,2}-\\d{1,2}-\\d{4}\\s\\d{1,2}:\\d{2}$", "dd-MM-yyyy HH:mm");
        put("^\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{2}$", "yyyy-MM-dd HH:mm");
        put("^\\d{1,2}/\\d{1,2}/\\d{4}\\s\\d{1,2}:\\d{2}$", "MM/dd/yyyy HH:mm");
        put("^\\d{4}/\\d{1,2}/\\d{1,2}\\s\\d{1,2}:\\d{2}$", "yyyy/MM/dd HH:mm");
        put("^\\d{1,2}\\s[a-z]{3}\\s\\d{4}\\s\\d{1,2}:\\d{2}$", "dd MMM yyyy HH:mm");
        put("^\\d{1,2}\\s[a-z]{4,}\\s\\d{4}\\s\\d{1,2}:\\d{2}$", "dd MMMM yyyy HH:mm");
        //added seconds
        put("^\\d{14}$", "yyyyMMddHHmmss");
        put("^\\d{8}\\s\\d{6}$", "yyyyMMdd HHmmss");
        put("^\\d{1,2}-\\d{1,2}-\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "dd-MM-yyyy HH:mm:ss");
        put("^\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{2}:\\d{2}$", "yyyy-MM-dd HH:mm:ss");
        put("^\\d{1,2}/\\d{1,2}/\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "MM/dd/yyyy HH:mm:ss");
        put("^\\d{4}/\\d{1,2}/\\d{1,2}\\s\\d{1,2}:\\d{2}:\\d{2}$", "yyyy/MM/dd HH:mm:ss");
        put("^\\d{1,2}\\s[a-z]{3}\\s\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "dd MMM yyyy HH:mm:ss");
        put("^\\d{1,2}\\s[a-z]{4,}\\s\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "dd MMMM yyyy HH:mm:ss");
    }};
    //Determines if given date is valid compared to regex using SimpleDate Pattern
    public static String determineDateFormat(String dateString) {
        for (String regexp : DATE_FORMAT_REGEXPS.keySet()) {
            if (dateString.toLowerCase().matches(regexp)) {
                return DATE_FORMAT_REGEXPS.get(regexp);
            }
        }
        return null; // Unknown format.
    }
}
