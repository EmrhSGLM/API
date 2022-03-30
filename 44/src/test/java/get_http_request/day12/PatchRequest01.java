package get_http_request.day12;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PatchRequest01 extends JsonPlaceHolderBaseUrl {

    /*
               https://jsonplaceholder.typicode.com/todos/198 URL ine aşağıdaki body gönderdiğimde
              {

                 "title": "Batch44"

                }
            Dönen response un status kodunun 200 ve body kısmının aşağıdaki gibi olduğunu test edin
            {
            "userId": 10,
            "title": "Batch44"
            "completed": true,
            "id": 198
            }
    */

    @Test
    public void test01(){

        // 1) URL OLUSTUR
        spec04.pathParams("1", "todos", "2", "198");

        // 2) EXPECTED DATA OLUSTUR
        JsonPlaceHolderTestData testData= new JsonPlaceHolderTestData();
        JSONObject requestData = testData.setUpPatchRequestData();
        System.out.println("requestData = " + requestData);

        JSONObject expectedRequest = testData.setUpPatchExpecdetData();
        System.out.println("expectedRequest = " + expectedRequest);

        // 3) Response nd Request
        Response response = given().spec(spec04).
                            contentType("application/json").
                            auth().
                            basic("admin", "password123").
                            body(requestData.toString()).
                            when().
                            patch("/{1}/{2}");
        response.prettyPrint();

        // Dogrulama
        // 1) JSonPath
        JsonPath json = response.jsonPath();
        Assert.assertEquals(expectedRequest.get("userId"), json.getInt("userId"));
        Assert.assertEquals(expectedRequest.get("title"), json.getString("title"));
        Assert.assertEquals(expectedRequest.get("completed"), json.getBoolean("completed"));
        Assert.assertEquals(expectedRequest.get("id"), json.getInt("id"));

        // 2) Matcher Class
        response.then().assertThat().statusCode(200).
                                     body("userId", equalTo(expectedRequest.get("userId")),
                                     "title", equalTo(expectedRequest.get("title")),
                                     "completed", equalTo(expectedRequest.get("completed")),
                                     "id", equalTo(expectedRequest.get("id"))
                                     );

        // 3) De-Serialization
        HashMap<String, Object> actualData = response.as(HashMap.class);
        Assert.assertEquals(expectedRequest.get("userId"), actualData.get("userId"));
        Assert.assertEquals(expectedRequest.get("title"), actualData.get("title"));
        Assert.assertEquals(expectedRequest.get("completed"), actualData.get("completed"));
        Assert.assertEquals(expectedRequest.get("id"), actualData.get("id"));


    }


}
