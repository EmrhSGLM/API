package tests._14_;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class ReqresToken {

    public String tokenAl(){

        String url = "https://reqres.in/api/login";

        // {
        //    "email": "eve.holt@reqres.in",
        //    "password": "cityslicka"
        //}
        HashMap<String, Object> requestBody = new HashMap<String, Object>();
        requestBody.put("email", "eve.holt@reqres.in");
        requestBody.put("password", "cityslicka");

        //System.out.println("requestBody = " + requestBody);
        Response response = given().
                contentType(ContentType.JSON).
                body(requestBody).
                when().
                post(url);

       //response.prettyPrint();
        JsonPath json = response.jsonPath();

        String token = json.getString("token");

        return token;
    }


}
