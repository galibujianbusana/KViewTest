package com.example.gsontag;

import java.util.List;

public class Provinces {
    String code;
    String name;
    List<City> cityList;

    @Override
    public String toString() {
        return "Provinces{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", cityList=" + cityList +
                '}';
    }
}
