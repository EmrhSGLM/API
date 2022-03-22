package get_http_request.day13;

import base_url.DummyBaseUri;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.DataPojo;
import pojos.DummyPojo;

import static io.restassured.RestAssured.given;

public class GetRequestPojo01 extends DummyBaseUri
{

        /*
                GET Request to the URL http://dummy.restapiexample.com/api/v1/employee/1
                                           Status code is 200
                {
                 "status": "success",
                 "data": {
                   "id": 1,
                   "employee_name": "Tiger Nixon",
                   "employee_salary": 320800,
                   "employee_age": 61,
                   "profile_image": ""
                   },
                 "message": "Successfully! Record has been fetched."
                 }

         */

    @Test
    public void test01(){

        // 1) URL OLUSTUR
        spec02.pathParams("1","api","2", "v1", "3", "employee", "4", "1");

        // 2) EXPECTED DATA OLUSTUR
        DataPojo data = new DataPojo(1, "Tiger Nixon", 320800,
                                61, "");
        System.out.println("data = " + data);

        DummyPojo expectedData = new DummyPojo("success", data , "Successfully! Record has been fetched.");
        System.out.println("expectedData = " + expectedData);

        // 3) RESPONSE AND REQUEST
        Response response = given().
                            contentType(ContentType.JSON).
                            spec(spec02).
                            when().
                            get("/{1}/{2}/{3}/{4}");

        response.prettyPrint();

        // 4) DOGRULAMA

        DummyPojo actual = response.as(DummyPojo.class);
        System.out.println("actual = " + actual);

        Assert.assertEquals(expectedData.getStatus(), actual.getStatus() );
        Assert.assertEquals(expectedData.getMessage(), actual.getMessage() );
        Assert.assertEquals(expectedData.getData().getEmployee_age(), actual.getData().getEmployee_age());
        Assert.assertEquals(expectedData.getData().getEmployee_name(), actual.getData().getEmployee_name());
        Assert.assertEquals(expectedData.getData().getId(), actual.getData().getId());
        Assert.assertEquals(expectedData.getData().getEmployee_salary(), actual.getData().getEmployee_salary());
        Assert.assertEquals(expectedData.getData().getProfile_image(), actual.getData().getProfile_image());


        // Serialization -> Java yapısındaki datayı JSON formatına donusturur
        Gson gson = new Gson();
        String jsonFromJava = gson.toJson(actual);
        System.out.println("jsonFromJava = " + jsonFromJava);
        // System.err.println("jsonFromJava = " + jsonFromJava);
        // Cıktıyı kırmızı yazar Hata mesajlarında kullanılır


    }




}
