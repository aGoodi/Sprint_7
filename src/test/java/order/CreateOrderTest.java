package order;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

@RunWith(Parameterized.class)
public class CreateOrderTest {
    private final OrderClient client = new OrderClient();
    private final OrderGenerator generator = new OrderGenerator();
    private final OrderAssertions check = new OrderAssertions();

    public List<String> color;

    public CreateOrderTest(List<String> color) {
        this.color = color;
    }

        @Parameterized.Parameters(name = "Color: {0}")
        public static Object[][] getColor() {
            return new Object[][] {
                    {List.of("BLACK")},
                    {List.of("GREY")},
                    {List.of("BLACK", "GREY")},
                    {List.of("")},
            };
        }

    @Test
    @DisplayName("Create new order with different colors")
    public void createNewOrder() {
        Order order = generator.generic(color);
        ValidatableResponse creationResponse = client.create(order);
        check.createdSuccessfully(creationResponse);
    }
}
