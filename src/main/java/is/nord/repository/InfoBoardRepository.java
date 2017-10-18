package is.nord.repository;

import is.nord.model.InfoBoard;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/*
 * Author:
 *       Stella Rut Guðmundsdóttir (srg30@hi.is)
*/

/**
 * An interface which queries the database for information information Nörd's board
 */
@Repository
public interface InfoBoardRepository extends CrudRepository<InfoBoard, Long> {
    // We only use methods that are included in CrudRepository
    // This is intentionally empty
}
