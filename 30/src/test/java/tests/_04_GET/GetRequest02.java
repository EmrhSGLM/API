package tests._04_GET;

import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest02 {

    @Test
    public void test01() {

    /*
            https://restful-booker.herokuapp.com/booking url'ine accept type'i "application/json" olan
            GET request'i yolladigimda gelen
            response'un
            status kodunun 200
            content type'inin "application/json"
            oldugunu test edin
     */

        String url = "https://restful-booker.herokuapp.com/booking";

        Response response = given().when().get(url);

        response.prettyPrint();

        response.then().
                assertThat().
                statusCode(200).
                contentType("application/json");
    }

     /*
    https://restful-booker.herokuapp.com/booking/1001 url'ine accept
    type'i "application/json" olan
     GET request'i yolladigimda gelen response'un
    status kodunun 404 oldugunu ve
    Response body'sinin "Not Found" icerdigini ve
    Response body'sinin "API" icermedigini test edin
     */

    @Test
    public void test02(){

        String url = "https://restful-booker.herokuapp.com/booking/1001";

        Response response = given().
                accept("application/json").
                when().
                get(url);

        response.prettyPrint();

        response.then().
                assertThat().
                statusCode(404);

        Assert.assertTrue(response.asString().contains("Not Found"));
        Assert.assertTrue(response.asString().contains("API"));
        // asString() methodu ile json formatında gelen response u String'e cevirilir

    }
}
