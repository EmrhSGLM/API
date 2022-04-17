package testData;

import org.json.JSONObject;

import java.util.HashMap;

public class HerOkuappTestData {

    /*
             {
                     "firstname": "Sally",
                      "lastname": "Wilson",
                      "totalprice": 627,
                      "depositpaid": true,
                      "bookingdates": {
                          "checkin": "2015-02-05",
                          "checkout": "2016-01-17"
                      },
                      "additionalneeds": "Breakfast"
                }
     */

    public HashMap<String, Object> setupTestData(){
        HashMap<String, Object> bookingdates = new HashMap<>();
        bookingdates.put("checkin", "2015-02-05");
        bookingdates.put("checkout", "2016-01-17");

        HashMap<String, Object> expectedData = new HashMap<>();
        expectedData.put("firstname", "Sally");
        expectedData.put("lastname", "Wilson");
        expectedData.put("totalprice", 627);
        expectedData.put("depositpaid", true);
        expectedData.put("bookingdates", bookingdates);

        return expectedData;
    }

    public  JSONObject setupTestAndRequestData(){

        JSONObject bookingdates = new JSONObject();
        bookingdates.put("checkin", "2023-02-05");
        bookingdates.put("checkout", "2024-01-17");

        JSONObject expectedRequest = new JSONObject();
        expectedRequest.put("firstname", "Murtaza");
        expectedRequest.put("lastname", "Harbi");
        expectedRequest.put("totalprice", 627);
        expectedRequest.put("depositpaid", true);
        expectedRequest.put("bookingdates", bookingdates);

        return expectedRequest;


    }
}
