package com.reqres;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.internal.assertion.Assertion;
import com.jayway.restassured.response.Response;
import com.reqres.pojo.FetchDetails;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;
import static com.reqres.Initialization.getJson;

public class CurdTests {
    /*
     * To Retrieve all the user info but this test should be controlled with the attribute.
     * */
    @Test(enabled = false)
    public void getUsers() {

        try {
            Response res = given().
                    when().get("users");
           FetchDetails.setStatusCode(res.getStatusCode());
            System.out.println("Response code: "+FetchDetails.getStatusCode());
            Assertions.assertThat(FetchDetails.getStatusCode()).isEqualTo(200);
            System.out.println(res.prettyPrint());
        } catch (Exception e) {
            System.out.println("Exception caught is: "+e.getMessage());
        }
    }
    /*
     * To Retrieve a single user info
     * */
    @Test(priority = 1)
    public void getSingleUser(){
        try {
            Response res = given().
                    when().get("users/2");
            FetchDetails.setStatusCode(res.getStatusCode());
            System.out.println("Response code: "+FetchDetails.getStatusCode());
            Assertions.assertThat(FetchDetails.getStatusCode()).isEqualTo(200);
            System.out.println(res.prettyPrint());
        } catch (Exception e) {
            System.out.println("Exception caught is: "+e.getMessage());
        }
    }

    /*
     * Trying to retrieve the user info which does not exist
     * */
    @Test(priority = 2)
    public void userNotFound(){
        try{
            Response res = given().when().get("users/23");
            FetchDetails.setStatusCode(res.getStatusCode());
            System.out.println("Response code: "+FetchDetails.getStatusCode());
            Assertions.assertThat(FetchDetails.getStatusCode()).isEqualTo(404);
            System.out.println(res.prettyPrint());
        }
        catch(Exception e){
            System.out.println("Exception caught is: "+e.getMessage());

        }

    }

    /*
     * Write the POST call for creating a new user into system with the given payload
     * */
    @Test(priority = 3)
    public void createUser(){
        try{

            String filePath = ".\\src\\test\\java\\jsonInputFile\\createUserPayload.json";

            String payLoad = getJson(filePath);
            Response res = given().when().body(payLoad).contentType(ContentType.JSON).post("users").then().log().all().extract().response();
            FetchDetails.setStatusCode(res.getStatusCode());
            System.out.println("Response code: "+FetchDetails.getStatusCode());
            Assertions.assertThat(FetchDetails.getStatusCode()).isEqualTo(201);
            System.out.println(res.prettyPrint());
        }
        catch(Exception e){
            System.out.println("Exception caught is: "+e.getMessage());
        }
    }

    /*
    * Write the call to PUT API for updating the entire resource for an given ID
    * */
    @Test(priority = 4)
    public void updateUser() {
        try {

            String filePath = ".\\src\\test\\java\\jsonInputFile\\updateUserPayload.json";

            String payLoad = getJson(filePath);
            Response res = given().when().body(payLoad).contentType(ContentType.JSON).put("users/2").then().log().all().extract().response();
            FetchDetails.setStatusCode(res.getStatusCode());
            System.out.println("Response code: " + FetchDetails.getStatusCode());
            Assertions.assertThat(FetchDetails.getStatusCode()).isEqualTo(200);
            System.out.println(res.prettyPrint());
        } catch (Exception e) {
            System.out.println("Exception caught is: "+e.getMessage());
        }
    }

    /*
    Register a user by providing the details as in the json files provided and check for the status of endpoint but it's
    execution should be excluded from the other test through testNG.xml file.
    */

    @Test
    public void registerUser(){
        try {

            String filePath = ".\\src\\test\\java\\jsonInputFile\\registerSuccessful.json";

            String payLoad = getJson(filePath);
            Response res = given().when().body(payLoad).contentType(ContentType.JSON).put("users/register").then().log().all().extract().response();
            FetchDetails.setStatusCode(res.getStatusCode());
            System.out.println("Response code: " + FetchDetails.getStatusCode());
            Assertions.assertThat(FetchDetails.getStatusCode()).isEqualTo(200);
            System.out.println(res.prettyPrint());
        } catch (Exception e) {
            System.out.println("Exception caught is: "+e.getMessage());
        }
    }

    /*
    * Write an API request to delete an userid 2 from the list and verify whether the status code is 204
    * */
    @Test(priority = 5)
    public void deleteUser(){
        try{
            Response res = given().when().delete("users/2").then().log().all().extract().response();
            FetchDetails.setStatusCode(res.getStatusCode());
            System.out.println("Response code: "+FetchDetails.getStatusCode());
            Assertions.assertThat(FetchDetails.getStatusCode()).isEqualTo(204);
            System.out.println(res.prettyPrint());
        }
        catch(Exception e){
            System.out.println("Exception caught is: "+e.getMessage());

        }
    }
    }