package ru;

import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.List;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class OrderCreateTest {

    private List<ScooterColourList> colour;
    public OrderCreateTest(List<ScooterColourList> colour) {
        this.colour = colour;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {List.of(ScooterColourList.BLACK, ScooterColourList.GREY)},
                {List.of(ScooterColourList.BLACK)},
                {List.of(ScooterColourList.GREY)},
                {null}
        };
    }

    @Test
    public void orderWithDifColourParamsCreated() {
        OrderClient orderClient = new OrderClient();

        Order order = Order.getOrderData().setColour(colour);
        ValidatableResponse response = orderClient.create(order);

        int trackNumber = response.extract().path("track");
        assertNotEquals("Order was not created", trackNumber, 0);

        OrderClient.cancel(trackNumber);
    }
}
