package get_http_request.day12;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;

import static io.restassured.RestAssured.given;

public class PostRequest01 extends JsonPlaceHolderBaseUrl {

       /*
           https://jsonplaceholder.typicode.com/todos url 'ine bir request gönderildiğinde
        Request body{
                "userId": 21,
                "id": 201,
                "title": "Tidy your room",
                "completed": false
        }
        Status kodun 201, response body 'nin ise

        {
                "userId": 21,
                "id": 201,
                "title": "Tidy your room",
                "completed": false
        }
        */

    @Test
    public void test01() {

        // 1) URL OLUSTUR
        spec04.pathParams("1", "todos");

        // 2) EXPECTED DATA OLUSTUR
        JsonPlaceHolderPojo expectedData = new JsonPlaceHolderPojo(21, 201,
                                                        "Tidy your room", false );
        System.out.println("expectedData = " + expectedData);

        // 3) REQUEST AND RESPONSE
        Response response = given().
                contentType(ContentType.JSON).
                spec(spec04).
                body(expectedData).
                when().
                post("/{1}");

        response.prettyPrint();

        // 4) Dogrulama
        // De - Serialization

        JsonPlaceHolderPojo actualData = response.as(JsonPlaceHolderPojo.class);
        Assert.assertEquals(201, response.getStatusCode());
        Assert.assertEquals(expectedData.getId(), actualData.getId());
        Assert.assertEquals(expectedData.getUserId(), actualData.getUserId());
        Assert.assertEquals(expectedData.getCompleted(), actualData.getCompleted());
        Assert.assertEquals(expectedData.getTitle(), actualData.getTitle());

    }



}
