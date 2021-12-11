package ru;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class CourierLoginTest {

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
    public void correctLoginTest() {
        courierClient.create(courier);

        courierId = courierClient.loginToEnter(CourierCredentials.from(courier)).extract().path("id");
        assertThat("Courier ID was not created", courierId, is(not(0)));
    }

    @Test
    public void incorrectLoginTest() {
        courierClient.create(courier);

        String wrongLoginError = courierClient.loginToEnter(new CourierCredentials(courier.login+"1", courier.password)).extract().path("message");
        assertThat("Authorization error", wrongLoginError, equalTo("Учетная запись не найдена"));

        String wrongPasswordError = courierClient.loginToEnter(new CourierCredentials(courier.login, courier.password+"1")).extract().path("message");
        assertThat("Authorization error", wrongPasswordError, equalTo("Учетная запись не найдена"));

        String justLoginAuthError = courierClient.loginToEnter(new CourierCredentials(courier.login, null)).extract().path("message");
        assertThat("Authorization error", justLoginAuthError, equalTo("Недостаточно данных для входа"));

        String justPasswordAuthError = courierClient.loginToEnter(new CourierCredentials(null, courier.password)).extract().path("message");
        assertThat("Authorization error", justPasswordAuthError, equalTo("Недостаточно данных для входа"));
    }
}
