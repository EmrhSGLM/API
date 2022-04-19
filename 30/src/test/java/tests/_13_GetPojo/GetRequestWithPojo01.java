package tests._13_GetPojo;

import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojosYeni.Data;
import pojosYeni.DummyPojo;
import testBase.DummyBaseUrl;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class GetRequestWithPojo01 extends DummyBaseUrl {

    /*

        http://dummy.restapiexample.com/api/v1/employee/2 url ‘ine bir
        get request gönderildiğinde , dönen response ‘un,
        Status kodunun 200 ve response body’nin
        {"status": "success",
        "data":
            { "id": 2,
            "employee_name":"Garrett Winters",
            "employee_salary": 170750,
            "employee_age": 63,
            "profile_image": ""
            },
        "message": "Successfully! Record has been fetched." }
        Olduğunu test edin

     */
    @Test
    public void test(){

        // url olusturulur
        spec03.pathParams("parametre1", "employee", "parametre2", 2);

        // expectedData olusturulur
        Data data = new Data(2, "Garrett Winters", 170750, 63, "");
        DummyPojo dummyExpectedData = new DummyPojo("success", data, "Successfully! Record has been fetched.");

        Response response = given().
                contentType(ContentType.JSON).
                spec(spec03).
                when().
                get("/{parametre1}/{parametre2}");
        //response.prettyPrint();

        // actual Data olustur

        DummyPojo actualData = response.as(DummyPojo.class);

        assertEquals(dummyExpectedData.getStatus(), actualData.getStatus());
        assertEquals(dummyExpectedData.getMessage(), actualData.getMessage());
        assertEquals(dummyExpectedData.getData().getId(), actualData.getData().getId());
        assertEquals(dummyExpectedData.getData().getEmployee_name(), actualData.getData().getEmployee_name());
        assertEquals(dummyExpectedData.getData().getEmployee_age(), actualData.getData().getEmployee_age());
        assertEquals(dummyExpectedData.getData().getEmployee_salary(), actualData.getData().getEmployee_salary());
        assertEquals(dummyExpectedData.getData().getProfile_image(), actualData.getData().getProfile_image());

        // Serialization Java yapısında olan data ları Json formatına donusturme islemi dir
        // Gson sınıfından bir obje uretilir

        Gson gson = new Gson();
        String jsonFromJava = gson.toJson(actualData);
        System.out.println("jsonFromJava = " + jsonFromJava);

    }

}
