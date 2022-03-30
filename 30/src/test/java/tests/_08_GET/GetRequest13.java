package tests._08_GET;

import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import testBase.DummyBaseUrl;
import testData.DummyTestData;

import java.util.*;

import static io.restassured.RestAssured.given;

public class GetRequest13 extends DummyBaseUrl {

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
    public void test(){

        spec03.pathParam("parametre1", "employees");

        DummyTestData expectedObje = new DummyTestData();
        HashMap<String, Object> expectedData = expectedObje.setupTestData();

        Response response = given().
                accept("applicaiton/json").
                spec(spec03).
                when().
                get("/{parametre1}");

        // De-Seerialization
        HashMap<String, Object> actualData = response.as(HashMap.class);

        // 5. Çalışan isminin "Airi Satou" olduğunu
        Assert.assertEquals(expectedData.get("besinciCalisan"),
                ((Map)((List)actualData.get("data")).get(4)).get("employee_name"));

        // çalışan sayısının 24 olduğunu
        Assert.assertEquals(expectedData.get("calisanSayisi"),
                ((List)actualData.get("data")).size());

        // Sondan 2. çalışanın maaşının 106450 olduğunu
        Assert.assertEquals(expectedData.get("sondanIkinciCalisanMaasi"),
                ((Map)((List)actualData.get("data")).get(((List)actualData.get("data")).size()-2)).get("employee_salary"));

        // 40,21 ve 19 yaslarında çalışanlar olup olmadığını
        List<Integer> actualYasListesi = new ArrayList<>();

        for(int i=0 ; i < ((List)actualData.get("data")).size() ; i++ ){
            actualYasListesi.add((Integer) ((Map)((List)actualData.get("data")).get(i)).get("employee_age"));
        }
        Assert.assertTrue(actualYasListesi.containsAll((List) expectedData.get("arananAges")));

        // 11. Çalışan bilgileri

        Assert.assertEquals(((Map)expectedData.get("onBirinciEmployee")).get("id"),
                ((Map)((List)actualData.get("data")).get(10)).get("id"));

        Assert.assertEquals(((Map)expectedData.get("onBirinciEmployee")).get("employee_name"),
                ((Map)((List)actualData.get("data")).get(10)).get("employee_name"));

        Assert.assertEquals(((Map)expectedData.get("onBirinciEmployee")).get("employee_salary"),
                ((Map)((List)actualData.get("data")).get(10)).get("employee_salary"));

        Assert.assertEquals(((Map)expectedData.get("onBirinciEmployee")).get("employee_age"),
                ((Map)((List)actualData.get("data")).get(10)).get("employee_age"));

        Assert.assertEquals(((Map)expectedData.get("onBirinciEmployee")).get("profile_image"),
                ((Map)((List)actualData.get("data")).get(10)).get("profile_image"));


    }

}
