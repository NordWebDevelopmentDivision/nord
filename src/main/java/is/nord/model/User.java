package is.nord.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/*
 * Author:
 *       Chris Ramacciotti, a teacher at teamtreehouse.com
 * Altered by:
 *       Ólafur Georg Gylfason (ogg4@hi.is)
*/

/**
 * Define users
 */
@Entity
@Table(name="member")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;            // The id of the user

    @Column(unique = true)
    @Size(min = 8, max = 20)
    private String username;    // The username of the user

    @Column(length = 100)
    private String password;    // The password of the user

    @Column(nullable = false)
    private boolean enabled;    // Whether the user is enabled

    @OneToOne
    @JoinColumn(name = "role_id")
    private Role role;          // The assigned role of the user

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.getName()));
        return authorities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
