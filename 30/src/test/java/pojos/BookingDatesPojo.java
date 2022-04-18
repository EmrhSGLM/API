package pojos;

public class BookingDatesPojo {

    /*
    "bookingdates":
                                     {
                                     "checkin": "2020-09-09",
                                     "checkout": "2020-09-21" }
     */

    // 1- private degiskenler olustur
    private String checkin;
    private String checkout;

    // 2- getter setter


    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    // 3- Constructor olustur


    public BookingDatesPojo() {
    }

    public BookingDatesPojo(String checkin, String checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }

    // 4- toString() methodu olustur


    @Override
    public String toString() {
        return "BookingDatesPojo{" +
                "checkin='" + checkin + '\'' +
                ", checkout='" + checkout + '\'' +
                '}';
    }
}
