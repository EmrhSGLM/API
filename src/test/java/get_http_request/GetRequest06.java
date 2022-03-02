package get_http_request;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequest06 {

    /*
        https://restful-booker.herokuapp.com/booking/5 url’ine
        accept type’i “application/json” olan GET request’i yolladigimda
        gelen response’un
        status kodunun 200
        ve content type’inin “application/json”
        ve firstname’in “Jim”
        ve totalprice’in 600
        ve checkin date’in 2015-06-12"oldugunu test edin
     */

    @Test
    public void test06(){

        String url = "https://restful-booker.herokuapp.com/booking/20";

        Response response = given().when().get(url);

        response.prettyPrint();

        response.then().contentType("application/json").statusCode(200);

        response.then().assertThat().body("firstname",equalTo("Susan"),
                                          "lastname", equalTo("Jackson"),
                                           "totalprice", equalTo(958),
                                           "depositpaid",equalTo(false),
                                        "bookingdates.checkin",equalTo("2018-12-06"),
                                        "bookingdates.checkout",equalTo("2022-01-23"),
                                        "additionalneeds",equalTo("Breakfast"));

    }
}
