package courier;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;


public class CreateCourierTest {

    private final CourierGenerator generator = new CourierGenerator();
    private final CourierClient client = new CourierClient();
    private final CourierAssertions check = new CourierAssertions();
    private int courierId;

    @After
    public void deleteCourier(){
        if (courierId > 0) {
            ValidatableResponse response = client.delete(courierId);
            check.deletedSuccessfully(response);
        }
    }

    @Test
    @DisplayName("Create new courier with valid data")
    public void createNewCourier() {
        Courier courier = generator.random();
        ValidatableResponse creationResponse = client.create(courier);
        check.createdSuccessfully(creationResponse);
    }

    @Test
    @DisplayName("Create new courier with null password")
    public void createNewCourierWithEmptyPassword() {
        Courier courier = generator.emptyPassword();
        ValidatableResponse creationResponse = client.create(courier);
        check.createdFailsWithEmptyPassword(creationResponse);
    }

    @Test
    @DisplayName("Create new courier with same login")
    public void createNewCourierWithSameLogin() {
        Courier courier = generator.generic();
        ValidatableResponse creationResponse = client.create(courier);
        check.createdFailsWithSameLogin(creationResponse);
    }
}