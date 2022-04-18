package tests._10_POST;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;
import testBase.HerOkuAppBaseUrl;
import testData.HerOkuappTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class PostRequest02 extends HerOkuAppBaseUrl {

    /*
    https://restful-booker.herokuapp.com/booking url ine, Request Body olarak
        "{
                firstname": "Selim",
                lastname": "Ak",
                totalprice": 11111,
                "depositpaid": true,
                "bookingdates": {
                           "checkin": "2020-09-09",
                            "checkout": "2020-09-21" }
           }
           gönderildiğinde, Status kodun 200 olduğunu ve dönen response body nin ,
            booking": {
                    "firstname": " Selim ",
                    "lastname": " Ak ",
                    "totalprice": 11111,
                    "depositpaid": true,
                    "bookingdates": {
                            "checkin": "2020-09-01",
                            "checkout": " 2020-09-21”
            },
        }
        olduğunu test edin
     */

    @Test
    public void test(){

        // Url olusturulur
        spec02.pathParam("1", "booking");

        // requestBody ve expected Data aynı oldugu icin tek bir JSONObject kullanılması yeterlidir

        HerOkuappTestData testData = new HerOkuappTestData();
        JSONObject expectedRequestData = testData.setupTestAndRequestData();

        // request gonder

        Response response = given().
                contentType(ContentType.JSON).
                spec(spec02).
                body(expectedRequestData.toString()). // JSONObject de toString kullanılması gerekir
                auth().
                basic("admin", "password123").
                when().
                post("/{1}");

        response.prettyPrint();

        // De - Serialization

        HashMap<String, Object> actualData = response.as(HashMap.class);

        assertEquals(expectedRequestData.getString("firstname"),
                ((Map) actualData.get("booking")).get("firstname"));
        assertEquals(expectedRequestData.getString("lastname"),
                ((Map) actualData.get("booking")).get("lastname"));
        assertEquals(expectedRequestData.getInt("totalprice"),
                ((Map) actualData.get("booking")).get("totalprice"));
        assertEquals(expectedRequestData.getBoolean("depositpaid"),
                ((Map) actualData.get("booking")).get("depositpaid"));
        assertEquals(expectedRequestData.getJSONObject("bookingdates").getString("checkin"),
                ((Map) ((Map) actualData.get("booking")).get("bookingdates")).get("checkin"));
        assertEquals(expectedRequestData.getJSONObject("bookingdates").getString("checkout"),
                ((Map) ((Map) actualData.get("booking")).get("bookingdates")).get("checkout"));


        // JsonPath yontemi ile

        JsonPath json = response.jsonPath();
        assertEquals(expectedRequestData.getString("firstname"),
                json.getString("booking.firstname"));
        assertEquals(expectedRequestData.getString("lastname"),
                json.getString("booking.lastname"));
        assertEquals(expectedRequestData.getInt("totalprice"),
                json.getInt("booking.totalprice"));
        assertEquals(expectedRequestData.getBoolean("depositpaid"),
                json.getBoolean("booking.depositpaid"));
        assertEquals(expectedRequestData.getJSONObject("bookingdates").getString("checkin"),
                json.get("booking.bookingdates.checkin"));
        assertEquals(expectedRequestData.getJSONObject("bookingdates").getString("checkout"),
                json.get("booking.bookingdates.checkout"));

    }



}
