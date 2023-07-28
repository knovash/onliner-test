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

    public String name;
    public String price;
    public String brand;
    public String priceMin;
    public String priceMax;
}
