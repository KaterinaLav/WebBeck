import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class Logging extends Abstract {

    @BeforeAll
    static void setUp() {

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

    }

    @Test
    void getRequestLogTest() {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("includeNutrition", "false")
                .log().method()
                .log().params()
                .when()
                .get("https://api.spoonacular.com/recipes/716429/information");

    }
}