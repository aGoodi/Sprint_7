package courier;

import org.apache.commons.lang3.RandomStringUtils;

public class CourierGenerator {

    public Courier generic() {
        return new Courier("user3", "1234", "Jack");
    }

    public Courier emptyPassword() {
        return new Courier("user3", "", "Jack");
    }

    public Courier random() {
        return new Courier(RandomStringUtils.randomAlphabetic(6), "1234", "Jack");
    }
}
