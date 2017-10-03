package is.nord.service;

import is.nord.model.Ad;

/*
 * Author:
 *       Stella Rut Guðmundsdóttir (srg30@hi.is)
*/

/**
 * A service layer between the AdController and the AdRepository
 */
public interface AdService {
    // Returns all the news items (in descending order)
    Iterable<Ad> findAll();

    // Returns the news item with the specified id
    Ad findOne(Long id);

    // Saves the specified ad through a call to the adRepository
    void save(Ad ad);

    // Deletes the specified ad through a call to the adRepository
    void delete(Long adId);
}
