package tests._12_PatchDelete;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;
import testBase.JsonPlaceHolderBaseUrl;
import testData.JsonPlaceHolderTestData;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class PatchRequest01 extends JsonPlaceHolderBaseUrl {

    /*
    https://jsonplaceholder.typicode.com/todos/198 URL ine aşağıdaki body gönderdiğimde
        {
            "title": "API calismaliyim"
        }
    Dönen response un status kodunun 200 ve body kısmının aşağıdaki gibi olduğunu test edin
        {
            "userId": 10,
            "title": "API calismaliyim",
             "completed": true,
             "id": 198
         }
     */

    @Test
    public void test(){

        // URL olustur
        spec01.pathParams("1", "todos", "2", "198");

        // expected ve request data olustur

        JsonPlaceHolderTestData testData = new JsonPlaceHolderTestData();
        JSONObject requestData = testData.setupPatchRequestData();
        JSONObject expectedData = testData.expectedDataPatchRequest();

        // request gonder
        Response response = given().
                contentType(ContentType.JSON).
                spec(spec01).
                auth().basic("admin", "password123").
                body(requestData.toString()).
                when().
                patch("/{1}/{2}");

        response.prettyPrint();

        // Jsonpath
        JsonPath json = response.jsonPath();
        assertEquals(expectedData.getInt("userId"),json.getInt("userId"));
        assertEquals(expectedData.getInt("id"),json.getInt("id"));
        assertEquals(expectedData.getString("title"),json.getString("title"));
        assertEquals(expectedData.getBoolean("completed"),json.getBoolean("completed"));

        // De-Serialization
        HashMap<String, Object> actualData = response.as(HashMap.class);

        assertEquals(expectedData.getInt("userId"), actualData.get("userId"));
        assertEquals(expectedData.getInt("id"), actualData.get("id"));
        assertEquals(expectedData.getString("title"), actualData.get("title"));
        assertEquals(expectedData.getBoolean("completed"), actualData.get("completed"));

    }


}
