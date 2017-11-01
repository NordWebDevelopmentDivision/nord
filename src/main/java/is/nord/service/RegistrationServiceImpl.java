package is.nord.service;

import is.nord.model.Event;
import is.nord.model.Registration;
import is.nord.model.User;
import is.nord.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * Author:
 *       Ólafur Georg Gylfason (ogg4@hi.is)
*/

@Service
public class RegistrationServiceImpl implements RegistrationService {
    @Autowired
    RegistrationRepository registrationRepository;

    @Override
    public Iterable<Registration> findAll() {
        return registrationRepository.findAll();
    }

    @Override
    public void save(Registration registration) {
        registrationRepository.save(registration);
    }

    @Override
    public void delete(Event event, User user) {
        // Get the registration
        Registration registration = registrationRepository.findRegistrationByEventAndUser(event, user);

        // Then delete it
        registrationRepository.delete(registration);
    }

    @Override
    public boolean isRegisteredForEvent(Event event, User user) {
        if (registrationRepository.findRegistrationByEventAndUser(event, user) == null) {
            return false;
        }
        return true;
    }

    @Override
    public Iterable<Registration> findRegistrationsByEvent(Event event) {
        // TODO: If the event is priority event, then sort the list accordingly
        return registrationRepository.findRegistrationsByEvent(event);
    }

    @Override
    public void deleteAll(Iterable<Registration> registrations) {
        registrationRepository.delete(registrations);
    }

    /*@Override
    public List<TopEventUser> findTopUsers(){
        return registrationRepository.findTopEventUsers();
    }*/


}
