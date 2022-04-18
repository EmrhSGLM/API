package testData;

import org.json.JSONObject;

import java.util.HashMap;

public class JsonPlaceHolderTestData {

    public HashMap<String, Object> setupTestData(){
        HashMap<String, Object> expectedData = new HashMap<>();
        expectedData.put("statusCode", 200);
        expectedData.put("Via", "1.1 vegur");
        expectedData.put("Server", "cloudflare");
        expectedData.put("userId", 1);
        expectedData.put("title", "quis ut nam facilis et officia qui");
        expectedData.put("completed", false);

        return expectedData;

    }

    /*
        {   "userId": 55,
            "title": "Tidy your room",
            "completed": false
        }
     */

    public JSONObject setupTestAndRequestData(){

        JSONObject jsonexpectedData = new JSONObject();
        jsonexpectedData.put("userId", 55);
        jsonexpectedData.put("title", "Tidy your room");
        jsonexpectedData.put("completed", false);

        return jsonexpectedData;
    }

    /*
    {
                "userId": 21,
                "title": "Wash the dishes",
                "completed": false
            }
     */

    public JSONObject setupPutData(){
        JSONObject jsonExpectedData = new JSONObject();

        jsonExpectedData.put("userId", 21);
        jsonExpectedData.put("title", "Wash the dishes");
        jsonExpectedData.put("completed", false);

        return jsonExpectedData;
    }

    /*
        {
            "title": "API calismaliyim"
        }
     */

    public JSONObject setupPatchRequestData(){

        JSONObject jsonExpectedData = new JSONObject();
        jsonExpectedData.put("title", "API calismaliyim");
        return jsonExpectedData;

    }

    /*
    {
            "userId": 10,
            "title": "API calismaliyim",
             "completed": true,
             "id": 198
         }
     */

    public JSONObject expectedDataPatchRequest(){
        JSONObject jsonExpectedData = new JSONObject();
        jsonExpectedData.put("userId", 10);
        jsonExpectedData.put("id", 198);
        jsonExpectedData.put("title", "API calismaliyim");
        jsonExpectedData.put("completed", true);
        return jsonExpectedData;
    }

}
