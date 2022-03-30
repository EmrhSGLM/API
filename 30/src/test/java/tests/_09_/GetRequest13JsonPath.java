package tests._09_;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import testBase.DummyBaseUrl;
import testData.DummyTestData;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequest13JsonPath extends DummyBaseUrl {


    /*
         http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde

        Status kodun 200 olduğunu,

        5. Çalışan isminin "Airi Satou" olduğunu ,
        çalışan sayısının 24 olduğunu,
        Sondan 2. çalışanın maaşının 106450 olduğunu
        40,21 ve 19 yaslarında çalışanlar olup olmadığını
        11. Çalışan bilgilerinin   {
                             “id”:”11”
                             "employee_name": "Jena Gaines",
                             "employee_salary": "90560",
                             "employee_age": "30",
                             "profile_image": ""
                                    }
                    } gibi olduğunu test edin.
     */

    @Test
    public void test() {

        spec03.pathParam("parametre1", "employees");

        DummyTestData expectedObje = new DummyTestData();
        HashMap<String, Object> expectedData = expectedObje.setupTestData();

        Response response = given().
                accept("applicaiton/json").
                spec(spec03).
                when().
                get("/{parametre1}");

        // with JsonPath
        JsonPath json = response.jsonPath();

        // 5. Çalışan isminin "Airi Satou" olduğunu
        Assert.assertEquals(expectedData.get("besinciCalisan"),
                (((Map) ((List) json.get("data")).get(4)).get("employee_name")));

        // çalışan sayısının 24 oldugunu
        Assert.assertEquals(expectedData.get("calisanSayisi"), ((List) json.get("data")).size());

        // Sondan 2. çalışanın maaşının 106450 olduğunu
        int size = ((List) json.get("data")).size();
        Assert.assertEquals(expectedData.get("sondanIkinciCalisanMaasi"),
                ((Map) ((List) json.get("data")).get(size-2)).get("employee_salary"));

        // 40,21 ve 19 yaslarında çalışanlar olup olmadığını
        List<Integer> yasListesi =  json.get("data.employee_age");
        Assert.assertTrue(yasListesi.containsAll((Collection) expectedData.get("arananAges")));

        // 11. Çalışan bilgilerinin

        Assert.assertEquals(((Map) expectedData.get("onBirinciEmployee")).get("id"),
                ((Map) ((List) json.get("data")).get(10)).get("id"));

        Assert.assertEquals(((Map) expectedData.get("onBirinciEmployee")).get("employee_name"),
                ((Map) ((List) json.get("data")).get(10)).get("employee_name"));

        Assert.assertEquals(((Map) expectedData.get("onBirinciEmployee")).get("employee_salary"),
                ((Map) ((List) json.get("data")).get(10)).get("employee_salary"));

        Assert.assertEquals(((Map) expectedData.get("onBirinciEmployee")).get("employee_age"),
                ((Map) ((List) json.get("data")).get(10)).get("employee_age"));

        Assert.assertEquals(((Map) expectedData.get("onBirinciEmployee")).get("profile_image"),
                ((Map) ((List) json.get("data")).get(10)).get("profile_image"));



    }
}
