package get_http_request.day11;

import base_url.DummyBaseUri;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import test_data.DummyTestData;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class PostRequest02 extends DummyBaseUri {

    /*
        http://dummy.restapiexample.com/api/v1/create url ine, Request Body olarak
        {
            "name":"Ali Can",
            "salary":"2000",
            "age":"40",
        }
        gönderildiğinde,Status kodun 200 olduğunu ve dönen response body nin,

        {
            "status": "success",
            "data": {
            "id":…
        },
            "message": "Successfully! Record has been added."
        }

        olduğunu test edin
    */

    @Test
    public void test24(){

        // 1) URL OLUSTUR
        spec02.pathParams("1", "api", "2", "v1", "3", "create");

        // 2) EXPECTED DATA OLUSTUR
        DummyTestData obje = new DummyTestData();

        // Request icin
        HashMap<String, Object> requestBodyMap = obje.setUpRequestBody();

        // Expected Data icin
        HashMap<String, Object> expectedDataMap = obje.setUpExpectedData();
        System.out.println("expecdetDataMap = " + expectedDataMap);

        // 3) REQUEST AND RESPONSE
        Response response = given().
                contentType(ContentType.JSON).
                spec(spec02).
                body(requestBodyMap).
                when().
                post("/{1}/{2}/{3}");

        // POST isleminde, Map kullandigimizda toString kullanmaya gerek yok
        response.prettyPrint();

        // 4) DOGRULAMA
        //    De-Serialization
        HashMap<String, Object> actualDataMap = response.as(HashMap.class);

        System.out.println("actualDataMap = " + actualDataMap);

        Assert.assertEquals(expectedDataMap.get("statusCode"), response.getStatusCode());
        Assert.assertEquals(expectedDataMap.get("status"), actualDataMap.get("status"));
        Assert.assertEquals(expectedDataMap.get("message"), actualDataMap.get("message"));

        // JsonPath
        JsonPath jsonPath=response.jsonPath();

        Assert.assertEquals(expectedDataMap.get("statusCode"),response.statusCode());
        Assert.assertEquals(expectedDataMap.get("message"),jsonPath.getString("message"));
        Assert.assertEquals(expectedDataMap.get("status"),jsonPath.getString("status"));




    }

}
