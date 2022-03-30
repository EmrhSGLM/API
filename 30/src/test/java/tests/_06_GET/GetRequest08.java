package tests._06_GET;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import testBase.DummyBaseUrl;

import static io.restassured.RestAssured.given;

public class GetRequest08 extends DummyBaseUrl {

    /*
             http://dummy.restapiexample.com/api/v1/employees url’inde bulunan
            1) Butun calisanlarin isimlerini consola yazdıralim
            2) 3. calisan kisinin ismini konsola yazdıralim
            3) Ilk 5 calisanin adini konsola yazdiralim
            4) En son calisanin adini konsola yazdiralim
     */

    @Test
    public void test(){

        spec03.pathParam("parametre1", "employees");

        Response response = given().
                accept("acception/json").
                when().
                spec(spec03).
                get("/{parametre1}");

        //response.prettyPrint();

        JsonPath jsonPath = response.jsonPath();


        //  1) Butun calisanlarin isimlerini consola yazdıralim
        System.out.println("data.employee_name = " + jsonPath.getList("data.employee_name"));

        //  2) 3. calisan kisinin ismini konsola yazdıralim
        System.out.println("3. kişinin ismi => " + jsonPath.getString("data[2].employee_name"));

        //  3) Ilk 5 calisanin adini konsola yazdiralim

        // with Lambda
        jsonPath.getList("data.employee_name").stream().
                limit(5).forEach(t-> System.out.println(t));

        jsonPath.getString("data.employee_name[0,1,2,3,4]");

        //  4) En son calisanin adini konsola yazdiralim
        System.out.println("Last employee" + jsonPath.getString("data.employee_name[-1]"));


        // Json Assert
        Assert.assertEquals("Ashton Cox", jsonPath.getString("data[2].employee_name"));
        Assert.assertEquals("Doris Wilder", jsonPath.getString("data.employee_name[-1]"));


    }
}
