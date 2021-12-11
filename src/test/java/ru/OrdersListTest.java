package ru;

import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import static org.hamcrest.Matchers.*;

public class OrdersListTest {

    @Test
    public void checkListOrdersByGetRequest() {

    ListOrders listOrders = new ListOrders();

    ValidatableResponse response = listOrders.list();
    response.assertThat().body("orders.size()", is(not(0)));
    }
}

