package searchengine.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Site")
public class SiteDatabase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    int id;


    @Enumerated
    @Column(columnDefinition = "ENUM('INDEXING', 'INDEXED', 'FAILED')", nullable = false)
    SiteStatus status;

    @Column(name = "status_time", columnDefinition = "DATETIME ", nullable = false)
    Date statusTime;

    @Column(name = "last_error", columnDefinition = "TEXT")
    String lastError;

    @Column(columnDefinition = "VARCHAR(255)", nullable = false)
    String url;

    @Column(columnDefinition = "VARCHAR(255)", nullable = false)
    String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public searchengine.model.SiteStatus getStatus() {
        return status;
    }

    public void setStatus(searchengine.model.SiteStatus status) {
        this.status = status;
    }

    public Date getStatusTime() {
        return statusTime;
    }

    public void setStatusTime(Date statusTime) {
        this.statusTime = statusTime;
    }

    public String getLastError() {
        return lastError;
    }

    public void setLastError(String lastError) {
        this.lastError = lastError;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
