package is.nord.repository;

import is.nord.model.InfoBoard;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * An interface which queries the database for information information Nörd's board
 * @Author Stella Rut Guðmundsdóttir (srg30@hi.is)
 */
@Repository
public interface InfoBoardRepository extends CrudRepository<InfoBoard, Long> {
    // We only use methods that are included in CrudRepository
    // This is intentionally empty
}
