package tests._12_PatchDelete;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;
import testBase.DummyBaseUrl;
import testData.DummyTestData;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;

public class DeleteRequest01 extends DummyBaseUrl {


    /*
    http://dummy.restapiexample.com/api/v1/delete/2 bir DELETE request gönderdiğimde
    Dönen response un status kodunun 200 ve body kısmının aşağıdaki gibi olduğunu test edin
        {
            "status": "success",
            "data": "2",
            "message": "Successfully! Record has been deleted"
        }
     */

    @Test
    public void test(){

        // url olustur
        spec03.pathParams("1", "delete", "2", "2");

        // expectedData olustur
        DummyTestData testObje = new DummyTestData();
        JSONObject expectedData = testObje.setupDeleteExpectedData();

        // request olusturulur
        Response response = given().
                contentType(ContentType.JSON).
                spec(spec03).
                auth().
                basic("admin", "password123"). // delete isleminde body kullanılmaz
                when().
                delete("/{1}/{2}");

        //response.prettyPrint();

        // De-Serialization
        HashMap<String, Object> actualData = response.as(HashMap.class);

        assertEquals(expectedData.getString("status"), actualData.get("status"));
        assertEquals(expectedData.getString("data"), actualData.get("data"));
        assertEquals(expectedData.getString("message"), actualData.get("message"));

        // Matchers class
        response.then().assertThat().statusCode(200).
                body("status", equalTo(expectedData.getString("status")),
                        "data", equalTo(expectedData.getString("data")),
                        "message", equalTo(expectedData.getString("message")));

    }

}
