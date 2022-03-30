package tests._04_GET;

import io.restassured.response.Response;
import org.junit.Test;
import static io.restassured.RestAssured.given;

public class GetRequest01 {

    /*
        GetRequest01:
        https://restful-booker.herokuapp.com/booking/3 adresine bir request gonderildiginde
        donecek cevap(response) icin

        HTTP status kodunun 200
        Content Type’in Json
        Ve Status Line’in HTTP/1.1 200 OK
        Oldugunu test edin.
     */
    @Test
    public void test01() {
        // 1- API testi yapılırken ilk olarak url (EndPoint) belirlenmeli
        String url = "https://restful-booker.herokuapp.com/booking/3";

        // 2- Bekelenen sonuc(expected result) saklanmalı
        // bu test case de benden body dogrulanması istenmedigi icin simdilik beklenen sonuc olusturmadık

        // 3) request gondeririz
        Response response = given().
                //accept("application/json").
                when().
                get(url);
        // accept("application/json") gelen verinin formatının json formatı olarak kabul et yazmayada biliriz

        response.prettyPrint();
        /* OUTPUT
                {
                        "firstname": "Mark",
                        "lastname": "Jones",
                        "totalprice": 176,
                        "depositpaid": true,
                        "bookingdates": {
                            "checkin": "2015-06-13",
                            "checkout": "2018-05-13"
                        }
                }
         */

        // 4) Actual result olusturduk
        // response body ile alakalı islem istenmedigi icin bu test de olustrumaycagız

        // 5) Dogrulama
        System.out.println("StatusCode = " + response.getStatusCode());
        System.out.println("ContentType = " + response.getContentType());
        System.out.println("StatusLine = " + response.getStatusLine());
        System.out.println("getHeaders = " + response.getHeaders());

        /*
        // expected kismi bize task olarak verilen degerdir, actual kismi ise response da donen degerdir
        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals("application/json; charset=utf-8", response.getContentType());
        Assert.assertEquals("HTTP/1.1 200 OK", response.getStatusLine());
        */

        // Verify with response (API daki assert yontemlerinden bir tanesi)

        response.then().
                assertThat().
                statusCode(200).
                //contentType(ContentType.JSON). // 2 ifade de kullanilabilir
                contentType("application/json").
                statusLine("HTTP/1.1 200 OK");
    }

}
