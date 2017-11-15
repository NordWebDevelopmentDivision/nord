package is.nord.repository;

import is.nord.model.InfoNord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * An interface which queries the database for information information Nörd
 * @Author Stella Rut Guðmundsdóttir (srg30@hi.is)
 */
@Repository
public interface InfoNordRepository extends CrudRepository<InfoNord, Long> {
    // We only use methods that are included in CrudRepository
    // This is intentionally empty
}
