package is.nord.repository;

import is.nord.model.Ad;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/*
 * Author:
 *       Stella Rut Guðmundsdóttir (srg30@hi.is)
*/

/**
 * An interface which queries the database for ads
 */
@Repository
public interface AdRepository extends CrudRepository<Ad, Long> {
    // Fetches all the information
    List<Ad> findAll();
}
