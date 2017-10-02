package is.nord.service;

import is.nord.model.News;

/*
 * Author:
 *       Ólafur Georg Gylfason (ogg4@hi.is)
*/

/**
 * A service layer between the Newscontroller and the Newsrepository
 */
public interface NewsService {
    // Returns all the news items (in descending order)
    Iterable<News> findAll();

    // Returns the news item with the specified id
    News findOne(Long id);

    // Saves the specified news item through a call to the newsRepository
    void save(News news);

    // Deletes the specified news item through a call to the newsRepository
    void delete(News news);
}
