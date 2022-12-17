package courier;

import io.restassured.response.ValidatableResponse;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.greaterThan;

public class CourierAssertions {

    public void createdSuccessfully(ValidatableResponse response) {
        response.assertThat()
                .statusCode(201)
                .body("ok", is(true));
    }

    public void createdFailsWithEmptyPassword(ValidatableResponse response) {
        response.assertThat()
                .statusCode(400)
                .body("message", notNullValue());
    }

    public void createdFailsWithSameLogin(ValidatableResponse response) {
        response.assertThat()
                .statusCode(409)
                .body("message", notNullValue());
    }

    public void loggedInSuccessfully(ValidatableResponse response) {
        response.assertThat()
                .statusCode(200)
                .body("id", greaterThan(0));
    }

    public void loggedInFailsWithEmptyPassword(ValidatableResponse response) {
        response.assertThat()
                .statusCode(400)
                .body("message", notNullValue());
    }

    public void loggedInFailsWithInvalidLogin(ValidatableResponse response) {
        response.assertThat()
                .statusCode(404)
                .body("message", notNullValue());
    }

    public int loggedInSuccessfullyExtractId(ValidatableResponse response) {
        return response.assertThat()
                .statusCode(200)
                .body("id", greaterThan(0))
                .extract().path("id");
    }

    public void deleteSuccessfully(ValidatableResponse response) {
        response.assertThat()
                .statusCode(200)
                .body("ok", is(true));
    }
}
