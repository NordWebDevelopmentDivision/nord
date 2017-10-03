package is.nord.service;

import is.nord.model.Ad;
import is.nord.repository.AdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * Author:
 *       Stella Rut Guðmundsdóttir (srg30@hi.is)
*/

@Service
public class AdServiceImpl implements AdService {
    @Autowired
    private AdRepository adRepository;

    @Override
    public Iterable<Ad> findAll() {
        return adRepository.findAll();
    }

    @Override
    public Ad findOne(Long id) {
        return adRepository.findOne(id);
    }

    @Override
    public void save(Ad ad) {
        adRepository.save(ad);
    }

    @Override
    public void delete(Long adId) {
        adRepository.delete(adId);
    }
}
