package tests._07_GET;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import testBase.JsonPlaceHolderBaseUrl;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequest11 extends JsonPlaceHolderBaseUrl {

    /*
        https://jsonplaceholder.typicode.com/todos/2 url ‘ine istek gönderildiğinde,
        Dönen response un
        Status kodunun 200, dönen body de,
                "completed": değerinin false
                "title”: değerinin “quis ut nam facilis et officia qui” "
                userId" sinin 1 ve
                header değerlerinden
                "Via" değerinin “1.1 vegur”
                ve "Server" değerinin “cloudflare” olduğunu test edin…
     */

    /*
                URL olustur
                -- expectedData
                request gonder
                -- actual Data
                assertion
     */

    @Test
    public void test(){

        spec01.pathParams("parametre1", "todos", "parametre2", 2);

        HashMap<String, Object> expectedData = new HashMap<>();
        expectedData.put("statusCode", 200);
        expectedData.put("Via", "1.1 vegur");
        expectedData.put("Server", "cloudflare");
        expectedData.put("userId", 1);
        expectedData.put("title", "quis ut nam facilis et officia qui");
        expectedData.put("completed", false);

        System.out.println(expectedData);
        System.out.println("------------------------------");


            Response response = given().
                    accept("application/json").
                    spec(spec01).
                    when().
                    get("/{parametre1}/{parametre2}");

            response.prettyPrint();

        // Verify with Matchers Class

        response.then().assertThat().
                statusCode((Integer) expectedData.get("statusCode")).
                body("completed", equalTo(expectedData.get("completed")),
                        "title", equalTo(expectedData.get("title")),
                        "userId", equalTo(expectedData.get("userId"))).
                headers("Via", equalTo(expectedData.get("Via")),
                        "Server", equalTo(expectedData.get("Server")));


        // Verify with JsonPath
        JsonPath json= response.jsonPath();
        Assert.assertEquals( expectedData.get("statusCode"), 200);
        Assert.assertEquals( expectedData.get("Via"), response.getHeader("Via"));
        Assert.assertEquals( expectedData.get("Server"), response.getHeader("Server"));
        Assert.assertEquals(expectedData.get("completed"), json.getBoolean("completed"));
        Assert.assertEquals(expectedData.get("title"), json.getString("title"));
        Assert.assertEquals(expectedData.get("userId"), json.getInt("userId"));

        // Verify with De-Serialization(HashMap)
        //  -- object mapper
        //  -- with pojo class

        HashMap<String, Object> actualData = response.as(HashMap.class);

        Assert.assertEquals( expectedData.get("statusCode"), 200);
        Assert.assertEquals( expectedData.get("Via"), response.getHeader("Via"));
        Assert.assertEquals( expectedData.get("Server"), response.getHeader("Server"));
        Assert.assertEquals(expectedData.get("completed"), actualData.get("completed"));
        Assert.assertEquals(expectedData.get("title"), actualData.get("title"));
        Assert.assertEquals(expectedData.get("userId"), actualData.get("userId"));




        }

    }


