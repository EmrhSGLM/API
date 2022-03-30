package pojos;

public class JsonPlaceHolderPojo {

     /*
           https://jsonplaceholder.typicode.com/todos url 'ine bir request gönderildiğinde

        {
                "userId": 21,
                "id": 201,
                "title": "Tidy your room",
                "completed": false
        }
        */

    // 1) Degiskenleri private olarak tanımlayacagız.

    private int userId;
    private int id;
    private String title;
    private Boolean completed;

    // 2) Degiskenlerin degerlendine ulasmak icin getter ve setter olustururuz

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    // 3) Parametreli ve parametresiz Constructor olusturacagız

    // Parametresiz Constructor
    public JsonPlaceHolderPojo() {

    }

    // Parametreli Constructor
    public JsonPlaceHolderPojo(int userId, int id, String title, Boolean completed) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    // 4) toString() methodu olustur

    @Override
    public String toString() {
        return "JsonPlaceHolderPojo{" +
                "userId=" + userId +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }

}
