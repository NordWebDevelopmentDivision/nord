package is.nord.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;

/**
 * A model for ads
 * @Author Ólafur Georg Gylfason (ogg4@hi.is)
 * @Author Stella Rut Guðmundsdóttir (srg30@hi.is)
 */
@Entity
@Table(name="ad")
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                // The id of the ad

    @URL(message="{ad.link.URL}")
    @NotEmpty(message="{ad.link.notEmpty}")
    private String link;            // The link behind the ad for the company's website

    @Lob
    private byte[] bytes;          // The ad, that is the company logo or product

    public Ad() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}
