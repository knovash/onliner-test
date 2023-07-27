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
    private static String dataFileNameCheapest;
    private static String dataFileNameSearch;
    private static String dataFileNameFilter;
    private static String dataFileNameOrder;
    private static String pathToData;

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
            dataFileNameCheapest = properties.getProperty("dataFileNameCheapest");
            dataFileNameSearch = properties.getProperty("dataFileNameSearch");
            dataFileNameFilter = properties.getProperty("dataFileFilter");
            dataFileNameOrder = properties.getProperty("dataFileNameOrder");
            pathToData = properties.getProperty("pathToData");
            log.info("homePage: " + homePage);
            log.info("dataFileNameCheapest: " + dataFileNameCheapest);
            log.info("dataFileNameSearch: " + dataFileNameSearch);
            log.info("dataFileNameFilter: " + dataFileNameFilter);
            log.info("dataFileNameOrder: " + dataFileNameOrder);
            log.info("pathToData: " + pathToData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getHomePage() {
        return homePage;
    }

    public static String getDataFileNameCheapest() {
        return dataFileNameCheapest;
    }

    public static String getDataFileNameSearch() {
        return dataFileNameSearch;
    }

    public static String getDataFileNameFilter() {
        return dataFileNameFilter;
    }

    public static String getDataFileNameOrder() {
        return dataFileNameOrder;
    }

    public static String getPathToData() {
        return pathToData;
    }
}
