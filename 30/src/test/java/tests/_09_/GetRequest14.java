package tests._09_;

import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import testBase.DummyBaseUrl;
import testData.DummyTestData;

import java.util.*;

import static io.restassured.RestAssured.given;

public class GetRequest14 extends DummyBaseUrl {

    /*
            http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
            Status kodun 200 olduğunu,
            En yüksek maaşın 725000 olduğunu,
            En küçük yaşın 19 olduğunu,
            İkinci en yüksek maaşın 675000
            olduğunu test edin.
     */

    @Test
    public void test(){

        spec03.pathParam("parametre1", "employees");

        DummyTestData expectedObje = new DummyTestData();
        HashMap<String, Object> expectedData = expectedObje.setupTestData2();

        Response response = given().
                accept("application/json").
                spec(spec03).
                when().
                get("/{parametre1}");

        // De-Serialization
        HashMap<String, Object> actualData = response.as(HashMap.class);

        Assert.assertEquals(expectedData.get("statusCode"), response.getStatusCode());

        // En yüksek maaşın 725000 olduğu
        List<Integer> salary = new ArrayList<>();

        for(int i=0 ; i < ((List) actualData.get("data")).size(); i++) {
            salary.add( (Integer) ((Map) ((List) actualData.get("data")).get(i)).get("employee_salary"));
        }
        Collections.sort(salary);

        Assert.assertEquals(expectedData.get("enYuksekMaas"), salary.get(salary.size() - 1));

        // İkinci en yüksek maaşın 675000

        Assert.assertEquals(expectedData.get("enYuksekIkıncıMaas"), salary.get(salary.size() - 2));

        // En küçük yaşın 19 olduğunu
        List<Integer> ages = new ArrayList<>();

        for(int i=0 ; i < ((List) actualData.get("data")).size(); i++) {
            ages.add( (Integer) ((Map) ((List) actualData.get("data")).get(i)).get("employee_age"));
        }
        Collections.sort(ages);

        Assert.assertEquals(expectedData.get("enKucukYas"), ages.get(0));
    }
}
