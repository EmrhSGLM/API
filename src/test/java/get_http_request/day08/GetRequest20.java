package get_http_request.day08;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequest20 extends JsonPlaceHolderBaseUrl {


     /*
            https://jsonplaceholder.typicode.com/todos/2
            1) Status kodunun 200,
            2) respose body'de,
                     "completed": değerinin false
                     "title": değerinin "quis ut nam facilis et officia qui"
                     "userId" sinin 1 ve
                header değerlerinden
                     "via" değerinin "1.1 vegur" ve
                     "Server" değerinin "cloudflare" olduğunu test edin…
*/

    @Test
    public void test20(){

        //1)URL OLUSTUR
        spec04.pathParams("parametre1", "todos", "parametre2", 2);

        //2) EXPECTED DATA OLUSTUR
        HashMap<String, Object> expectedData = new HashMap<>();
        expectedData.put("statusCode", 200);
        expectedData.put("completed", false);
        expectedData.put("userId", 1);
        expectedData.put("via", "1.1 vegur");
        expectedData.put("title", "quis ut nam facilis et officia qui");
        expectedData.put("server", "cloudflare");

        //3) REQUEST VE RESPONSE
        Response response = given().
                             accept(ContentType.JSON).spec(spec04).
                             when().
                             get("/{parametre1}/{parametre2}");
        response.prettyPrint();

        //4) DOGRULAMA
        //1. WAY MATCHERS CLASS
        response.then().assertThat().statusCode((Integer)expectedData.get("statusCode")).
                                        headers("via", equalTo(expectedData.get("via")),
                                      "server", equalTo(expectedData.get("server"))).
                                        body("completed", equalTo(expectedData.get("completed")),
                                                "userId", equalTo(expectedData.get("userId")),
                                                "title", equalTo(expectedData.get("title")));


        //2.WAY JSONPATH ILE
        JsonPath json=response.jsonPath();
        Assert.assertEquals(expectedData.get("statusCode"), response.statusCode());
        Assert.assertEquals(expectedData.get("via"), response.getHeader("via"));
        Assert.assertEquals(expectedData.get("server"), response.getHeader("server"));
        Assert.assertEquals(json.getBoolean("completed"),expectedData.get("completed"));
        Assert.assertEquals(json.getInt("userId"),expectedData.get("userId"));
        Assert.assertEquals(json.getString("title"),expectedData.get("title"));


        //3.Way De-Serialization
        HashMap<String, Object> actualData = response.as(HashMap.class);
        System.out.println("Actual Data : " + actualData);
        Assert.assertEquals(expectedData.get("completed"), actualData.get("completed"));
        Assert.assertEquals(expectedData.get("userId"), actualData.get("userId"));
        Assert.assertEquals(expectedData.get("title"), actualData.get("title"));


    }


}
