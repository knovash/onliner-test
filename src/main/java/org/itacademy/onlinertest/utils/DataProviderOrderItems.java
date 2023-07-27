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
        String path = "data/";
        String fileName = "order_items.json";
        ArrayList<OrderItem> list;
        Class clazz = OrderItem.class;

        String jsonData = JsonUtils.getJsonFromFile(path, fileName);
        list = JsonUtils.getListFromJson(jsonData, clazz);
        Object[][] data = Objects.requireNonNull(list).stream()
                .map(d -> new Object[]{d})
                .toArray(Object[][]::new);
        return data;
    }
}