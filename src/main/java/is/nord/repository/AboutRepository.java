package is.nord.repository;

import is.nord.model.About;
import is.nord.model.Ad;
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
    // Fetches all the news items in descending order, i.e. the most recent in front
    List<About> findAll();
}
