package dev.kwolszczak;

import dev.kwolszczak.util.RequestSpec;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {


    protected static RequestSpecification requestSpecificationWeather;

    @BeforeEach
    void setUp() {
        requestSpecificationWeather = RequestSpec.getRequestSpecificationForWeather();
    }
}
