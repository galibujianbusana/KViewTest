package com.example.gsontag;

import java.util.List;

public class City {
    String code;
    String name;
    List<Area> areaList;

    @Override
    public String toString() {
        return "City{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", areaList=" + areaList +
                '}';
    }
}
