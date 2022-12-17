package order;

import io.restassured.response.ValidatableResponse;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;

public class OrderAssertions {
    public void createdSuccessfully(ValidatableResponse response) {
        response.assertThat()
                .statusCode(201)
                .body("track", greaterThan(0));
    }
    public void gettingSuccessfully(ValidatableResponse response) {
        response.assertThat()
                .statusCode(200)
                .body("orders", notNullValue());
    }
}
