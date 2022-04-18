package tests._11_POST;

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

public class PutRequest01 extends JsonPlaceHolderBaseUrl {

    /*

        https://jsonplaceholder.typicode.com/todos/198 URL ine aşağıdaki body gönerdiğimde
            {
                "userId": 21,
                "title": "Wash the dishes",
                "completed": false
            }
        Dönen response un status kodunun 200 ve body kısmının aşağıdaki gibi olduğunu test edin
            {
                "userId": 21,
                "title": "Wash the dishes",
                 "completed": false,
                 "id": 198
              }

     */


    @Test
    public void test(){

        // url olustur
        spec01.pathParams("1", "todos", "2", "198");

        // expectedData olusturulur
        JsonPlaceHolderTestData testObje = new JsonPlaceHolderTestData();
        JSONObject expectedData= testObje.setupPutData();

        // request gonderilme islemi
        Response response = given().
                contentType(ContentType.JSON).
                spec(spec01).
                auth().basic("admin", "password123").
                body(expectedData.toString()).
                when().
                put("/{1}/{2}");

        response.prettyPrint();

        // De - Serialization

        HashMap<String, Object> actualData = response.as(HashMap.class);

        assertEquals(expectedData.getInt("userId"), actualData.get("userId"));
        assertEquals(expectedData.getString("title"), actualData.get("title"));
        assertEquals(expectedData.getBoolean("completed"), actualData.get("completed"));

        // JsonPath ile
        JsonPath json = response.jsonPath();

        assertEquals(expectedData.getInt("userId"),json.getInt("userId"));
        assertEquals(expectedData.getString("title"),json.getString("title"));
        assertEquals(expectedData.getBoolean("completed"),json.getBoolean("completed"));


    }


}
