package get_http_request.day15;

public class Country {

    /*
    country”: {
        “id”: 3,
        “name”: “USA”,
        “states”: null
    }
     */
    // private degiskenler olusturulur
    private int id;
    private String name;
    private String states;

    // getter ve setter olustur

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states;
    }

    // parametreli ve parametresiz constructor olustur


    public Country(int id, String name, String states) {
        this.id = id;
        this.name = name;
        this.states = states;
    }

    public Country(){

    }

    // toString() olustur


    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", states='" + states + '\'' +
                '}';
    }
}
