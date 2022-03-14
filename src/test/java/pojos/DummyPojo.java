package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DummyPojo {

    /*

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

    // 1) private degiskenler olustur
    private String status;
    private DataPojo dataPojo;
    private String message;

    // 2) getter and setter

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataPojo getDataPojo() {
        return dataPojo;
    }

    public void setDataPojo(DataPojo dataPojo) {
        this.dataPojo = dataPojo;
    }

    // 3) parametreli ve parametresiz constructor olustur


    public DummyPojo() {
    }

    public DummyPojo(String status, DataPojo dataPojo , String message) {
        this.status = status;
        this.message = message;
        this.dataPojo = dataPojo;
    }

    // 4) toString();

    @Override
    public String toString() {
        return "DummyPojo{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", dataPojo=" + dataPojo +
                '}';
    }
}
