package order;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

public class GetOrderListTest {
    private final OrderClient client = new OrderClient();
    private final OrderAssertions check = new OrderAssertions();

    @Test
    @DisplayName("Get list of orders")
    public void getOrderList() {
        ValidatableResponse creationResponse = client.getOrdersList();
        check.gettingSuccessfully(creationResponse);
    }
}
