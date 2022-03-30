package tests._09_;

import io.restassured.response.Response;
import org.junit.Test;
import testBase.DummyBaseUrl;
import testData.DummyTestData;

import java.util.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetRequest13MatchersClass extends DummyBaseUrl {

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

        spec03.pathParam("1", "employees");

        DummyTestData expectedObje = new DummyTestData();
        HashMap<String, Object> expectedData = expectedObje.setupTestData();

        Response response = given().
                accept("applicaiton/json").
                spec(spec03).
                when().
                get("/{1}");

        //response.prettyPrint();

        // with MatchersClsas

        response.then().assertThat().
                statusCode((Integer) expectedData.get("statusCode")).
                body("data[4].employee_name", equalTo(expectedData.get("besinciCalisan")),
                        "data.id",hasSize((Integer) expectedData.get("calisanSayisi")),
                        "data[-2].employee_salary", equalTo(expectedData.get("sondanIkinciCalisanMaasi")),
                        "data.employee_age", hasItems(((List) expectedData.get("arananAges")).get(0),
                                ((List) expectedData.get("arananAges")).get(1),
                                ((List) expectedData.get("arananAges")).get(2)),
                        "data[10].employee_name", equalTo(((Map)expectedData.get("onBirinciEmployee")).get("employee_name")),
                        "data[10].employee_age", equalTo(((Map)expectedData.get("onBirinciEmployee")).get("employee_age")),
                        "data[10].employee_salary", equalTo(((Map)expectedData.get("onBirinciEmployee")).get("employee_salary")),
                        "data[10].profile_image", equalTo(((Map)expectedData.get("onBirinciEmployee")).get("profile_image")));

    }
}