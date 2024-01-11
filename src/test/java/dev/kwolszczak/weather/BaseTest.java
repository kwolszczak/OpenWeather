package dev.kwolszczak.weather;

import dev.kwolszczak.weather.util.RequestSpec;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {

    protected static RequestSpecification requestSpecificationWeather;

    @BeforeEach
    void setUp() {
        requestSpecificationWeather = RequestSpec.getRequestSpecificationForWeather();
    }
}
