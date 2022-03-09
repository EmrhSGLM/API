package get_http_request.day10;

import base_url.DummyBaseUri;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import test_data.DummyTestData;

import java.util.*;

import static io.restassured.RestAssured.given;

public class GetRequest23 extends DummyBaseUri {

    /*
            http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
            Status kodun 200 olduğunu,
            14. Çalışan isminin "Haley Kennedy" olduğunu,
            Çalışan sayısının 24 olduğunu,
            Sondan 3. çalışanın maaşının 675000 olduğunu
            40,21 ve 19 yaslarında çalışanlar olup olmadığını
            10. Çalışan bilgilerinin bilgilerinin aşağıdaki gibi
            {
                    "id": 10,
                    "employee_name": "Sonya Frost",
                    "employee_salary": 103600,
                    "employee_age": 23,
                    "profile_image": ""
             }

              olduğunu test edin.
      */

    @Test
    public void test23(){

        // 1) URL OLUSTUR
        spec02.pathParams("1", "api", "2", "v1", "3", "employees");

        // 2) EXPECTED DATA OLUSTUR
        DummyTestData expectedObje = new DummyTestData();
        HashMap<String, Object> expectedTestDataMap = expectedObje.setUpTestData();
        System.out.println("EXPECT TEST DATA : " + expectedTestDataMap);

        //EXPECT TEST DATA : {arananYaslar=[40, 21, 19],
        //                      sondanUcuncuCalisaninMaasi=675000,
        //                      calisanSayisi=24,
        //                      statusCode=200,
        //                      ondorduncuCalisan=Haley Kennedy,
        //                      onuncuCalisan={
        //                                      profile_image=,
        //                                      employee_name=Sonya Frost,
        //                                      employee_salary=103600,
        //                                      id=10,
        //                                      employee_age=23}}

        // 3) REQUEST VE RESPONSE OLUSTUR
        Response response = given().spec(spec02).when().get("/{1}/{2}/{3}");
        //response.prettyPrint();

        // 4) DOGRULAMA
        // 1.WAY De-Serialization
        HashMap<String, Object> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);

        //Status kodun 200 olduğunu,
        Assert.assertEquals(expectedTestDataMap.get("statusCode"), response.statusCode());

        //14. Çalışan isminin "Haley Kennedy" olduğunu,
        Assert.assertEquals(expectedTestDataMap.get("ondorduncuCalisan"),
                ((Map)((List)actualData.get("data")).get(13)).get("employee_name"));

        //Çalışan sayısının 24 olduğunu,
        Assert.assertEquals(expectedTestDataMap.get("calisanSayisi"), ((List)actualData.get("data")).size());

        //Sondan 3. çalışanın maaşının 675000 olduğunu
        // 1.YOL
        Assert.assertEquals(expectedTestDataMap.get("sondanUcuncuCalisaninMaasi"),
                ((Map)((List)actualData.get("data")).get(((List)actualData.get("data")).size()-3)).get("employee_salary"));

        //2.YOL
        //int dataSize=((List)actualDataMap.get("data")).size();
        //Assert.assertEquals(expectedDataMap.get("sondanUcuncuCalisaninMaasi"),
        //        ((Map) ((List)actualDataMap.get("data")).get(dataSize-3)).get("employee_salary"));


        //40,21 ve 19 yaslarında çalışanlar olup olmadığını

        // 1. Yol
        List<Integer> employee_age = new ArrayList<>();
        for(int i=0 ; i < ((List)actualData.get("data")).size() ; i++){
            employee_age.add((Integer) ((Map)((List)actualData.get("data")).get(i)).get("employee_age"));
        }
        Assert.assertTrue(employee_age.containsAll((Collection<?>) expectedTestDataMap.get("arananYaslar")));

        // 2. Yol
        // List<Integer> yas=new ArrayList<>();
        //for (int i = 0; i <dataSize ; i++) {
        //    yas.add((Integer) ((Map) ((List)actualDataMap.get("data")).get(i)).get("employee_age"));
        //}
        //Assert.assertTrue(yas.containsAll((Collection<?>) expectedDataMap.get("yaslar")));






    }




}
