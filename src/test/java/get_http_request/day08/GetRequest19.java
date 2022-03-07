package get_http_request.day08;

import base_url.DummyBaseUri;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequest19 extends DummyBaseUri {

    /*
http://dummy.restapiexample.com/api/v1/employees
1) Status kodunun 200,
2) 10’dan büyük tüm id'leri ekrana yazdırın ve 10’dan büyük 14 id olduğunu,
3) 30’dan küçük tüm yaşları ekrana yazdırın ve bu yaşların içerisinde en büyük yaşın 23 olduğunu
4) Maası 350000 den büyük olan tüm employee name'leri ekrana yazdırın
     ve bunların içerisinde "Charde Marshall" olduğunu test edin
*/

    @Test
    public void test19(){

        //  URL olustur
        spec02.pathParams("1", "api", "2", "v1", "3", "employees");

        //  REQUEST VE RESPONSE
        // http://dummy.restapiexample.com request onceki adresimiz
        Response response = given().spec(spec02).when().get("/{1}/{2}/{3}");
        // /{1}/{2}/{3} -> /api/v1/employees
        //response.prettyPrint();

        // 1) Status kodunun 200
        Assert.assertEquals(200, response.statusCode());
        response.then().assertThat().statusCode(200);

        // 2) 10’dan büyük tüm id'leri ekrana yazdırın ve 10’dan büyük 14 id olduğunu,
        JsonPath json = response.jsonPath();

        List<Integer> idList = json.getList("data.findAll{it.id>10}.id");
        //List<Integer> idList = json.getList("data.id.findAll{it.id>10}");
        System.out.println("ID List : " + idList);
        // Groovy Java platformu uzerinde calısan bir bilgisayar dilidir.
        //Groovy ile loop kullanmadan response'dan gelen degerlei bir sarta gore alabiliriz

        // 3) 30’dan küçük tüm yaşları ekrana yazdırın ve bu yaşların içerisinde en büyük yaşın 23 olduğunu
        List<Integer> ageList = json.getList("data.findAll{it.employee_age<30}.employee_age");
        System.out.println("Age List : " + ageList);
        // System.out.println("Age List Max : " + ageList.stream().mapToInt(t->t).max().orElse(0));
        // int maxAge= ageList.stream().mapToInt(t->t).max().orElse(0);
        // Assert.assertEquals(maxAge,23);
        Collections.sort(ageList);
        Assert.assertEquals(23, (int) ageList.get(ageList.size()-1));
        Assert.assertEquals((Integer) 23, ageList.get(ageList.size()-1));

        // 4) Maası 350000 den büyük olan tüm employee name'leri ekrana yazdırın
        //     ve bunların içerisinde "Charde Marshall" olduğunu test edin

        List<String> salaryList = json.getList("data.findAll{it.employee_salary>3500}.employee_name");
        System.out.println("Name : " + salaryList);
        Assert.assertTrue(salaryList.contains("Charde Marshall"));

    }



}
