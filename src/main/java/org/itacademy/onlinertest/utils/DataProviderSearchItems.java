package org.itacademy.onlinertest.utils;

import lombok.extern.log4j.Log4j2;
import org.itacademy.onlinertest.models.CatalogItem;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Objects;

@Log4j2
public class DataProviderSearchItems {

    @DataProvider
    public Object[][] searchItems() {
        log.info("DATA PROVIDER searchItems");
        String path = "data/";
        String fileName = "search_items.json";
        ArrayList<CatalogItem> list;
        Class clazz = CatalogItem.class;

        String jsonData = JsonUtils.getJsonFromFile(path, fileName);
        list = JsonUtils.getListFromJson(jsonData, clazz);
        Object[][] data = Objects.requireNonNull(list).stream()
                .map(d -> new Object[]{d})
                .toArray(Object[][]::new);
        return data;
    }
}
