package is.nord.service;

import is.nord.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * A service layer for user objects
 * @Author Chris Ramacciotti, a teacher at teamtreehouse.com
 * @Author Kári Snær Kárason(ksk12@hi.is)
 */
public interface UserService extends UserDetailsService {

    Iterable<User> findAll();

    // Returns a user with the specified username. Throws exception if that user does not exist
    User findByUsername(String username);

    // Returns the news item with the specified id
    User findOne(Long id);

    // Saves the specified user item through a call to the userRepository
    void save(User user);

    // Updates the user
    void update(User user);

    // Finds all users and orderes the output by points
    Iterable<User> findAllOrderedByPoints();
}
