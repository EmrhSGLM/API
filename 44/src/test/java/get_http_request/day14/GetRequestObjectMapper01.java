package get_http_request.day14;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utilities.JsonUtil;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequestObjectMapper01 extends JsonPlaceHolderBaseUrl{

    /*
    https://jsonplaceholder.typicode.com/todos/198 url’ine bir get request gönderildiğinde,
            Dönen response ’un status kodunun 200 ve body kısmının
             {
                    “userId”: 10,
                    “id”: 198,
                    “title”: “quis eius est sint explicabo”,
                    “completed”: true
             }
            Olduğunu Object Mapper kullanarak test edin
     */


    @Test
    public void test(){

        // 1) URL OLUSTUR
        spec04.pathParams("1", "todos", "2", "198");

        // 2) Expected data
        String jsonData=" {\n" +
                "     \"userId\": 10,\n" +
                "     \"id\": 198,\n" +
                "     \"title\": \"quis eius est sint explicabo\",\n" +
                "     \"completed\": true\n" +
                "     }";


        Map<String, Object> expectedData = JsonUtil.convertJsonToJava(jsonData, Map.class);

        System.err.println("jsonData = " + jsonData);
        System.out.println("expectedData = " + expectedData);

        // 3) REQUEST VE RESPONSE
        Response response = given().
                            contentType(ContentType.JSON).
                            spec(spec04).
                             when().
                             get("/{1}/{2}");

        response.prettyPrint();

        // 4) Dogrulama
        // De-Serialization
        // Onceki derslede ogrendigimiz
        HashMap<String, Object> actualData2 = response.as(HashMap.class);
        //System.out.println("actualData2 = " + actualData2);

        // JsonUtill tesuable classını kullanarak de-serialization yaptık
        HashMap<String, Object> actualData = JsonUtil.convertJsonToJava(response.asString() , HashMap.class);
        System.out.println("actualData = " + actualData);

        Assert.assertEquals(200,response.getStatusCode());
        Assert.assertEquals(actualData.get("userId"),expectedData.get("userId"));
        Assert.assertEquals(actualData.get("id"),expectedData.get("id"));
        Assert.assertEquals(actualData.get("title"),expectedData.get("title"));
        Assert.assertEquals(actualData.get("completed"),expectedData.get("completed"));

        // De-Serialization 2.Way
        Map<String,Object> actualDataErdi = response.as(LinkedHashMap.class);
        System.out.println(actualData);
        Assert.assertEquals(expectedData,actualDataErdi);

        //Macther ile
        response.then().
                assertThat().
                statusCode(200).
                body("userId",equalTo(expectedData.get("userId"))
                        ,"id",equalTo(expectedData.get("id"))
                        ,"title",equalTo(expectedData.get("title"))
                        ,"completed",equalTo(expectedData.get("completed")));

        //JsonPath ile
        JsonPath json=response.jsonPath();
        Assert.assertEquals(200,response.statusCode());
        Assert.assertEquals(expectedData.get("userId"),json.getInt("userId"));
        Assert.assertEquals(expectedData.get("id"),json.getInt("id"));
        Assert.assertEquals(expectedData.get("title"),json.getString("title"));
        Assert.assertEquals(expectedData.get("completed"),json.getBoolean("completed"));


    }



}
