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
    // Returns all the news items (in descending order)
    Iterable<About> findAll();

    // Returns the news item with the specified id
    About findOne(Long id);

    // Saves the specified ad through a call to the adRepository
    void save(About about);

    // Deletes the specified ad through a call to the adRepository
    void delete(About about);
}
