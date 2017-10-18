package is.nord.service;

import is.nord.model.Event;
import is.nord.model.EventBan;
import is.nord.model.User;

/*
 * Author:
 *       Kári Snær Kárason(ksk12@hi.is)
*/

/**
 * A service layer between the User controller and the EventBan repository
 */
public interface EventBanService {
    // Returns all the event bans
    Iterable<EventBan> findAllBanned();

    // Returns the news item with the specified id
    EventBan findOneByUser(User user);

    // Saves the specified event ban
    void save(EventBan eventBan);

    // Returns whether the specified user is event banned
    boolean isEventBanned(User user);

}
