package org.itacademy.onlinertest.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Log4j2
public class OrderItem {

    public String street;
    public String building;
    public String entrance;
    public String floor;
    public String apartment;
    public String comment;
    public String firstName;
    public String lastName;
    public String email;
    public String phone;
}
