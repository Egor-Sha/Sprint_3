package ru;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class ListOrders extends RestAssuredClient {
    private static final String LIST_PATH = "api/v1/orders/";

    @Step
    public static ValidatableResponse list() {
        return given()
                .spec(getBaseSpec())
                .get(LIST_PATH)
                .then();
    }
}
