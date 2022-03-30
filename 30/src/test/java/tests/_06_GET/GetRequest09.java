package tests._06_GET;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import testBase.DummyBaseUrl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GetRequest09 extends DummyBaseUrl {

    /*
    http://dummy.restapiexample.com/api/v1/employees
     url ine bir istek gönderildiğinde,
     status kodun 200,
     gelen body de,
     5. çalışanın isminin "Airi Satou" olduğunu ,
     6. çalışanın maaşının "372000" olduğunu ,
     Toplam 24 tane çalışan olduğunu,
     "Rhona Davidson" ın employee lerden biri olduğunu
     "21", "23", "61" yaşlarında employeeler olduğunu test edin
     */

    @Test
    public void test(){

        spec03.pathParam("parametre1", "employees");

        Response response = given().
                accept("application/json").
                when().
                spec(spec03).
                get("/{parametre1}");

        //response.prettyPrint();

        JsonPath jsonPath = response.jsonPath();
        List<Integer> expectedList = new ArrayList<>(Arrays.asList(21, 23, 61));

        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals("Airi Satou", jsonPath.getString("data[4].employee_name"));
        Assert.assertEquals(372000, jsonPath.getInt("data[5].employee_salary"));
        Assert.assertEquals(24, jsonPath.getList("data.id").size());
        Assert.assertTrue(jsonPath.getString("data.employee_name").contains("Rhona Davidson"));
        Assert.assertTrue(jsonPath.getList("data.employee_age").containsAll(expectedList));

    }
}
