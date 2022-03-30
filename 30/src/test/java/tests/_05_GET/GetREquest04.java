package tests._05_GET;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetREquest04 {

    /*
    https://restful-booker.herokuapp.com/booking/5 url'ine
    accept type'i "application/json" olan
    GET request'i yolladigimda gelen response'un status kodunun 200 ve
    content type'inin "application/json" ve
    firstname'in “Jim" ve
    totalprice’in 600 ve
    checkin date'in 2015-06-12"
    oldugunu test edin
    {
    "firstname": "Mary",
    "lastname": "Ericsson",
    "totalprice": 142,
    "depositpaid": false,
    "bookingdates": {
        "checkin": "2017-04-19",
        "checkout": "2019-10-07"
    }
}
     */

    @Test
    public void test01(){

        String url="https://restful-booker.herokuapp.com/booking/12 ";

        Response response = given().when().get(url);

        response.then().
                assertThat().
                contentType(ContentType.JSON).
                statusCode(200).
                body("firstname", equalTo("Mary"),
                        "lastname", equalTo("Ericsson"),
                        "totalprice", equalTo(142),
                        "depositpaid", equalTo(false),
                        "bookingdates.checkin", equalTo("2017-04-19"),
                        "bookingdates.checkout", equalTo("2019-10-07"));

    }
}
