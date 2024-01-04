package dev.kwolszczak;

import dev.kwolszczak.util.DataProvider;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.Properties;
import java.util.stream.Stream;

import static io.restassured.RestAssured.given;

class WeatherTests {

    private RequestSpecification req;

    @BeforeEach
    void setUp() throws IOException {
        Properties properties = DataProvider.getProperties("test.properties");
        String apiKey = properties.getProperty("apiKey");
        String url = properties.getProperty("baseUri");

        req = new RequestSpecBuilder()
                .setBaseUri(url)
                .addQueryParam("appid", apiKey)
                .setContentType(ContentType.JSON)
                .build();
    }

    @ParameterizedTest
    @MethodSource("dev.kwolszczak.util.DataProvider#dataProvider")
    void shouldGetWeatherForACity(String city) {

        Location location = new Location(city);
        var weather =given()
                .spec(req)
                .basePath("/data/2.5/weather")
                .queryParam("lat", location.getLat())
                .queryParam("lon", location.getLon())
        .when().get()
        .then().assertThat().statusCode(200)
                .extract().jsonPath();

        System.out.println(weather.getString("weather[0].description"));
    }
}
