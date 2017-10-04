package is.nord.repository;

import is.nord.model.About;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/*
 * Author:
 *       Stella Rut Guðmundsdóttir (srg30@hi.is)
*/

/**
 * An interface which queries the database for information about Nörd
 */
@Repository
public interface AboutRepository extends CrudRepository<About, Long> {
    // We only use methods that are included in CrudRepository
    // This is intentionally empty
}
