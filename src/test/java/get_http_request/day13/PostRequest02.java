package get_http_request.day13;

import base_url.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.BookingResponsePojo;

import static io.restassured.RestAssured.given;

public class PostRequest02 extends HerOkuAppBaseUrl {

    /*
 https://restful-booker.herokuapp.com/booking
 request body
         {  "firstname": "Ali",
            "lastname": "Can",
            "totalprice": 500,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2022-03-01",
                "checkout": "2022-03-11"
             }
 }
Status code is 200
 response body
 {
    "bookingid": 11,
       "booking": {
                "firstname": "Ali",
                "lastname": "Can",
                "totalprice": 500,
                "depositpaid": true,
                "bookingdates": {
                   "checkin": "2022-03-01",
                   "checkout": "2022-03-11"
                                    }
                   }
  }
  */

    @Test
    public void test02(){

        // 1) URL OLUSTUR
        spec05.pathParams("1", "booking");

        // 2) EXPECTED DATA OLUSTUR
        BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2022-03-01", "2022-03-11");
        System.out.println("bookingDatesPojo = " + bookingDatesPojo);
        BookingPojo bookingPojo = new BookingPojo("Ali", "Can", 500, true, bookingDatesPojo);
        System.out.println("bookingPojo = " + bookingPojo);

        // 3) RESPONSE AND REQUSET
        Response response = given().
                            contentType(ContentType.JSON).
                            spec(spec05).
                            auth().
                            basic("admin", "password123").
                            body(bookingPojo).
                            when().
                            post("/{1}");


        //response.prettyPrint();
        response.prettyPeek();


        // 4) DOGRULAMA
        BookingResponsePojo actualData = response.as(BookingResponsePojo.class);
        System.out.println("actualData = " + actualData);

        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals(bookingPojo.getFirstname(), actualData.getBookingPojo().getFirstname());
        Assert.assertEquals(bookingPojo.getLastname(), actualData.getBookingPojo().getLastname());
        Assert.assertEquals(bookingPojo.getDepositpaid(), actualData.getBookingPojo().getDepositpaid());
        Assert.assertEquals(bookingPojo.getTotalprice(), actualData.getBookingPojo().getTotalprice());
        Assert.assertEquals(bookingPojo.getBookingDatesPojo().getCheckin(),
                                              actualData.getBookingPojo().getBookingDatesPojo().getCheckin());
        Assert.assertEquals(bookingPojo.getBookingDatesPojo().getCheckout(),
                                               actualData.getBookingPojo().getBookingDatesPojo().getCheckout());

    }






}
