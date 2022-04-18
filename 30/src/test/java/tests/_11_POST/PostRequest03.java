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
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;

public class PostRequest03 extends JsonPlaceHolderBaseUrl {

    /*

        https://jsonplaceholder.typicode.com/todos URL ine aşağıdaki body gönderildiğinde,
        { "userId": 55,
        "title": "Tidy your room",
        "completed": false
        }

        Dönen response un Status kodunun 201 ve response body nin aşağıdaki gibi olduğunu test edin
        { "userId": 55,
        "title": "Tidy your room",
        "completed": false,
         "id": … }

     */

    @Test
    public void test(){

        // URL olustur
        spec01.pathParam("parametre1", "todos");

        // expectedData olusturulur
        JsonPlaceHolderTestData testObje = new JsonPlaceHolderTestData();
        JSONObject expectedData = testObje.setupTestAndRequestData();

        // request olusturulur
        Response response = given().
                contentType(ContentType.JSON).
                spec(spec01).
                auth().basic("admin", "password123").
                body(expectedData.toString()).
                when().
                post("/{parametre1}");

        response.prettyPrint();

        // Matchers class

        response.then().assertThat().
                statusCode(201).
                body("completed", equalTo(expectedData.getBoolean("completed")),
                        "userId", equalTo(expectedData.getInt("userId")),
                        "title", equalTo(expectedData.getString("title")));

        // De - Serialization

        HashMap<String, Object> actualData = response.as(HashMap.class);

        assertEquals(expectedData.getInt("userId"), actualData.get("userId"));
        assertEquals(expectedData.getString("title"), actualData.get("title"));
        assertEquals(expectedData.getBoolean("completed"), actualData.get("completed"));

        // JsonPath yontemi ile
        JsonPath json = response.jsonPath();
        assertEquals(expectedData.getInt("userId"), json.getInt("userId"));
        assertEquals(expectedData.getString("title"), json.getString("title"));
        assertEquals(expectedData.getBoolean("completed"), json.getBoolean("completed"));

    }
}
