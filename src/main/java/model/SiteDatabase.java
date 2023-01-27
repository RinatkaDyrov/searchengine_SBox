package model;

import javax.persistence.*;

@Entity
@Table(name = "site")
public class SiteDatabase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String string;

}
