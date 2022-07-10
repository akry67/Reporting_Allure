package ru.netology.rest.generate;

import com.github.javafaker.DateAndTime;
import lombok.Data;

@Data
public class Generation {
    private final String city;
    private final DateAndTime data;
    private String name;
    private String phone;
}
