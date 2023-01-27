package model;

import javax.persistence.*;

@Entity
@Table(name = "page")
public class PageDatabase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String string;

}
