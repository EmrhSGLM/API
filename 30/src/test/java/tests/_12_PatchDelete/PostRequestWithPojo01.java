package tests._12_PatchDelete;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.TodosPojo;
import testBase.JsonPlaceHolderBaseUrl;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class PostRequestWithPojo01 extends JsonPlaceHolderBaseUrl {

    /*
        https://jsonplaceholder.typicode.com/todos url ‘ine bir request gönderildiğinde
        Request body
                {
                    "userId": 21,
                    "id": 201,
                    "title": "Tidy your room",
                    "completed": false
                }
         Status kodun 201, response body ‘nin ise
                {
                    "userId": 21,
                    "id": 201,
                    "title": "Tidy your room",
                    "completed": false
                }
        olduğunu test edin.
     */


    @Test
    public void test(){
        spec01.pathParam("1", "todos");

        TodosPojo requestExpected = new TodosPojo(21,201,"Tidy your room",false);
        System.out.println(requestExpected);

        // request gonderilir ve response alınır
        Response response = given().
                contentType(ContentType.JSON).
                spec(spec01).
                auth().basic("admin", "password123").
                body(requestExpected).
                when().
                post("/{1}");

        response.prettyPrint();

        // De Serialization

        TodosPojo actualData = response.as(TodosPojo.class);

        assertEquals(201, response.getStatusCode());
        assertEquals(requestExpected.getUserId(), actualData.getUserId());
        assertEquals(requestExpected.getId(), actualData.getId());
        assertEquals(requestExpected.getTitle(), actualData.getTitle());
        assertEquals(requestExpected.isCompleted(), actualData.isCompleted());

    }

}
