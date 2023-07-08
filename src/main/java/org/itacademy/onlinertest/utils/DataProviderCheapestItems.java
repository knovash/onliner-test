package org.itacademy.onlinertest.utils;

import lombok.extern.log4j.Log4j2;
import org.itacademy.onlinertest.models.Catalog;
import org.itacademy.onlinertest.models.CatalogItem;
import org.testng.annotations.DataProvider;

import java.util.List;
import java.util.stream.IntStream;

@Log4j2
public class DataProviderCheapestItems {

    @DataProvider
    public Object[][] cheapestItems() {
        Catalog object = JsonUtil.getObjectFromFile(Config.getDataFileCheapest(), Catalog.class);
        List<CatalogItem> list = object.getItems();
        int size = list.size();
        Object[][] data = new Object[size][1];
        IntStream.range(0, size)
                .peek(i -> log.info("DATAPROVIDER ["+ i +"] "+ list.get(i)))
                .forEach(i -> data[i][0] = list.get(i));
        return data;
    }
}
