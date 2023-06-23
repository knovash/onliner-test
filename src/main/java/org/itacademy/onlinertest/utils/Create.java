package org.itacademy.onlinertest.utils;

import lombok.extern.log4j.Log4j2;
import org.itacademy.onlinertest.models.*;

import java.util.ArrayList;
import java.util.List;

@Log4j2
public class Create {

    public static void main(String[] args) {
        log.info("CREATE JSON FILE");
        List<CatalogItem> menuList = new ArrayList<>();
        menuList.add(new CatalogItem("iphone 12", "100"));
        menuList.add(new CatalogItem("iphone 13", "200"));
        Catalog catalog = new Catalog();
        catalog.setItems(menuList);
        JsonUtil.setObjectToFile(catalog, Config.getDataFile());
        log.info("CATALOG CREATED:\n" + catalog);
    }
}


