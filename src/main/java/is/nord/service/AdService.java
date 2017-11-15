package is.nord.service;

import is.nord.model.Ad;
import org.springframework.web.multipart.MultipartFile;

/**
 * A service layer between the AdController and the AdRepository
 * @Author Ólafur Georg Gylfason (ogg4@hi.is)
 * @Author Stella Rut Guðmundsdóttir (srg30@hi.is)
 */
public interface AdService {
    // Returns all the ads
    Iterable<Ad> findAll();

    // Returns the ad with the specified id
    Ad findOne(Long id);

    // Saves the specified ad through a call to the adRepository
    void save(Ad ad, MultipartFile file);

    void save(Ad ad);

    // Deletes the specified ad through a call to the adRepository
    void delete(Ad ad);
}
