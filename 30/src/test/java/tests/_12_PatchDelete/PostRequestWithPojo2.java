package tests._12_PatchDelete;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.BookingResponsePojo;
import testBase.HerOkuAppBaseUrl;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;


public class PostRequestWithPojo2 extends HerOkuAppBaseUrl {

    /*

       https://restful-booker.herokuapp.com/booking
        url’ine aşağıdaki request body gönderildiğinde,
        url’ine aşağıdaki request body gönderildiğinde,
                {
                "firstname": "Selim",
                "lastname": "Ak",
                "totalprice": 15000,
                "depositpaid": true,
                "bookingdates":
                        {
                        "checkin": "2020-09-09",
                        "checkout": "2020-09-21"
                        }
               }
       Status kodun 200 ve dönen response body si
                {
                        "bookingid": 11,
                        "booking":
                             {
                             "firstname": "Selim",
                             "lastname": "Ak",
                             "totalprice": 15000,
                             "depositpaid": true,
                             "bookingdates":
                                     {
                                     "checkin": "2020-09-09",
                                     "checkout": "2020-09-21" }
                                     }
                     }
      olduğunu test edin

     */

    @Test
    public void test(){

        spec02.pathParam("1", "booking");

        BookingDatesPojo bookingDates = new BookingDatesPojo("2020-09-09", "2020-09-21");
        BookingPojo bookingPojo = new BookingPojo("Selim","Ak", 15000, true, bookingDates );

        Response response = given().
                contentType(ContentType.JSON).
                spec(spec02).
                auth().basic("admin", "password123").
                body(bookingPojo).
                when().
                post("/{1}");

        response.prettyPrint();

        BookingResponsePojo actualData = response.as(BookingResponsePojo.class);

        assertEquals(bookingPojo.getFirstname(), actualData.getBooking().getFirstname());
        assertEquals(bookingPojo.getLastname(), actualData.getBooking().getLastname());
        assertEquals(bookingPojo.getTotalprice(), actualData.getBooking().getTotalprice());
        assertEquals(bookingPojo.isDepositpaid(), actualData.getBooking().isDepositpaid());
        assertEquals(bookingPojo.getBookingdates().getCheckin(),
                actualData.getBooking().getBookingdates().getCheckin());
        assertEquals(bookingPojo.getBookingdates().getCheckout(),
                actualData.getBooking().getBookingdates().getCheckout());

    }
}
