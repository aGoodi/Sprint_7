package courier;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

    public class DeleteCourierTest {

    private final CourierGenerator generator = new CourierGenerator();
    private final CourierClient client = new CourierClient();
    private final CourierAssertions check = new CourierAssertions();

    @Test
    @DisplayName("Delete courier")
    public void deleteCourier() {
        Courier courier = generator.random();
        ValidatableResponse creationResponse = client.create(courier);
        check.createdSuccessfully(creationResponse);

        Credentials creds = Credentials.from(courier);
        ValidatableResponse loginResponse = client.login(creds);
        var courierId = check.loggedInSuccessfullyExtractId(loginResponse);

        if (courierId > 0) {
            ValidatableResponse response = client.delete(courierId);
            check.deletedSuccessfully(response);
            }
    }
}

