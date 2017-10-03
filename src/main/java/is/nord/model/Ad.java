package is.nord.model;

import javax.persistence.*;
import java.awt.*;
import java.io.File;
import java.util.Date;

/*
 * Author:
 *       Stella Rut Guðmundsdóttir (srg30@hi.is)
*/

@Entity
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                // The id of the ad
    private String link;            // The link behind the ad for the company's website
    private File adLogo;           // The ad, that is the company logo or product

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

    public File getAdLogo() {
        return adLogo;
    }

    public void setAdLogo(File adLogo) {
        this.adLogo = adLogo;
    }
}
