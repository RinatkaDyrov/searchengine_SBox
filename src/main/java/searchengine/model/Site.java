package searchengine.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Site {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    int id;

    @Enumerated
    @Column(columnDefinition = "enum")
    SiteStatus status;

    @Column(name = "status_time")
    Date statusTime;

    @Column(name = "last_error")
    String lastError;


    String url;

    String name;
}
