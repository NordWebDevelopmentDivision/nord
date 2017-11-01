package is.nord.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/*
 * Author:
 *       Stella Rut Guðmundsdóttir (srg30@hi.is)
*/

/**
 * A model for information information the board
 */
@Entity
@Table(name="infoBoard")
public class InfoBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                // The id of the ad

    @NotEmpty(message="{infoBoard.name.notEmpty}")
    private String name;           // The person's name

    @NotEmpty(message="{infoBoard.title.notEmpty}")
    private String title;         // The title
    private boolean isBoard;         // Specifies if the person is one of the top five

    @Lob
    private byte[] bytes;          // The ad, that is the company logo or product

    public InfoBoard() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean getIsBoard() {
        return isBoard;
    }

    public void setIsBoard(boolean isBoard) {
        this.isBoard = isBoard;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}
