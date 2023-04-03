package searchengine.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Page")
public class PageDatabase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    @Getter
    @Setter
    int id;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "site_id")
    private SiteDatabase site;

    @Column(columnDefinition = "TEXT NOT NULL, UNIQUE KEY idx_page_path(path(512), site_id)")
    @Getter
    @Setter
    String path;

    @Column(nullable = false)
    @Getter
    @Setter
    int code;

    @Column(columnDefinition = "MEDIUMTEXT", nullable = false)
    @Getter
    @Setter
    String content;
}
