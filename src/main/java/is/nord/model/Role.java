package is.nord.model;

import javax.persistence.*;

/**
 * Define a role for each user.
 * Each role has different authorization
 * @Author Chris Ramacciotti, a teacher at teamtreehouse.com
 */
@Entity
@Table(name="role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;        // The id of the role
    private String name;    // The name of the role

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
}
