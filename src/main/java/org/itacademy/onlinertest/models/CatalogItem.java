package org.itacademy.onlinertest.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Log4j2
public class CatalogItem {

    String title;
    String price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CatalogItem that = (CatalogItem) o;
        log.info("TITLE1: " + title);
        log.info("TITLE2: " + that.title);
        log.info("CONTAINS = " + title.contains(that.title));
        log.info("PRICE1: " + price);
        log.info("PRICE2: " + that.price);
        log.info("EQUALS = " + price.equals(that.price));
        return  title.contains(that.title) && price.equals(that.price);
    }
}
