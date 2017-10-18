package is.nord.service;

import is.nord.model.InfoBoard;
import org.springframework.web.multipart.MultipartFile;

/*
 * Author:
 *       Stella Rut Guðmundsdóttir (srg30@hi.is)
*/

/**
 * A service layer between the InformationController and the InfoNordRepository
 */
public interface InfoBoardService {
    // Returns all the information
    Iterable<InfoBoard> findAll();

    // Returns the info with the specified id
    InfoBoard findOne(Long id);

    // Saves the specified info through a call to the aboutRepository
    void save(InfoBoard infoBoard, MultipartFile file);

    void save(InfoBoard infoBoard);

    // Deletes the specified info through a call to the aboutRepository
    void delete(InfoBoard infoBoard);
}
