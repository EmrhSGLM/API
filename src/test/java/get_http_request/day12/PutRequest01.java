package get_http_request.day12;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

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
    public void test01(){

        // 1) URL OLUSTUR
        spec04.pathParams("1", "todos", "2", "198");

        // 2) EXPECTED DATA OLUSTUR
        JsonPlaceHolderTestData testObje = new JsonPlaceHolderTestData();
        JSONObject expectedRequest = testObje.setPutData();
        System.out.println("expectedRequest = " + expectedRequest);

        // 3) RESPONSE AND REQUEST
        Response response = given().
                            contentType(ContentType.JSON).
                            spec(spec04).
                            body(expectedRequest.toString()).
                            when().
                            put("/{1}/{2}");

        response.prettyPrint();

        // 4) DOGRULAMA
        // JSonPath
        JsonPath json = response.jsonPath();

        Assert.assertEquals(expectedRequest.get("completed"), json.getBoolean("completed"));
        Assert.assertEquals(expectedRequest.get("title"), json.getString("title"));
        Assert.assertEquals(expectedRequest.get("userId"), json.getInt("userId"));

        // Matcher Class
        response.then().assertThat().statusCode(200).
                                            body("completed", equalTo(expectedRequest.get("completed")),
                                            "title", equalTo(expectedRequest.get("title")),
                                            "userId", equalTo(expectedRequest.get("userId")));


        // De-Serialization
        HashMap<String, Object> actuaalData = response.as(HashMap.class);

        Assert.assertEquals(expectedRequest.get("completed"), actuaalData.get("completed"));
        Assert.assertEquals(expectedRequest.get("title"), actuaalData.get("title"));
        Assert.assertEquals(expectedRequest.get("userId"), actuaalData.get("userId"));


    }



}
