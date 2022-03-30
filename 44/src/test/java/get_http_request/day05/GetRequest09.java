package get_http_request.day05;

import base_url.DummyBaseUri;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequest09 extends DummyBaseUri {

    /*
        http://dummy.restapiexample.com/api/v1/employee/12 URL'E GiT.
        1) Matcher CLASS ile
        2) JsonPath ile dogrulayin.
    */

    @Test
    public void test09(){

        spec02.pathParams("birinci", "api",
                "ikinci", "v1",
                "ucuncu", "employee",
                "dorduncu", "12");

        Response response = given().spec(spec02).when().get("/{birinci}/{ikinci}/{ucuncu}/{dorduncu}");

        response.prettyPrint();
        response.then().statusCode(200).contentType(ContentType.JSON);

        // Matchers Class ile dogrulayin

        response.then().assertThat().body("status", equalTo("success"),
                                    "data.id", equalTo(12),
                                        "data.employee_name", equalTo("Quinn Flynn"),
                                        "data.employee_salary", equalTo(342000),
                                        "data.employee_age", equalTo(22),
                                        "data.profile_image", equalTo(""),
                                        "message", equalTo("Successfully! Record has been fetched."));

        // 2) JsonPath ile dogrulayin.
        JsonPath json = response.jsonPath();
        System.out.println(json.getString("status"));
        System.out.println(json.getInt("data.id"));
        System.out.println(json.getString("data.employee_name"));
        System.out.println(json.getInt("data.employee_salary"));
        System.out.println(json.getInt("data.employee_age"));
        System.out.println(json.getString("data.profile_image"));
        System.out.println(json.getString("message"));

        Assert.assertEquals("success",json.getString("status"));
        Assert.assertEquals(12,json.getInt("data.id"));
        Assert.assertEquals("Quinn Flynn",json.getString("data.employee_name"));
        Assert.assertEquals(342000,json.getInt("data.employee_salary"));
        Assert.assertEquals(22,json.getInt("data.employee_age"));
        Assert.assertEquals("",json.getString("data.profile_image"));
        Assert.assertEquals("Successfully! Record has been fetched.",json.getString("message"));

    }



}
