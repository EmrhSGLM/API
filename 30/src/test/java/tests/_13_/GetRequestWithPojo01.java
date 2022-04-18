package tests._13_;

import org.junit.Test;
import testBase.DummyBaseUrl;

public class GetRequestWithPojo01 extends DummyBaseUrl {

    /*

        http://dummy.restapiexample.com/api/v1/employee/1 url ‘ine bir
        get request gönderildiğinde , dönen response ‘un,
        Status kodunun 200 ve response body’nin
        {
        "status": "success",
        "data":
            { "id": 1,
            "employee_name":
            "Tiger Nixon",
            "employee_salary": 320800,
            "employee_age": 61,
            "profile_image": ""
            },
        "message": "Successfully! Record has been fetched." }
        Olduğunu test edin

     */
    @Test
    public void test(){


    }

}
