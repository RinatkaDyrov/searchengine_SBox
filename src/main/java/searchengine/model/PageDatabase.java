package searchengine.model;

import javax.persistence.*;

@Entity
@Table(name = "page", indexes = {@Index(name = "idx_page_path", columnList = "path")})
public class PageDatabase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    int id;

    @Column(name = "site_id", nullable = false)
    int siteId;

    @Column(columnDefinition = "TEXT", nullable = false)
    String path;

    @Column(nullable = false)
    int code;

    @Column(columnDefinition = "MEDIUMTEXT", nullable = false)
    String content;
}
