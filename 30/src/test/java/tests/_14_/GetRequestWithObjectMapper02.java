package tests._14_;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import testBase.HerOkuAppBaseUrl;
import utilities.JsonUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class GetRequestWithObjectMapper02 extends HerOkuAppBaseUrl {

    /*

        https://restful-booker.herokuapp.com/booking/2 url’ine bir get request gönderildiğinde,
        status kodun 200 ve response body’nin
            {
            "firstname": "Mary",
            "lastname": "Brown",
            "totalprice": 562,
            "depositpaid": true,
            "bookingdates": {
                                 "checkin": "2017-05-18",
                                 "checkout": "2021-01-16"
                             },
            "additionalneeds": "Breakfast"
            }
        Olduğunu Object Mapper kullanarak test edin
}

     */

    @Test
    public void test() throws IOException {

        spec02.pathParams("parametre1", "booking", "parametre2", 2);

        String requestBody = "{\n" +
                "\"firstname\": \"Sally\",\n" +
                "\"lastname\": \"Jones\",\n" +
                "\"totalprice\": 163,\n" +
                "\"depositpaid\": false,\n" +
                "\"bookingdates\": {\n" +
                "\"checkin\": \"2021-07-27\",\n" +
                "\"checkout\": \"2022-01-21\"\n" +
                "},\n" +
                "\"additionalneeds\": \"Breakfast\"\n" +
                "}";

        ObjectMapper obj = new ObjectMapper();


        //HashMap<String, Object> expectedData = JsonUtil.convertJsonToJava(requestBody, HashMap.class);
        HashMap<String, Object> expectedData = obj.readValue(requestBody, HashMap.class);
        System.out.println("expectedData = " + expectedData);

        Response response = given().
                contentType(ContentType.JSON).
                spec(spec02).
                when().
                get("/{parametre1}/{parametre2}");

        response.prettyPrint();

        //HashMap<String, Object> actualData = JsonUtil.convertJsonToJava(response.asString(), HashMap.class);
        HashMap<String, Object> actualData = obj.readValue(response.asString(), HashMap.class);

        assertEquals(expectedData.get("firstname"), actualData.get("firstname"));
        assertEquals(expectedData.get("lastname"), actualData.get("lastname"));
        assertEquals(expectedData.get("totalprice"), actualData.get("totalprice"));
        assertEquals(expectedData.get("depositpaid"), actualData.get("depositpaid"));
        assertEquals(((Map)expectedData.get("bookingdates")).get("checkin"),
                ((Map) actualData.get("bookingdates")).get("checkin"));
        assertEquals(((Map)expectedData.get("bookingdates")).get("checkout"),
                ((Map) actualData.get("bookingdates")).get("checkout"));
        assertEquals(expectedData.get("additionalneeds"), actualData.get("additionalneeds"));

    }

}
