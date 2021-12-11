package ru;

import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class CourierCreateValidationTest {

    private final Courier courier;
    private final int expectedStatus;
    private final String expectedErrorMessage;

    public CourierCreateValidationTest(Courier courier, int expectedStatus, String expectedErrorMessage) {
        this.courier = courier;
        this.expectedStatus = expectedStatus;
        this.expectedErrorMessage = expectedErrorMessage;
    }
    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {Courier.getWithLoginOnly(), 400, "Недостаточно данных для создания учетной записи"},
                {Courier.getWithPasswordOnly(), 400, "Недостаточно данных для создания учетной записи"},
                {Courier.getWithNameOnly(), 400, "Недостаточно данных для создания учетной записи"},
                {Courier.getWithLoginPassOnly(), 400, "Недостаточно данных для создания учетной записи"},
                {Courier.getWithPassNameOnly(), 400, "Недостаточно данных для создания учетной записи"},
                {Courier.getWithNameLoginOnly(), 400, "Недостаточно данных для создания учетной записи"}
        };
    }
    @Test
    public void wrongCreateRequestFault() {

        ValidatableResponse response = new CourierClient().create(courier);

        int statusCode = response.extract().statusCode();

        if(statusCode==201) {
            CourierClient courierClient = new CourierClient();
            int courierId = courierClient.loginToEnter(CourierCredentials.from(courier)).extract().path("id");;
            courierClient.delete(courierId);
        };

        assertThat("Courier created by fault", statusCode, equalTo(expectedStatus));
    }
}
