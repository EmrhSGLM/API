package get_http_request.day14;

import base_url.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utilities.JsonUtil;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequestObjectMapper02 extends HerOkuAppBaseUrl {

    /*
    https://restful-booker.herokuapp.com/booking/40 url’ine bir get request gönderildiğinde,
         status kodun 200 ve response body’nin
        {
                “firstname”: “Ali”,
                “lastname”: “Can”,
                “totalprice”: 500,
                “depositpaid”: true,
                “bookingdates”: {
                “checkin”: “2022-03-01”,
                “checkout”: “2022-03-11”
        },
        “additionalneeds”: “Breakfast”
        }
        Olduğunu Object Mapper kullanarak test edin
     */

    @Test
    public void test02(){

        // 1) URL OLUSTURULUR
        spec05.pathParams("param1", "booking","param2", 11);

        // 2) Expected Data
        //String jsonData = "{\n" +
        //        " \"“firstname\"”: \"“Ali\"”,\n" +
        //        " \"“lastname\"”: \"“Can\"”,\n" +
        //        " \"“totalprice\"”: 500,\n" +
        //        " \"“depositpaid\"”: true,\n" +
        //        " \"“bookingdates\"”: {\n" +
        //        "\"“checkin\"”: \"“2022-03-01\"”,\n" +
        //        "\"“checkout\"”: \"“2022-03-11\"”\n" +
        //        " \"},\n" +
        //        " \"“additionalneeds\"”: \"“Breakfast\"”\n" +
        //        " \"}";
        String jsonData="{\n" +
                "   \"firstname\": \"Ali\",\n" +
                "   \"lastname\": \"Can\",\n" +
                "   \"totalprice\": 500,\n" +
                "   \"depositpaid\": true,\n" +
                "   \"bookingdates\": {\n" +
                "   \"checkin\": \"2022-03-01\",\n" +
                "   \"checkout\": \"2022-03-11\"\n" +
                "    },\n" +
                "    \"additionalneeds\": \"Breakfast\"\n" +
                "    }";
        System.out.println("jsonData = " + jsonData);


        HashMap<String, Object> expectedData = JsonUtil.convertJsonToJava(jsonData, HashMap.class);
        System.out.println("expectedData = " + expectedData);

        // 3) Request ve Response

        Response response = given().
                spec(spec05).
                contentType(ContentType.JSON).
                when().
                get("/{param1}/{param2}");
        response.prettyPrint();


        // 4) Dogrulama
        HashMap<String, Object> actualData = JsonUtil.convertJsonToJava(response.asString(), HashMap.class);

        Assert.assertEquals(actualData.get("firstname"), expectedData.get("firstname"));
        Assert.assertEquals(actualData.get("lastname"), expectedData.get("lastname"));
        Assert.assertEquals(actualData.get("totalprice"), expectedData.get("totalprice"));
        Assert.assertEquals(actualData.get("depositpaid"), expectedData.get("depositpaid"));
        Assert.assertEquals(actualData.get("bookingdates.checkin"), expectedData.get("bookingdates.checkin"));
        Assert.assertEquals(actualData.get("bookingdates.checkout"), expectedData.get("bookingdates.checkout"));
        Assert.assertEquals(actualData.get("additionalneeds"), expectedData.get("additionalneeds"));

        Assert.assertEquals(((Map<?, ?>)expectedData.get("bookingdates")).get("checkin"),
                ((Map<?, ?>)actualData.get("bookingdates")).get("checkin"));
        Assert.assertEquals(((Map<?, ?>)expectedData.get("bookingdates")).get("checkout"),
                ((Map<?, ?>)actualData.get("bookingdates")).get("checkout"));

        //Matcher ile
        response.then().
                assertThat().
                statusCode(200).
                body("firstname", equalTo(expectedData.get("firstname"))
                        ,"lastname",equalTo(expectedData.get("lastname"))
                        ,"totalprice",equalTo(expectedData.get("totalprice"))
                        ,"depositpaid",equalTo(expectedData.get("depositpaid"))
                        ,"bookingdates.checkin",equalTo(((Map)expectedData.get("bookingdates")).get("checkin"))
                        ,"bookingdates.checkout",equalTo(((Map)expectedData.get("bookingdates")).get("checkout")));


    }
}
