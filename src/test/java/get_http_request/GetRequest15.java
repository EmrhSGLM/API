package get_http_request;

import base_url.GMIBankBasUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequest15 extends GMIBankBasUrl {

    /*
      https://www.gmibank.com/api/tp-customers/85694
        "login": "dino.kohler",
    "firstName": "Winona",
    "lastName": "Abernathy",
    "email": "winonaabernathy@gmail.com"

 */

    @Test
    public void test15(){
        spec03.pathParams("bir", "tp-customers","iki","85694");

        Response response = given().
                spec(spec03).
                header("Authorization", "Bearer " + generateToken()).
                when().
                get("/{bir}/{iki}");

        // /{bir}/{iki} => /tp-customers/85694

        response.prettyPrint();

        // MATCHERS CLASS ILE
        response.then().assertThat().body("firstName", equalTo("Winona"),
                                        "lastName", equalTo("Abernathy"),
                                        "email", equalTo("winonaabernathy@gmail.com"),
                                        "user.login", equalTo("dino.kohler"));

        // Json Path ile
        JsonPath json = response.jsonPath();
        Assert.assertEquals("Winona", json.getString("firstName"));
        Assert.assertEquals("Abernathy", json.getString("lastName"));
        Assert.assertEquals("winonaabernathy@gmail.com", json.getString("email"));
        Assert.assertEquals("dino.kohler", json.getString("user.login"));
    }
}
