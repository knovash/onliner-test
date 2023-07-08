package org.itacademy.onlinertest.utils;

import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Properties;

@Log4j2
public class Config {

    private static String homePage;
    private static String dataFile;
    private static String dataFileCheapest;
    private static String dataFileSearch;
    private static String dataFileFilter;

    public static void getProperties() {
        log.info("CONFIG GET PROPERTIES");
        Properties properties = new Properties();
        URL resource = Config.class.getClassLoader().getResource("config.properties");
        File file = new File(Objects.requireNonNull(resource).getFile());
        try {
            FileInputStream in = new FileInputStream(file);
            properties.load(in);
            in.close();
            homePage = properties.getProperty("homePage");
            dataFile = properties.getProperty("dataFile");
            dataFileCheapest = properties.getProperty("dataFileCheapest");
            dataFileSearch = properties.getProperty("dataFileSearch");
            dataFileFilter = properties.getProperty("dataFileFilter");
            log.info("homePage: " + homePage);
            log.info("dataFile: " + dataFile);
            log.info("dataFileCheapest: " + dataFileCheapest);
            log.info("dataFileSearch: " + dataFileSearch);
            log.info("dataFileFilter: " + dataFileFilter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getHomePage() {
        return homePage;
    }

    public static String getDataFile() {
        return dataFile;
    }

    public static String getDataFileCheapest() {
        return dataFileCheapest;
    }

    public static String getDataFileSearch() {
        return dataFileSearch;
    }

    public static String getDataFileFilter() {
        return dataFileFilter;
    }
}
