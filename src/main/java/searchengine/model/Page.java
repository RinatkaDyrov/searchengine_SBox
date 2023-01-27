package searchengine.model;

import javax.persistence.*;

@Entity
//@Table(name = "page")
public class Page {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String string;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
}
