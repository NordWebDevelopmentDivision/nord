package is.nord.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * A model for information information Nörd
 * @Author Stella Rut Guðmundsdóttir (srg30@hi.is)
 */
@Entity
@Table(name="infoNord")
public class InfoNord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                // The id of the ad

    @NotEmpty(message="{infoNord.title.notEmpty}")
    private String title;           // The title

    @Column(columnDefinition = "text")
    @NotEmpty(message="{infoNord.content.notEmpty}")
    private String content;         // The content

    public InfoNord() {}

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
