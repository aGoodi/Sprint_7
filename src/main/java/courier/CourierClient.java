package courier;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class CourierClient {
    protected final String BASE_URI = "https://qa-scooter.praktikum-services.ru";
    protected final String ROOT = "/api/v1/courier";

    public ValidatableResponse create(Courier courier) {
        return given()
                .log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .and()
                .body(courier)
                .when()
                .post(ROOT)
                .then().log().all();
    }

    public ValidatableResponse login(Credentials creds) {
        return given()
                .log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .and()
                .body(creds)
                .when()
                .post(ROOT + "/login")
                .then().log().all();
    }

    public ValidatableResponse delete(int courierId) {
        var mapId = Map.of("id", courierId);
        return given()
                .log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .and()
                .body(mapId)
                .when()
                .delete(ROOT + "/" + courierId)
                .then().log().all();
    }
}
