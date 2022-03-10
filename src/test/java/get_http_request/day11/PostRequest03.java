package get_http_request.day11;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import test_data.HerOkuAppTestData;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PostRequest03 extends JsonPlaceHolderBaseUrl {

    /*
            https://jsonplaceholder.typicode.com/todos URL ine aşağıdaki body gönderildiğinde,
             {
             "userId": 55,
             "title": "Tidy your room",
             "completed": false

             Dönen response un Status kodunun 201 ve response body nin aşağıdaki gibi olduğunu test edin

             "userId": 55,
             "title": "Tidy your room",
             "completed": false,
             "id": …
            }
    */

    @Test
    public void test03(){

        // URL olustur
        spec04.pathParams("1", "todos");

        // EXPECTED DATA
        JsonPlaceHolderTestData testData = new JsonPlaceHolderTestData();
        JSONObject expectedRequestData = testData.setUpPostData();
        System.out.println("expectedRequestData = " + expectedRequestData);

        // REQUEST AND RESPONSE

        Response response = given().
                contentType(ContentType.JSON).
                spec(spec04).
                body(expectedRequestData.toString()).
                when().
                post("/{1}");

        response.prettyPrint();

        // DOGRULAMA
        // Matchers Class
            response.then().assertThat().statusCode((Integer) expectedRequestData.get("statusCode")).
                                            body("title", equalTo(expectedRequestData.get("title")),
                                                "userId", equalTo(expectedRequestData.get("userId")),
                                                "id", equalTo(expectedRequestData.get("id")),
                                                 "completed", equalTo(expectedRequestData.get("completed")));

        // JSON Path
        JsonPath json=response.jsonPath();
        Assert.assertEquals(expectedRequestData.get("id"),json.getInt("id"));
        Assert.assertEquals(expectedRequestData.get("userId"),json.getInt("userId"));
        Assert.assertEquals(expectedRequestData.getInt("statusCode"),json.getInt("statusCode"));
        Assert.assertEquals(expectedRequestData.get("title"),json.getString("title"));
        Assert.assertEquals(expectedRequestData.get("completed"),json.getBoolean("completed"));


        // De-Serialization
        HashMap<String, Object> actualRequestData = response.as(HashMap.class);
        System.out.println("actualRequestData = " + actualRequestData);

        Assert.assertEquals(expectedRequestData.get("completed"), actualRequestData.get("completed"));
        Assert.assertEquals(expectedRequestData.get("title"), actualRequestData.get("title"));
        Assert.assertEquals(expectedRequestData.get("id"), actualRequestData.get("id"));
        Assert.assertEquals(expectedRequestData.get("userId"), actualRequestData.get("userId"));
        Assert.assertEquals(expectedRequestData.get("statusCode"), actualRequestData.get("statusCode"));

    }
}
