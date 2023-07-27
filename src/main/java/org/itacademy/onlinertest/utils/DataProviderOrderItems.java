package org.itacademy.onlinertest.utils;

import lombok.extern.log4j.Log4j2;
import org.itacademy.onlinertest.models.OrderItem;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Objects;

@Log4j2
public class DataProviderOrderItems {

    @DataProvider
    public Object[][] orderItems() {
        log.info("DATA PROVIDER orderItems");
        String path = Config.getPathToData();
        String fileName = Config.getDataFileNameOrder();
        ArrayList<OrderItem> list;
        Class clazz = OrderItem.class;

        String jsonData = JsonGenericUtils.getJsonFromFile(path, fileName);
        list = JsonGenericUtils.getListFromJson(jsonData, clazz);
        Object[][] data = Objects.requireNonNull(list).stream()
                .map(d -> new Object[]{d})
                .toArray(Object[][]::new);
        return data;
    }
}