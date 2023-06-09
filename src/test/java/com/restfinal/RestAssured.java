package com.restfinal;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

public class RestAssured {

    @Test
    public void endPointShouldReturn200(){
        //see if framework is set up
        get("/vehicle")
                .then()
                .statusCode(200);
    }


    @Test
    public void whenUsePathParamValidId_thenOK(){
        given().pathParam("id", 1)
                .when().get("/vehicle/{id}")
                .then().statusCode(200);
    }

    @Test
    public void whenUsePathParamInvalidId_thenNOT_FOUND(){
        given().pathParam("id", 100)
                .when().get("/vehicle/{id}")
                .then().statusCode(404);
    }

    @Test
    public void getResponseTime(){
        System.out.println("Response Time: " + get("/vehicle/").timeIn(TimeUnit.MILLISECONDS) + "ms");
    }

    @Test
    public void responseContentType(){
        System.out.println("Content Type of reponse: " + get("/vehicle/").then().extract().contentType());
    }

    @Test
    public void saveVehicleShouldReturnId(){
        JSONObject requestBody = new JSONObject();
        requestBody.put("color", "Gray");
        requestBody.put("purchasePrice", "23000");
        requestBody.put("licensePlate", "ABC-123");
        requestBody.put("vin", "JCV32FSEIF35FSDX");

        given().header("Content-Type", "application/json")
                .body(requestBody.toJSONString())
                .post("/vehicle/")
                .then().statusCode(201)
                .assertThat()
                .body("$", hasKey("vehicleId"))
                .body("color", equalTo("Gray"));

    }
    @Test
    public void patchVehicleWithInvalidIdShouldThrowException(){
        JSONObject requestBody = new JSONObject();
        requestBody.put("year", "Dummy Update");
        requestBody.put("color", "Gray");
        requestBody.put("purchasePrice", "Dummy Update");

        given().header("Content-Type", "application/json")
                .body(requestBody.toJSONString())
                .pathParam("id", 100)
                .when()
                .patch("/vehicle/{id}")
                .then()
                .statusCode(404);

    }

    @Test
    public void patchVehicleWithInvalidFieldNameShouldThrowException(){

        JSONObject requestBody = new JSONObject();
        requestBody.put("errorField", "ERROR");
        requestBody.put("color", "Gray");
        requestBody.put("purchasePrice", "Dummy Update");

        given().header("Content-Type", "application/json")
                .body(requestBody.toJSONString())
                .pathParam("id", 1)
                .when()
                .patch("/vehicle/{id}")
                .then()
                .statusCode(404);

    }

    @Test
    public void patchVehicleWithValidFieldsShouldUpdateResource(){


        JSONObject requestBody = new JSONObject();
        requestBody.put("color", "Gray");
        requestBody.put("purchasePrice", "Dummy Update");

        given().header("Content-Type", "application/json")
                .body(requestBody.toJSONString())
                .pathParam("vehicleId", 1)
                .when()
                .patch("/vehicle/{vehicleId}")
                .then()
                .statusCode(202)
                .assertThat()
                .body("color", equalTo("Gray"))
                .body("purchasePrice", equalTo("Dummy Update"));

    }
}
