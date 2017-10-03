package is.nord.service;

import is.nord.model.About;

/*
 * Author:
 *       Stella Rut Guðmundsdóttir (srg30@hi.is)
*/

/**
 * A service layer between the AboutController and the AboutRepository
 */
public interface AboutService {
    // Returns all the information
    Iterable<About> findAll();

    // Returns the info with the specified id
    About findOne(Long id);

    // Saves the specified info through a call to the aboutRepository
    void save(About about);

    // Deletes the specified info through a call to the aboutRepository
    void delete(About about);
}
