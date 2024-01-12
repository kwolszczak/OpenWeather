package dev.kwolszczak.weather;

import dev.kwolszczak.weather.supportTest.Location;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static io.restassured.RestAssured.given;

class WeatherTests extends BaseTest {

    @ParameterizedTest
    @MethodSource("dev.kwolszczak.DataProvider#dataProvider")
    void shouldGetWeatherForACity(String city) {

        Location location = new Location(city);
        var weather = given()
                        .spec(requestSpecificationWeather)
                        .queryParam("lat", location.getLat())
                        .queryParam("lon", location.getLon())
                .when().get()
                .then().assertThat().statusCode(200)
                        .extract().jsonPath();

        System.out.println(weather.getString("weather[0].description"));
    }
}
