package tests._06_GET;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import testBase.HerOkuAppBaseUrl;

import static io.restassured.RestAssured.given;

public class GetRequest07 extends HerOkuAppBaseUrl {

    /*
    https://restful-booker.herokuapp.com/booking/5 url’ine bir request yolladigimda
            HTTP Status Code’unun 200
            ve response content type’inin “application/JSON” oldugunu
            ve response body’sinin asagidaki gibi oldugunu test edin
            {
                    "firstname": "Mark",
                    "lastname": "Wilson",
                    "totalprice": 705,
                    "depositpaid": false,
                    "bookingdates": {
                        "checkin": "2018-09-20",
                        "checkout": "2019-04-07"
                    },
                    "additionalneeds": "Breakfast"
            }
     */

    @Test
    public void test(){

        spec02.pathParams("parametre1", "booking",
                "parametre2", 5);

        Response response = given().
                accept("application/json").
                spec(spec02).
                when().
                get("/{parametre1}/{parametre2}");

        response.prettyPrint();

        response.then().
                assertThat().
                statusCode(200).
                contentType("application/json");



        JsonPath json = response.jsonPath();
        // JUnit de assert isleminde once expected sonra actual
        // TestNg de assert isleminde once actual sonra expected

        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals("application/json", response.getContentType());
        Assert.assertEquals("Mark", json.getString("firstname"));
        Assert.assertEquals("Wilson", json.getString("lastname"));
        Assert.assertEquals(705, json.getInt("totalprice"));
        Assert.assertEquals(false,json.getBoolean("depositpaid"));
        Assert.assertEquals("2018-09-20", json.getString("bookingdates.checkin"));
        Assert.assertEquals("2019-04-07", json.getString("bookingdates.checkout"));
        Assert.assertEquals("Breakfast", json.getString("additionalneeds"));





    }

}
