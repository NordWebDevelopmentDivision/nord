package is.nord.model;

import javax.persistence.*;
import java.util.Date;

/*
 * Author:
 *       Ólafur Georg Gylfason (ogg4@hi.is)
*/

@Entity
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                // The id of the news item
    private String title;           // The title of the news item

    @Column(columnDefinition = "text")
    private String description;     // The description of the news item
    private String tag;             // The tag of the news item
    private String author;          // The author of the news item
    private Date datePosted;        // The time when the news item was posted

    public News() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(Date datePosted) {
        this.datePosted = datePosted;
    }
}
