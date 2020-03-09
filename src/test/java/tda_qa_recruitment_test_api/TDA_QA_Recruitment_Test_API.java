package tda_qa_recruitment_test_api;

import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class TDA_QA_Recruitment_Test_API {

    @Test
    public void healthCheck() {
        System.out.println("******* Health Check *******");
        Response response = given().when()
                .header("app-locale", "en_GB")
                .header("x-api-key", "1b34X9pNjg5lMvRAXLNTS85TQgrzoYLEa3LUQ6Wh")
                .get("https://4s9rh46bpe.execute-api.eu-central-1.amazonaws.com/test/healthcheck");
        response.prettyPrint();
        Assert.assertEquals(200, response.getStatusCode());
    }

    @Test
    public void loginDE() {
        System.out.println("******* Login DE *******");
        String payload = "{\n" +
                "    \"username\": \"test\",\n" +
                "    \"password\": \"test\"\n" +
                "}";
        Response response = given()
                .header("device-os", "ios")
                .header("app-locale", "de_DE")
                .header("x-api-key", "1b34X9pNjg5lMvRAXLNTS85TQgrzoYLEa3LUQ6Wh")
                .contentType("application/json").when().body(payload)
                .post("https://4s9rh46bpe.execute-api.eu-central-1.amazonaws.com/test/auth/login");
        response.prettyPrint();
        Assert.assertEquals(200, response.getStatusCode());
    }

    @Test
    public void loginUK() {
        String loginToken;
        System.out.println("******* Login UK *******");
        Response response = given().when()
                .param("bookingRef", "AR58930")
                .param("surname", "Smith")
                .and()
                .header("device-os", "ios")
                .header("app-locale", "en_GB")
                .header("x-api-key", "1b34X9pNjg5lMvRAXLNTS85TQgrzoYLEa3LUQ6Wh")
                .get("https://4s9rh46bpe.execute-api.eu-central-1.amazonaws.com/test/auth/login");
        response.prettyPrint();
        Assert.assertEquals(200, response.getStatusCode());
        loginToken = response.getHeader("login-token");
        System.out.println("Login Token: " + loginToken);
//    }
//
//    @Test
//    public void bookingInfo() {
        System.out.println("******* Booking Info *******");
        response = given()
                .header("device-os", "android", "ios")
                .header("app-locale", "en_GB", "de_DE")
                .header("x-api-key", "1b34X9pNjg5lMvRAXLNTS85TQgrzoYLEa3LUQ6Wh")
                .header("login-token", loginToken)
                .header("id", "ADE9452")
                .get("https://4s9rh46bpe.execute-api.eu-central-1.amazonaws.com/test/v1/bookings/ADE9452");
        response.prettyPrint();
        Assert.assertEquals(200, response.getStatusCode());
//    }
//
//    @Test
//    public void availableBookings() {
        System.out.println("******* Available Booking *******");
        response = given().when()
                .header("device-os", "android")
                .header("app-locale", "en_GB")
                .header("x-api-key", "1b34X9pNjg5lMvRAXLNTS85TQgrzoYLEa3LUQ6Wh")
                .header("login-token", loginToken)
                .get("https://4s9rh46bpe.execute-api.eu-central-1.amazonaws.com/test/v1/bookings");
        response.prettyPrint();
        Assert.assertEquals(200, response.getStatusCode());
//    }
//
//    @Test
//    public void weather() {
        System.out.println("******* Weather *******");
        response = given()
                .header("device-os", "ios")
                .header("app-locale", "de_DE")
                .header("x-api-key", "1b34X9pNjg5lMvRAXLNTS85TQgrzoYLEa3LUQ6Wh")
                .header("login-token", loginToken)
                .header("id", "ADE9452")
                .get("https://4s9rh46bpe.execute-api.eu-central-1.amazonaws.com/test/v1/bookings/ADE9452/weather");
        response.prettyPrint();
        Assert.assertEquals(200, response.getStatusCode());
    }
}
