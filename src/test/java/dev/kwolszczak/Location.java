package dev.kwolszczak;

import dev.kwolszczak.util.DataProvider;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class Location {
    private String city;
    private double lat;
    private double lon;

    public Location(String city) {
        this.city = city;
        getLocationFromAPI(city);
    }

    public Location(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    private void getLocationFromAPI(String city) {
        Properties properties;
        try {
           properties= DataProvider.getProperties("test.properties");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String apiKey = properties.getProperty("apiKey");
        String url = properties.getProperty("baseUri");
        var req = new RequestSpecBuilder()
                .setBaseUri(url)
                .addQueryParam("appid", apiKey)
                .setContentType(ContentType.JSON)
                .build();

        JsonPath location = given()
                        .spec(req)
                        .basePath("/geo/1.0")
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
