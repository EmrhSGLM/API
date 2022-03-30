package get_http_request.day07;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequest16 extends JsonPlaceHolderBaseUrl {

    /*
        https://jsonplaceholder.typicode.com/todos/7

        {
            "userId": 1,
            "id": 7,
            "title": "illo expedita consequatur quia in",
            "completed": false
        }
    */

    @Test
    public void test16(){

        // 1) URL OLUSTURMA
        spec04.pathParams("first" , "todos" , "second" , "7");

        // EXPECTED(BEKLENEN) DATA OLUSTUR
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("userId", 1);
        expectedData.put("id", 7);
        expectedData.put("title", "illo expedita consequatur quia in");
        expectedData.put("completed", false);

        System.out.println("expectedData : " + expectedData);
        // expectedData : {id=7, completed=false, title=illo expedita consequatur quia in, userId=1}

        // 3) REQUEST VE RESPONSE
        Response response = given().spec(spec04).when().get("/{first}/{second}");

        // /{first}/{second} => /todos/7

        //response.prettyPrint();

        // DATAYI JSON DAN -> JAVA A DONUSTURME ISLEMINE De-Serialization
        // DATAYI JAVA DAN -> JSON A DONUSTURME ISLEMINE Serialization

        // Java ve Json formatlarını kıyaslayabilmek  (Assert islemi) icin donusturme
        // (De-Serialization/Serialization) islemlerini yapiyoruz
        //Serialization is the process of converting an object into a stream of bytes
        // to store the object or transmit it to memory, a database, or a file.

        Map<String, Object> actualData = response.as(HashMap.class); // De-Serialization


        /*
             De-Serialization asagidaki JSON formatındaki data yi map'e donusturur
        {
            "userId": 1,
            "id": 7,
            "title": "illo expedita consequatur quia in",
            "completed": false
        }
             JSON datasının MAP'E DONUSMUS HALI

         actualData : {id=7, completed=false, title=illo expedita consequatur quia in, userId=1}

         */

        System.out.println("actualData : " + actualData);
        // actualData : {id=7, completed=false, title=illo expedita consequatur quia in, userId=1}

        Assert.assertEquals(expectedData.get("userId"),actualData.get("userId"));
        Assert.assertEquals(expectedData.get("id"),actualData.get("id"));
        Assert.assertEquals(expectedData.get("title"),actualData.get("title"));
        Assert.assertEquals(expectedData.get("completed"),actualData.get("completed"));



    }

}
