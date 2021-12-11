package ru;

import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class CourierCreateTest {

    private Courier courier;
    private CourierClient courierClient;
    private int courierId;

    @Before
    public void setUp() {
        courier = Courier.getRandom();
        courierClient = new CourierClient();
    }

    @After
    public void tearDown() {
        courierClient.delete(courierId);
    }

    @Test
    public void courierCanBeCreatedTest() {
        ValidatableResponse response = courierClient.create(courier);

        boolean isCourierCreated = response.extract().path("ok");
        int statusCode = response.extract().statusCode();

        assertTrue("Courier was not created", isCourierCreated);
        assertThat("Bad Status Code", statusCode, equalTo(201));
    }

    @Test
    public void theSameCourierCantBeCreatedTest() {
        courierClient.create(courier);
        ValidatableResponse response = courierClient.create(courier);

        int statusCode = response.extract().statusCode();
        assertThat("Wrong code, expected 409", statusCode, equalTo(409));

        String errorMessage = response.extract().path("message");
        assertThat("The same courier was not created", errorMessage, equalTo("Этот логин уже используется. Попробуйте другой."));
    }
}
