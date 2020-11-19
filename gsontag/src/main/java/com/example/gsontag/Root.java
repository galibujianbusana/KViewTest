package com.example.gsontag;

import java.util.List;

public class Root {

    List<Provinces> info;

   public class Provinces{
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

   public class City{
        String code;
        String name;
        List<Area> areaList;

       @Override
       public String toString() {
           return "City{" +
                   "name='" + name + '\'' +
                   ", areaList=" + areaList +
                   '}';
       }
   }

   public class Area{
        String code;
        String name;

       @Override
       public String toString() {
           return "Area{" +
                   "name='" + name + '\'' +
                   '}';
       }
   }
}
