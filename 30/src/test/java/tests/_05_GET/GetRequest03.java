package tests._05_GET;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequest03 {

    /*
    https://restful-booker.herokuapp.com/booking/7 url'ine
    accept type'i "application/json" olan GET request'i yolladigimda
    gelen response'un status kodunun 200 ve
    content type'inin "application/json" ve
    {
    "firstname": "Emrah",
    "lastname": "Saglam",
    "totalprice": 6871,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2016-11-30",
        "checkout": "2017-08-12"
    },
    "additionalneeds": "Breakfast"
}
     oldugunu test edin
     */

    @Test
    public void test01(){

        String url = "https://restful-booker.herokuapp.com/booking/18";

        Response response = given().when().get(url);

        response.prettyPrint();

        response.then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON);

        response.then().assertThat().body("firstname", equalTo("Emrah"),
                                        "lastname", equalTo("Saglam"),
                                            "totalprice", equalTo(6871),
                                            "depositpaid", equalTo(true),
                                            "bookingdates.checkin", equalTo("2016-11-30"),
                                            "bookingdates.checkout", equalTo("2017-08-12"),
                                            "additionalneeds", equalTo("Breakfast"));

        // We can do verify this way too
        response.then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("firstname", equalTo("Emrah"),
                        "lastname", equalTo("Saglam"),
                        "totalprice", equalTo(6871),
                        "depositpaid", equalTo(true),
                        "bookingdates.checkin", equalTo("2016-11-30"),
                        "bookingdates.checkout", equalTo("2017-08-12"),
                        "additionalneeds", equalTo("Breakfast"));

    }
}
