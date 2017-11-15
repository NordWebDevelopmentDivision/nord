package is.nord.repository;

import is.nord.model.Ad;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * An interface which queries the database for ads
 * @Author Stella Rut Guðmundsdóttir (srg30@hi.is)
 */
@Repository
public interface AdRepository extends CrudRepository<Ad, Long> {
    // We only use methods that are included in CrudRepository
    // This is intentionally empty
}
