package is.nord.controller;

import is.nord.model.Event;
import is.nord.model.News;
import is.nord.model.Registration;
import is.nord.model.User;
import is.nord.service.NewsService;
import is.nord.service.RegistrationService;
import is.nord.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/*
 * Author:
 *       Ólafur Georg Gylfason (ogg4@hi.is)
 * Altered:
 *       Kári Snær Kárason(ksk12@hi.is)
*/

/**
 * A controller that controls registration-related things
 */
@Controller
public class RegistrationController {
    @Autowired
    RegistrationService registrationService;    // Establish a connection to the registrationService

    @Autowired
    private UserService userService;    // Establish a connection to the userService

    @Autowired
    private NewsService newsService;    // Establish a connection to the newsService

    // Points the user gets for registering and unregistering for an event
    private int registrationPoints = 1;
    private int unregistrationPoints = -1;

    /**
     * Allows an authenticated user to register to an event
     * @param eventId the id of the event to which the user is registering
     * @return back to the homepage
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@RequestParam("eventId") Long eventId) {
        // The new registration that will be added to the database
        Registration registration = new Registration();

        // Get the event
        Event event = (Event)newsService.findOne(eventId);
        registration.setEvent(event);

        // Get the user
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        registration.setUser(user);

        // Add a point to the user
        user.addPoints(registrationPoints);
        userService.update(user);
        // Save to database through a service
        registrationService.save(registration);

        // Redirect browser to /
        return "redirect:/";
    }

    /**
     * Allows an authenticated user to unregister from an event
     * @param newsId the id of the event from which the user is unregistering
     * @return back to the homepage
     */
    @RequestMapping(value = "/unregister", method = RequestMethod.POST)
    public String unregister(@RequestParam("newsId") Long newsId) {
        // Get the user
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Get the event
        News news = newsService.findOne(newsId);

        // Deduct a point from the user
        user.addPoints(unregistrationPoints);
        userService.update(user);

        // delete the registration for the authenticated user for this particular event
        registrationService.delete((Event)news, user);

        // Redirect browser to /
        return "redirect:/";
    }
}
