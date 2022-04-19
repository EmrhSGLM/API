package tests._14_;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import testBase.JsonPlaceHolderBaseUrl;
import utilities.JsonUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class GetRequestWithObjectMapper01 extends JsonPlaceHolderBaseUrl {

    /*
        https://jsonplaceholder.typicode.com/todos/198 url’ine bir get request gönderildiğinde,
        Dönen response ‘un status kodunun 200 ve body kısmının
            {
                    "userId": 10,
                    "id": 198,
                    "title": "quis eius est sint explicabo",
                    "completed": true
             }
        Olduğunu Object Mapper kullanarak test edin

     */

    @Test
    public void test() throws IOException {

        // 1- url olusturulur
        spec01.pathParams("1", "todos", "2", 198);

        String jsonData = "{\n" +
                            "\"userId\": 10,\n" +
                            "\"id\": 198,\n" +
                            "\"title\": \"quis eius est sint explicabo\",\n" +
                            "\"completed\": true\n" +
                            "}";
        Map<String, Object> expectedData  = JsonUtil.convertJsonToJava(jsonData, Map.class);
        System.out.println("expectedData = " + expectedData);

        Response response = given().
                contentType(ContentType.JSON).
                spec(spec01).
                when().
                get("/{1}/{2}");

        response.prettyPrint();

        // 1.Way
        Map<String, Object> actualData =  JsonUtil.convertJsonToJava(response.asString(), Map.class);
        System.out.println("actualData = " + actualData);

        // 2. Way
        ObjectMapper obj = new ObjectMapper();

        HashMap<String, Object> actualData1 = obj.readValue(response.asString(), HashMap.class);
        System.out.println("actualData1 = " + actualData1);


        // Verify
        assertEquals(expectedData.get("id"), actualData.get("id"));
        assertEquals(expectedData.get("userId"), actualData.get("userId"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));

    }


}
