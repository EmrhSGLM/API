package tests._05_GET;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetRequest05 {

    /*
    http://dummy.restapiexample.com/api/v1/employees url'ine
    accept type'i "application/json" olan GET request'i yolladigimda
    gelen response'un
    status kodunun 200
    ve content type'inin "application/json"
    ve employees sayisinin 24
    ve employee'lerden birinin "Ashton Cox"
    ve gelen icinde 21, 61, ve 23 degerlerinden birinin oldugunu test edin
     */

    @Test
    public void test01(){

        String url = "http://dummy.restapiexample.com/api/v1/employees";

        Response response = given().when().get(url);

        response.then().
                assertThat().
                statusCode(200).
                contentType("application/json").
                body("data.id", hasSize(24),
                        "data.profile_image", hasSize(24),
                        "data.employee_name", hasItem("Ashton Cox"),
                        "data.employee_age", hasItems(21, 61, 23));


        // hasSize(24) => json formatindaki verinin eleman sayısını dondurur
        // hasItem(?) => veri ? iceriyormu (contains)
        // hasItems(?, ?, ?) => veri ?, ?, ? iceriyormu
    }
}
