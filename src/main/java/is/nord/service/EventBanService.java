package is.nord.service;

import is.nord.model.Event;
import is.nord.model.EventBan;
import is.nord.model.User;

/*
 * Author:
 *       Ólafur Georg Gylfason (ogg4@hi.is)
*/

/**
 * A service layer between the Registration controller and the Registration repository
 */
public interface EventBanService {
    // Returns all the event bans
    Iterable<EventBan> findAllBanned();

    // Returns the news item with the specified id
    EventBan findOneByUser(User user);

    // Saves the specified event ban through a call to the newsRepository
    void save(EventBan eventBan);

    // Returns whether the specified user is event banned
    boolean isEventBanned(User user);

}
