package is.nord.service;

import is.nord.model.InfoNord;

/*
 * Author:
 *       Stella Rut Guðmundsdóttir (srg30@hi.is)
*/

/**
 * A service layer between the InformationController and the InfoNordRepository
 */
public interface InfoNordService {
    // Returns all the information
    Iterable<InfoNord> findAll();

    // Returns the info with the specified id
    InfoNord findOne(Long id);

    // Saves the specified info through a call to the aboutRepository
    void save(InfoNord infoNord);

    // Deletes the specified info through a call to the aboutRepository
    void delete(InfoNord infoNord);
}
