package tests._05_GET;

import io.restassured.response.Response;
import org.junit.Test;
import testBase.JsonPlaceHolderBaseUrl;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequest06 extends JsonPlaceHolderBaseUrl {

    /*
    https://jsonplaceholder.typicode.com/todos/123 url'ine
    accept type'i "application/json" olan GET request'i yolladigimda
    gelen response’un
    status kodunun 200
    ve content type'inin "application/json"
    ve Headers'daki "Server" in "cloudflare"
    ve response body'deki
    "userId"'nin 7
    ve "title" in "esse et quis iste est earum aut impedit"
    ve "completed" bolumunun false oldugunu test edin
     */

    @Test
    public void test01(){

        spec01.pathParams("parametre1", "todos",
                "parametre2", 123);

        Response response = given().
                accept("application.json"). // API bir den fazla formatta request donderiyorsa kullanmak zorundayız
                spec(spec01).
                when().
                get("/{parametre1}/{parametre2}");

        response.prettyPrint();

        response.then().
                assertThat().
                statusCode(200).
                contentType("application/json").
                header("Server", equalTo("cloudflare")).
                body("userId", equalTo(7),
                        "id", equalTo(123),
                        "title", equalTo("esse et quis iste est earum aut impedit"),
                        "completed", equalTo(false));

    }
}
