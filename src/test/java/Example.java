import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class Example extends Abstract {
    @Test
    void getSpecifyingRequestDataTest() {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("includeNutrition", "false")
                .pathParam("id", 716429)
                .when()
                .get(getBaseUrl() + "recipes/{id}/information")
                .then()
                .statusCode(200);
        

    }
}