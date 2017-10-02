package is.nord.service;

import is.nord.repository.NewsRepository;
import is.nord.model.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * Author:
 *       Ólafur Georg Gylfason (ogg4@hi.is)
*/

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsRepository newsRepository;

    @Override
    public Iterable<News> findAll() {
        return newsRepository.findAllByOrderByIdDesc();
    }

    @Override
    public News findOne(Long id) {
        return newsRepository.findOne(id);
    }

    @Override
    public void save(News news) {
        newsRepository.save(news);
    }

    @Override
    public void delete(News news) {

    }
}
