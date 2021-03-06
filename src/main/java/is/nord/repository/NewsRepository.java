package is.nord.repository;

import is.nord.model.News;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * An interface which queries the database for news-related things
 * @Author Ólafur Georg Gylfason (ogg4@hi.is)
 */
@Repository
public interface NewsRepository extends CrudRepository<News, Long> {
    // Fetches all the news items in descending order, i.e. the most recent in front
    List<News> findAllByOrderByIdDesc();
}
