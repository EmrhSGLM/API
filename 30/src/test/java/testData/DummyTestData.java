package testData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class DummyTestData {

    /*

           5. Çalışan isminin "Airi Satou" olduğunu ,
           çalışan sayısının 24 olduğunu,
            Sondan 2. çalışanın maaşının 106450 olduğunu ve
            40,21 ve 19 yaslarında çalışanlar olup olmadığını
            11. Çalışan bilgilerinin   {
                                    “id”:”11”
                                    "employee_name": "Jena Gaines",
                                    "employee_salary": "90560",
                                    "employee_age": "30",
                                    "profile_image": ""
                                           }
                    }
     */

    public HashMap<String, Object> setupTestData(){
        List<Integer> ages = new ArrayList<>(Arrays.asList(40, 21, 19));

        HashMap<String, Object> onBirinciEmployee = new HashMap<>();
        onBirinciEmployee.put("id", 11);
        onBirinciEmployee.put("employee_name", "Jena Gaines");
        onBirinciEmployee.put("employee_salary", 90560);
        onBirinciEmployee.put("employee_age", 30);
        onBirinciEmployee.put("profile_image", "");

        HashMap<String, Object> expectedData = new HashMap<>();
        expectedData.put("statusCode", 200);
        expectedData.put("besinciCalisan", "Airi Satou");
        expectedData.put("calisanSayisi", 24);
        expectedData.put("sondanIkinciCalisanMaasi", 106450);
        expectedData.put("arananAges", ages);
        expectedData.put("onBirinciEmployee", onBirinciEmployee);

        return expectedData;

    }

    public HashMap<String, Object> setupTestData2(){
        /*
            Status kodun 200 olduğunu,
            En yüksek maaşın 725000 olduğunu,
            En küçük yaşın 19 olduğunu,
            İkinci en yüksek maaşın 675000
         */

        HashMap<String, Object> expectedData = new HashMap<>();
        expectedData.put("statusCode", 200);
        expectedData.put("enYuksekMaas", 725000);
        expectedData.put("enKucukYas", 19);
        expectedData.put("enYuksekIkıncıMaas", 675000);

        return expectedData;

    }


}
