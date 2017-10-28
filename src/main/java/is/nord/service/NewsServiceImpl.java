package is.nord.service;

import is.nord.repository.NewsRepository;
import is.nord.model.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;

/*
 * Author:
 *       Ólafur Georg Gylfason (ogg4@hi.is)
*/

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsRepository newsRepository;

    @Override
    @Transactional
    public Iterable<News> findAll() {
        return newsRepository.findAllByOrderByIdDesc();
    }

    @Override
    @Transactional
    public News findOne(Long id) {
        return newsRepository.findOne(id);
    }

    @Override
    public void save(News news, MultipartFile file) {
        try {
            news.setBytes(file.getBytes());
            newsRepository.save(news);
        } catch (IOException e) {
            System.err.println("Unable to get byte array from uploaded file.");
        }
    }

    @Override
    public void save(News news) {
        newsRepository.save(news);
    }

    @Override
    public void delete(News news) {
        newsRepository.delete(news);
    }
}
