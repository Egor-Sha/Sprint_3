package ru;

import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class CourierCreateWithSameLoginTest {

    private Courier courier;
    private CourierClient courierClient;
    private ValidatableResponse courierId;

    @Test
    public void theSameLoginCantBeCreatedTest() {

        courier = Courier.getCourierWithSameLogin();
        courierClient = new CourierClient();
        courierClient.create(courier);

        courier = Courier.getCourierWithSameLogin();
        ValidatableResponse response = courierClient.create(courier);

        int statusCode = response.extract().statusCode();
        String errorMessage = response.extract().path("message");

        if(statusCode==201) {
            CourierClient courierClient = new CourierClient();
            int courierId = courierClient.loginToEnter(CourierCredentials.from(courier)).extract().path("id");;
            courierClient.delete(courierId);
        };

        assertThat("Wrong code, expected 409", statusCode, equalTo(409));
        assertThat("The same courier was not created", errorMessage, equalTo("Этот логин уже используется. Попробуйте другой."));
    }
}
