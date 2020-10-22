package com.reqres;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.testng.annotations.BeforeTest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Initialization {

    @BeforeTest
    public void init(){

        RestAssured.baseURI="https://reqres.in/api/";

    }
    public static String getJson(String filePath) throws IOException {
        String line;
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String json = "";
        try {
            StringBuilder stringBuilder = new StringBuilder();
            line = reader.readLine();

            while (line != null) {
                stringBuilder.append(line);
                stringBuilder.append("\n");
                line = reader.readLine();
            }
            json = stringBuilder.toString();
//System.out.println("Final Payload is - " + json);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            reader.close();
        }
        return json;
    }

}
