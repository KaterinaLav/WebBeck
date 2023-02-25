import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class ShoppingList extends AbstractTest {
    @Test
    void getConnectUser() {
        given()
                .queryParam(getUsername())
                .queryParam(getHash())
                .pathParam(getId())
                .when()
                .get()
                .then()
                .spec(responseSpecification);
    }
    @Test
    void generateMealPlanet() {
        given()
                .queryParam("timeFrame", "day")
                .queryParam("targetCalories", "2000")
                .queryParam("diet", "vegetarian")
                .queryParam("exclude", "shellfish")
                .body(getUsername())
                .when()
                .post(getBaseUrl()+"mealplanner/{username}/items")
                .then()
                .spec(responseSpecification);
    }
    @Test
    void templareMealPlanet() {
        given()
                .queryParam("timeFrame", "day")
                .queryParam("targetCalories", "2000")
                .queryParam("diet", "vegetarian")
                .queryParam("exclude", "shellfish")
                .pathParam(getUsername())
                .pathParam(getId())
                .queryParam(getHash())
                .when()
                .get(getBaseUrl()+"/mealplanner/{username}/templates/{id}")
                .then()
                .spec(responseSpecification);
    }

    @Test
    void addShoppingList(){
        given()
                .pathParam(getUsername())
                .queryParam(getHash())
                .when()
                .post(getBaseUrl()+"/mealplanner/{username}/shopping-list/items")
                .then()
                .spec(responseSpecification);
    }

   @BeforeAll
   void deleteMealPlanet(){
       ValidatableResponse spec = given()
               .pathParam(getUsername())
               .pathParam(getId())
               .queryParam(getHash())
               .when()
               .delete(getBaseUrl() + "/mealplanner/{username}/templates/{id}")
               .then()
               .spec(responseSpecification);
   }
}
