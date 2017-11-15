package is.nord.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Author Ólafur Georg Gylfason (ogg4@hi.is)
 */
@Entity
@Table(name="news")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                // The id of the news item

    @NotEmpty(message="{news.title.notEmpty}")
    private String title;           // The title of the news item

    @NotEmpty(message="{news.description.notEmpty}")
    @Column(columnDefinition = "text")
    private String description;     // The description of the news item
    private String author;          // The author of the news item
    private LocalDateTime datePosted = LocalDateTime.now();        // The time when the news item was posted

    @Lob
    private byte[] bytes;          // The background image for front page

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
    
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDateTime getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(LocalDateTime datePosted) {
        this.datePosted = datePosted;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}
