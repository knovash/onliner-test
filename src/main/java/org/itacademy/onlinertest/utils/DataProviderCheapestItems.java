package org.itacademy.onlinertest.utils;

import lombok.extern.log4j.Log4j2;
import org.itacademy.onlinertest.models.CatalogItem;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Objects;

@Log4j2
public class DataProviderCheapestItems {

    @DataProvider
    public Object[][] cheapestItems() {
        log.info("DATA PROVIDER cheapestItems");
        String path = "data/";
        String fileName = "cheapest_items.json";
        ArrayList<CatalogItem> list;
        Class clazz = CatalogItem.class;

        String jsonData = JsonGenericUtils.getJsonFromFile(path, fileName);
        list = JsonGenericUtils.getListFromJson(jsonData, clazz);
        Object[][] data = Objects.requireNonNull(list).stream()
                .map(d -> new Object[]{d})
                .toArray(Object[][]::new);
        return data;
    }
}