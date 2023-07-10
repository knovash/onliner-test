package org.itacademy.onlinertest.utils;

import lombok.extern.log4j.Log4j2;
import org.itacademy.onlinertest.models.OrderItem;
import org.itacademy.onlinertest.models.Orders;
import org.testng.annotations.DataProvider;

import java.util.List;
import java.util.stream.IntStream;

@Log4j2
public class DataProviderOrderItems {

    @DataProvider
    public Object[][] orderItems() {
        Orders object = JsonUtils.getObjectFromFile(Config.getDataFileOrder(), Orders.class);
        List<OrderItem> list = object.getOrders();
        int size = list.size();
        Object[][] data = new Object[size][1];
        IntStream.range(0, size)
                .peek(i -> log.info("DATAPROVIDER ["+ i +"] "+ list.get(i)))
                .forEach(i -> data[i][0] = list.get(i));
        return data;
    }
}
