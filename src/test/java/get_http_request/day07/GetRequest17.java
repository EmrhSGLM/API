package get_http_request.day07;

import base_url.GMIBankBasUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequest17 extends GMIBankBasUrl {

    /*
          http://www.gmibank.com/api/tp-customers/114351 adresindeki müşteri bilgilerini doğrulayın

            {
               "firstName": "Della",
               "lastName": "Heaney",
               "email": "ricardo.larkin@yahoo.com",
               "mobilePhoneNumber": "123-456-7893",
            }
    */

    @Test
    public void test17(){

        spec03.pathParams("bir", "tp-customers", "iki", "114351");

        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("firstName", "Della");
        expectedData.put("lastName", "Heaney");
        expectedData.put("email", "ricardo.larkin@yahoo.com");
        expectedData.put("mobilePhoneNumber", "123-456-7893");

        System.out.println("expectedData : " + expectedData);


        Response response = given().
                spec(spec03).
                header("Authorization", "Bearer " + generateToken()).
                when().
                get("/{bir}/{iki}");

        response.prettyPrint();

        Map<String, Object> actualData = response.as(HashMap.class); // De-Serialization
        System.out.println("actualData : " + actualData);

        // MATCHERS CLASS
       response.then().assertThat().body("firstName", equalTo("Della"),
                                               "lastName", equalTo("Heaney"),
                                               "email",equalTo("ricardo.larkin@yahoo.com"),
                                               "mobilePhoneNumber",    equalTo("123-456-7893"));

        //JSONPATH ILE
        JsonPath json = response.jsonPath();
        Assert.assertEquals("Della",json.getString("firstName"));
        Assert.assertEquals("Heaney",json.getString("lastName"));
        Assert.assertEquals("ricardo.larkin@yahoo.com",json.getString("email"));
        Assert.assertEquals("123-456-7893",json.getString("mobilePhoneNumber"));

        // DOGRULAMA
        Assert.assertEquals(expectedData.get("firstName"),actualData.get("firstName"));
        Assert.assertEquals(expectedData.get("lastName"),actualData.get("lastName"));
        Assert.assertEquals(expectedData.get("email"),actualData.get("email"));
        Assert.assertEquals(expectedData.get("mobilePhoneNumber"),actualData.get("mobilePhoneNumber"));

    }


}
