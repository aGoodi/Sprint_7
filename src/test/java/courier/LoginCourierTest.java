package courier;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;

public class LoginCourierTest {
    private final CourierGenerator generator = new CourierGenerator();
    private final CourierClient client = new CourierClient();
    private final CourierAssertions check = new CourierAssertions();
    private int courierId;

    @After
    public void deleteCourier(){
        if (courierId > 0) {
            ValidatableResponse response = client.delete(courierId);
            check.deleteSuccessfully(response);
        }
    }


    @Test
    @DisplayName("Login courier with valid data")
    public void loginCourier() {
        Courier courier = generator.random();
        ValidatableResponse creationResponse = client.create(courier);
        check.createdSuccessfully(creationResponse);

        Credentials creds = Credentials.from(courier);
        ValidatableResponse loginResponse = client.login(creds);
        check.loggedInSuccessfully(loginResponse);
    }

    @Test
    @DisplayName("Login courier with empty password")
    public void loginCourierWithEmptyPassword() {
        Courier courier = generator.random();
        ValidatableResponse creationResponse = client.create(courier);
        check.createdSuccessfully(creationResponse);

        Credentials creds = new Credentials(courier.getLogin(), "");
        ValidatableResponse loginResponse = client.login(creds);
        check.loggedInFailsWithEmptyPassword(loginResponse);
    }

    @Test
    @DisplayName("Login courier with invalid login")
    public void loggedInFailsWithInvalidLogin() {
        Credentials creds = new Credentials("123", "123");
        ValidatableResponse loginResponse = client.login(creds);
        check.loggedInFailsWithInvalidLogin(loginResponse);
    }

}
