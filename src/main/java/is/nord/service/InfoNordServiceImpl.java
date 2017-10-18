package is.nord.service;

import is.nord.model.InfoNord;
import is.nord.repository.InfoNordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * Author:
 *       Stella Rut Guðmundsdóttir (srg30@hi.is)
*/

@Service
public class InfoNordServiceImpl implements InfoNordService {
    @Autowired
    private InfoNordRepository infoNordRepository;

    @Override
    public Iterable<InfoNord> findAll() {
        return infoNordRepository.findAll();
    }

    @Override
    public InfoNord findOne(Long id) {
        return infoNordRepository.findOne(id);
    }

    @Override
    public void save(InfoNord infoNord) {
        infoNordRepository.save(infoNord);
    }

    @Override
    public void delete(InfoNord infoNord) {
        infoNordRepository.delete(infoNord);
    }
}
