package is.nord.service;

import is.nord.model.Event;
import is.nord.model.EventBan;
import is.nord.model.User;
import is.nord.repository.EventBanRepository;
import is.nord.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * Author:
 *       Kári Snær Kárason(ksk12@hi.is)
*/

@Service
public class EventBanServiceImpl implements EventBanService {
    @Autowired
    EventBanRepository eventBanRepository;

    @Override
    public Iterable<EventBan> findAllBanned() {
        return eventBanRepository.findByCurrentlyBannedTrue();
    }

    @Override
    public void save(EventBan eventBan) {
        eventBanRepository.save(eventBan);
    }

    @Override
    public EventBan findOneByUser(User user){
        EventBan eventBan = eventBanRepository.findByUserAndCurrentlyBannedTrue(user);
        return eventBan;
    }

    @Override
    public boolean isEventBanned(User user){
        EventBan eventBan = eventBanRepository.findByUserAndCurrentlyBannedTrue(user);
        if(eventBan == null){
            return false;
        }
        else return true;
    }

}
