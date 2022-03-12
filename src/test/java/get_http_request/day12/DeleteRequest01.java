package get_http_request.day12;

import base_url.DummyBaseUri;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import test_data.DummyTestData;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DeleteRequest01 extends DummyBaseUri {

      /*
          http://dummy.restapiexample.com/api/v1/delete/2 bir DELETE request gönderdiğimde

        Dönen response un status kodunun 200 ve body kısmının aşağıdaki gibi olduğunu test edin
        {
            "status": "success",
            "data": "2",
            "message": "Successfully! Record has been deleted"
        }
   */

    @Test
    public void test01(){

        // 1) URL OLUSTUR
        spec02.pathParams("1","api", "2", "v1", "3", "delete", "4","2");

        // 2) EXPECTED DATA OLUSTUR
        DummyTestData testData = new DummyTestData();
        JSONObject expectedRequest = testData.setUpDeleteExpectedData();
        System.out.println("expectedRequest = " + expectedRequest);

        // 3) REQUEST AND RESPONSE
        Response response = given().
                            contentType(ContentType.JSON).
                            spec(spec02).
                            when().
                            delete("/{1}/{2}/{3}/{4}");

        response.prettyPrint();

        // 4) DOGRULAMA
        // JsonPath
        JsonPath json=response.jsonPath();
        Assert.assertEquals(expectedRequest.getString("status"),json.getString("status"));
        Assert.assertEquals(expectedRequest.getString("data"),json.getString("data"));
        Assert.assertEquals(expectedRequest.getString("message"),json.getString("message"));

        // Matcher Class

        response.then().assertThat().body("status",equalTo(expectedRequest.get("status"))
                ,"data",equalTo(expectedRequest.get("data")),
                "message",equalTo(expectedRequest.get("message")));

        // DDe-Serialaztion
        HashMap<String,Object> actualData=response.as(HashMap.class);

        Assert.assertEquals(expectedRequest.get("status"),actualData.get("status"));
        Assert.assertEquals(expectedRequest.get("data"),actualData.get("data"));
        Assert.assertEquals(expectedRequest.get("message"),actualData.get("message"));



    }





}
