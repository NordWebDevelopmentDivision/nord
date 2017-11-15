package is.nord.service;

import is.nord.model.News;
import org.springframework.web.multipart.MultipartFile;


/**
 * A service layer between the Newscontroller and the Newsrepository
 * @Author Ólafur Georg Gylfason (ogg4@hi.is)
 * @Date 1.11.2017
 */
public interface NewsService {
    // Returns all the news items (in descending order)
    Iterable<News> findAll();

    // Returns the news item with the specified id
    News findOne(Long id);

    // Saves the specified news item through a call to the newsRepository
    void save(News news, MultipartFile file);

    // Saves the specified item
    void save(News news);

    // Deletes the specified news item through a call to the newsRepository
    void delete(News news);

    // For test purposes
    public boolean erALifi();
}
