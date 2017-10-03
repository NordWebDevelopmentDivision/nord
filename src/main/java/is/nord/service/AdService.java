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
    // Returns all the ads
    Iterable<Ad> findAll();

    // Returns the ad with the specified id
    Ad findOne(Long id);

    // Saves the specified ad through a call to the adRepository
    void save(Ad ad);

    // Deletes the specified ad through a call to the adRepository
    void delete(Ad ad);
}
