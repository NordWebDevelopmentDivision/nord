package is.nord.service;

import is.nord.model.About;
import is.nord.repository.AboutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * Author:
 *       Stella Rut Guðmundsdóttir (srg30@hi.is)
*/

@Service
public class AboutServiceImpl implements AboutService {
    @Autowired
    private AboutRepository aboutRepository;

    @Override
    public Iterable<About> findAll() {
        return aboutRepository.findAll();
    }

    @Override
    public About findOne(Long id) {
        return aboutRepository.findOne(id);
    }

    @Override
    public void save(About about) {
        aboutRepository.save(about);
    }

    @Override
    public void delete(Long aboutId) {
        aboutRepository.delete(aboutId);
    }
}
