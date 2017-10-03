package is.nord.model;

import javax.persistence.*;
import java.io.File;

/*
 * Author:
 *       Stella Rut Guðmundsdóttir (srg30@hi.is)
*/

@Entity
@Table(name="about")
public class About {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                // The id of the ad
    private String title;           // The title

    @Column(columnDefinition = "text")
    private String content;         // The content

    public About() {}

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
