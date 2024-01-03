package dev.kwolszczak;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static io.restassured.RestAssured.given;

class WeatherTests {

    private String apiKey;
    private RequestSpecification req;

    @BeforeEach
    void setUp() {
        apiKey = "89a2ed8a594cc497a6273490e7ca59dd";

        req = new RequestSpecBuilder()
                .setBaseUri("http://api.openweathermap.org")
                .addQueryParam("appid", apiKey)
                .setContentType(ContentType.JSON)
                .build();
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    void shouldGetWeatherForACity(String city) {

        JsonPath location = given()
                        .spec(req)
                        .basePath("/geo/1.0")
                        .queryParam("q", city)
                        .queryParam("limit", 2)
                .when().get("/direct")
                .then().assertThat().statusCode(200)
                .extract().jsonPath();

        var lat = location.getFloat("[0].lat");
        var lon = location.getFloat("[0].lon");

        var weather =given()
                .spec(req)
                .basePath("/data/2.5/weather")
                .queryParam("lat", lat)
                .queryParam("lon", lon)
        .when().get()
        .then().assertThat().statusCode(200)
                .extract().jsonPath();

        System.out.println(weather.getString("weather[0].description"));
    }

    public static Stream<Arguments> dataProvider() {
        return Stream.of(Arguments.of("London"), Arguments.of("Oxford"), Arguments.of("Gdańsk"));
    }
}
