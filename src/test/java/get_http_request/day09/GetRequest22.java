package get_http_request.day09;

import base_url.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Test;
import test_data.HerOkuAppTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequest22 extends HerOkuAppBaseUrl {

    /*
           https://restful-booker.herokuapp.com/booking/47
                  {
                      "firstname": "Ali",
                      "lastname": "Can",
                      "totalprice": 500,
                      "depositpaid": true,
                      "bookingdates": {
                          "checkin": "2022-02-01",
                          "checkout": "2022-02-11"
                     }
                  }
           1) De-Serialization
           2) JsonPath
    */

    @Test
    public void test22(){

        // 1) URL olustur
        spec05.pathParams("1", "booking", "2", 11);

        // 2-) EXPECTED DATA OLUSTUR
        HashMap<String, Object> expectedDataTestDataMap = HerOkuAppTestData.setupTestData();

        //
        Response response = given().spec(spec05).when().get("/{1}/{2}");
        response.prettyPrint();

        response.then().
                assertThat().
                statusCode((Integer) expectedDataTestDataMap.get("statusCode")).
                contentType(ContentType.JSON);

        // 4) DOGRULAMA
        //1. De-Serialization
        HashMap<String, Object> actualData = response.as(HashMap.class);
        // JSON formatındaki data yı HashMap'e donusturur

        Assert.assertEquals(expectedDataTestDataMap.get("firstname"), actualData.get("firstname"));
        Assert.assertEquals(expectedDataTestDataMap.get("lastname"), actualData.get("lastname"));
        Assert.assertEquals(expectedDataTestDataMap.get("totalprice"), actualData.get("totalprice"));
        Assert.assertEquals(expectedDataTestDataMap.get("depositpaid"), actualData.get("depositpaid"));

        Assert.assertEquals(((Map)expectedDataTestDataMap.get("bookingdates")).get("checkin"),
                                    ((Map)actualData.get("bookingdates")).get("checkin"));
        Assert.assertEquals(((Map)expectedDataTestDataMap.get("bookingdates")).get("checkout"),
                                    ((Map)actualData.get("bookingdates")).get("checkout"));



        //2. JsonPath
        JsonPath json = response.jsonPath();
        Assert.assertEquals(json.getString("firstname"), expectedDataTestDataMap.get("firstname"));
        Assert.assertEquals(json.getString("lastname"), expectedDataTestDataMap.get("lastname"));
        Assert.assertEquals(json.getInt("totalprice"), expectedDataTestDataMap.get("totalprice"));
        Assert.assertEquals(json.getBoolean("depositpaid"), expectedDataTestDataMap.get("depositpaid"));
        Assert.assertEquals(json.getString("bookingdates.checkin"),
                                        ((Map)expectedDataTestDataMap.get("bookingdates")).get("checkin"));
        Assert.assertEquals(json.getString("bookingdates.checkout"),
                                            ((Map)expectedDataTestDataMap.get("bookingdates")).get("checkout"));



    }


}
