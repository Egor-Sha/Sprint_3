package ru;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;

public class OrderClient extends RestAssuredClient {

        static final String ORDER_PATH = "api/v1/orders/";

        @Step
        public ValidatableResponse create(Order order) {

            return given()
                    .spec(getBaseSpec())
                    .body(order)
                    .when()
                    .post(ORDER_PATH)
                    .then();
        }

        @Step
        public static ValidatableResponse cancel(int track) {

            return given()
                    .spec(getBaseSpec())
                    .body(track)
                    .when()
                    .put(ORDER_PATH+"cancel")
                    .then();
        }
}

