package is.nord.repository;

import is.nord.model.EventBan;
import is.nord.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * An interface which queries the database for EventBan-related things
 * @Author Kári Snær Kárason (ksk12@hi.is)
 */
@Repository
public interface EventBanRepository extends CrudRepository<EventBan, Long> {

    // Fetches all Event bans for users that are currently banned
    List<EventBan> findByCurrentlyBannedTrue();

    // Returns a value if the user is currently banned
    EventBan findByUserAndCurrentlyBannedTrue(User user);
}
