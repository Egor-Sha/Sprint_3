package ru;

import java.util.List;

public class Order {

    public String firstName;
    public String lastName;
    public String address;
    public int metroStation;
    public String  phone;
    public int  rentTime;
    public String  deliveryDate;
    public String  comment;
    public static List<ScooterColourList> colour;

    public Order(String firstName, String lastName, String address, int metroStation,
                 String phone, int rentTime, String deliveryDate, String comment, List<ScooterColourList> colour) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.colour = colour;
    }

    public static Order getOrderData() {
        return new Order("Naruto",
                "Uchiha",
                "Konoha, 142 apt.",
                4,
                "+7 800 355 35 35",
                5,
                "2020-06-06",
                "Saske, come back to Konoha",
                List.of(ScooterColourList.BLACK,ScooterColourList.GREY)
        );
    }
        public Order setColour(List<ScooterColourList> colour) {
            this.colour = colour;
            return this;
        }
    }
