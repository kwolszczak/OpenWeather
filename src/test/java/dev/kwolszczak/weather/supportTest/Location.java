package dev.kwolszczak.weather.supportTest;

import dev.kwolszczak.weather.util.RequestSpec;
import io.restassured.path.json.JsonPath;
import lombok.Getter;
import lombok.Setter;

import static io.restassured.RestAssured.given;

@Setter
@Getter
public class Location {
    private String city;
    private double lat;
    private double lon;

    public Location(String city) {
        this.city = city;
        getLocationFromAPI(city);
    }

    private void getLocationFromAPI(String city) {
        JsonPath location = given()
                        .spec(RequestSpec.getRequestSpecificationForLocation())
                        .queryParam("q", city)
                        .queryParam("limit", 2)
                .when().get("/direct")
                .then().assertThat().statusCode(200)
                .extract().jsonPath();

        String jsonPathForLat = "[0].lat";
        String jsonPathForLon = "[0].lon";
        lat = location.getFloat(jsonPathForLat);
        lon = location.getFloat(jsonPathForLon);
    }
}
