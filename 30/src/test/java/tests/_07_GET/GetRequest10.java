package tests._07_GET;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import testBase.DummyBaseUrl;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GetRequest10 extends DummyBaseUrl {

    /*
    http://dummy.restapiexample.com/api/v1/employees
    url ine bir istek gönderildiğinde
    Dönen response un
     Status kodunun 200,
     1)10’dan büyük tüm id’leri ekrana yazdırın ve
     10’dan büyük 14 id olduğunu,
     2)30’dan küçük tüm yaşları ekrana yazdırın ve
     bu yaşların içerisinde en büyük yaşın 23 olduğunu
     3)Maası 350000 den büyük olan tüm employee name’leri ekrana yazdırın ve
     bunların içerisinde “Charde Marshall” olduğunu test edin
     */

    @Test
    public void test(){

        spec03.pathParam("parametre1", "employees");

        Response response = given().
                accept("application/json").
                spec(spec03).
                when().
                get("/{parametre1}");

        JsonPath json = response.jsonPath();

        // Groovy dili java nin alt dilidir. Biz bu dil yardimi ile loop kullanmadan
        // gelen response daki degerleri bir şarta bagli olarak listeye yazdirabiliriz

        // Status kodunun 200
        Assert.assertEquals(200, response.getStatusCode());

        // 1)10’dan büyük tüm id’leri ekrana yazdırın ve
        //        10’dan büyük 14 id olduğunu

        // 1.Way
        List<Integer> idList = json.getList("data.findAll{it.id>10}.id");
        System.out.println(idList);
        Assert.assertEquals(14, idList.size());

        // 2.Way
        List<Integer> idList2 = json.getList("data.id");

        idList2.stream().
                filter(t -> t > 10).
                forEach(t -> System.out.print(t + " "));
        System.out.println();
        System.out.println("10'da buyuk id sayısı =>" + idList2.stream().
                                                        filter(t -> t > 10).
                                                        count());

        // 2)30’dan küçük tüm yaşları ekrana yazdırın ve
        //     bu yaşların içerisinde en büyük yaşın 23 olduğunu

        // 1.Way
        List<Integer> ageList = json.getList("data.findAll{it.employee_age<30}.employee_age");
        System.out.println(ageList);

        Collections.sort(ageList);
        Assert.assertEquals(23, (int) ageList.get(ageList.size()-1));


        // 2.Way
        List<Integer> ageList2 = json.getList("data.employee_age");
        ageList2.stream().
                filter(t -> t < 30 ).
                forEach(t -> System.out.print(t+" "));

        //3)Maası 350000 den büyük olan tüm employee name’leri ekrana yazdırın ve
        //     bunların içerisinde “Charde Marshall” olduğunu test edin

        List<String> employeesNameList = json.getList("data.findAll{it.employee_salary > 3500}.employee_name");
        Assert.assertTrue(employeesNameList.contains("Charde Marshall"));






    }
}
