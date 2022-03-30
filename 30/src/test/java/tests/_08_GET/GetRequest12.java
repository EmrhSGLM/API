package tests._08_GET;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import testBase.HerOkuAppBaseUrl;
import testData.HerOkuappTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequest12 extends HerOkuAppBaseUrl {

    /*
    https://restful-booker.herokuapp.com/booking/1 url ine bir istek gönderildiğinde
    dönen response body nin
               {
                    "firstname": "Mary",
                     "lastname": "Wilson",
                     "totalprice": 854,
                     "depositpaid": true,
                     "bookingdates": {
                              "checkin": "2017-02-09",
                              "checkout": "2020-10-09"
                      }
                }
     gibi olduğunu test edin.
     */

    @Test
    public void test(){

        spec02.pathParams("parametre1", "booking",
                "parametre2", 1);

        HerOkuappTestData expectedObje = new HerOkuappTestData();

        Response response = given().
                accept("application/json").
                spec(spec02).
                when().
                get("/{parametre1}/{parametre2}");

        HashMap<String, Object> expectedData = expectedObje.setupTestData();

        // JsonPath

        JsonPath json = response.jsonPath();
        Assert.assertEquals(expectedData.get("firstname"),json.getString("firstname"));
        Assert.assertEquals(expectedData.get("lastname"),json.getString("lastname"));
        Assert.assertEquals(expectedData.get("totalprice"),json.getInt("totalprice"));
        Assert.assertEquals(expectedData.get("depositpaid"),json.getBoolean("depositpaid"));
        Assert.assertEquals(((HashMap)expectedData.get("bookingdates")).get("checkin"),
                json.getString("bookingdates.checkin"));
        Assert.assertEquals(((HashMap)expectedData.get("bookingdates")).get("checkout"),
                        json.getString("bookingdates.checkout"));

        // De-Serilization
        HashMap<String, Object> actualData = response.as(HashMap.class);

        Assert.assertEquals(expectedData.get("firstname"), actualData.get("firstname"));
        Assert.assertEquals(expectedData.get("lastname"), actualData.get("lastname"));
        Assert.assertEquals(expectedData.get("totalprice"), actualData.get("totalprice"));
        Assert.assertEquals(expectedData.get("depositpaid"), actualData.get("depositpaid"));
        Assert.assertEquals(((HashMap)expectedData.get("bookingdates")).get("checkin"),
                ((HashMap)actualData.get("bookingdates")).get("checkin"));
        Assert.assertEquals(((HashMap)expectedData.get("bookingdates")).get("checkout"),
                ((HashMap)actualData.get("bookingdates")).get("checkout"));

    }

}
