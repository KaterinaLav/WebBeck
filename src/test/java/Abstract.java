import io.restassured.mapper.ObjectMapperType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class Abstract {
    static Properties prop = new Properties();
    private static InputStream configFile;
    private static String apiKey;
    private static String baseUrl;

    @BeforeAll
    static void initTest() throws IOException {
        configFile = new FileInputStream("src/main/resources/my.properties");
        prop.load(configFile);

        apiKey =  prop.getProperty("apiKey");
        baseUrl= prop.getProperty("base_url");
    }

    public static String getApiKey() {
        return apiKey;
    }

    public static String getBaseUrl() {
        return baseUrl;
    }

    public static class ShoppingList extends Abstract {
        @Test
        void getConnectUser() {
            given()
                    .queryParam("username", "LavruhinaEA@yandex.ru")
                    .queryParam("hash", "Deti151719")
                    .pathParam("id", 716429)
                    .when()
                    .then()
                    .statusCode(200);
        }
        @Test
        void generateMealPlanet() {
            given()
                    .queryParam("timeFrame", "day")
                    .queryParam("targetCalories", "2000")
                    .queryParam("diet", "vegetarian")
                    .queryParam("exclude", "shellfish")
                    .body("username", ObjectMapperType.valueOf("LavruhinaEA@yandex.ru"))
                    .when()
                    .post(getBaseUrl()+"mealplanner/{username}/items")
                    .then()
                    .statusCode(200);
        }
        @Test
        void templareMealPlanet() {
            given()
                    .queryParam("timeFrame", "day")
                    .queryParam("targetCalories", "2000")
                    .queryParam("diet", "vegetarian")
                    .queryParam("exclude", "shellfish")
                    .pathParam("username", "LavruhinaEA@yandex.ru")
                    .pathParam("id", "01")
                    .queryParam("hash", "Deti151719")
                    .when()
                    .get(getBaseUrl()+"/mealplanner/{username}/templates/{id}")
                    .then()
                    .statusCode(200);
        }

        @Test
        void addShoppingList(){
            given()
                    .pathParam("username", "LavruhinaEA@yandex.ru")
                    .queryParam("hash", "Deti151719")
                    .when()
                    .get(getBaseUrl()+"/mealplanner/{username}/shopping-list/items")
                    .then()
                    .statusCode(200);
        }

        @BeforeAll
        void deleteMealPlanet(){
            given()
                    .pathParam("username", "LavruhinaEA@yandex.ru")
                    .pathParam("id", "01")
                    .queryParam("hash", "Deti151719")
                    .when()
                    .delete(getBaseUrl()+"/mealplanner/{username}/templates/{id}")
                    .then()
                    .statusCode(200);
        }
    }
}
