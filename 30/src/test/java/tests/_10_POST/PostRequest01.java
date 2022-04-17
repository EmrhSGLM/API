package tests._10_POST;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import testBase.DummyBaseUrl;
import testData.DummyTestData;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class PostRequest01 extends DummyBaseUrl {
    /*
    PostRequest01: http://dummy.restapiexample.com/api/v1/create url ine, Request Body olarak

        { "name":"Mehmet Emrah",
        "salary":"1000",
         "age":"34",
         "profile_image": ""
         }
        gönderildiğinde, Status kodun 200 olduğunu ve dönen response body nin ,

        { "status": "success",
         "data": {
         “id”:…
         },
          "message": "Successfully! Record has been added." }

        olduğunu test edin
     */

    @Test
    public void test(){

        spec03.pathParams("1","create");

        DummyTestData obje = new DummyTestData();

        // Post request yaparken biz body gondermek zorundayız,
        // testdata class ında olusturdugumuz request body i burada cagiriyoruz
        HashMap<String, String> requestBodyMap = obje.setUpRequestBody();

        HashMap<String, Object> expectedDataMap  = obje.setupExpectedData();

        Response response = given().
                accept("application/json").
                spec(spec03).auth().
                basic("admin","password123").
                body(requestBodyMap).
                when().
                post("/{1}");

        response.prettyPrint();

        // De-Serialization

        HashMap<String, Object> actualDataMap = response.as(HashMap.class);
        assertEquals(expectedDataMap.get("statusCode"),response.getStatusCode());
        assertEquals(expectedDataMap.get("status"),actualDataMap.get("status"));
        assertEquals(expectedDataMap.get("message"),actualDataMap.get("message"));

        // JsonPath
        JsonPath json = response.jsonPath();
        assertEquals(expectedDataMap.get("status"),json.getString("status"));
        assertEquals(expectedDataMap.get("message"),json.getString("message"));

    }
}
