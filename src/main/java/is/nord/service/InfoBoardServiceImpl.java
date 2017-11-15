package is.nord.service;

import is.nord.model.InfoBoard;
import is.nord.repository.InfoBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import is.nord.repository.AdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Author Stella Rut Guðmundsdóttir (srg30@hi.is)
 */
@Service
public class InfoBoardServiceImpl implements InfoBoardService {
    @Autowired
    private InfoBoardRepository infoBoardRepository;

    @Override
    public Iterable<InfoBoard> findAll() {
        return infoBoardRepository.findAll();
    }

    @Override
    public InfoBoard findOne(Long id) {
        return infoBoardRepository.findOne(id);
    }

    @Override
    public void save(InfoBoard infoBoard, MultipartFile file) {
        try {
            infoBoard.setBytes(file.getBytes());
            infoBoardRepository.save(infoBoard);
        } catch (IOException e) {
            System.err.println("Unable to get byte array from uploaded file.");
        }
    }

    @Override
    public void save(InfoBoard infoBoard) {
        infoBoardRepository.save(infoBoard);
    }

    @Override
    public void delete(InfoBoard infoBoard) {
        infoBoardRepository.delete(infoBoard);
    }
}
