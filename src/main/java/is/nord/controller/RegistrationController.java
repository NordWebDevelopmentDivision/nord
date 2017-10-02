package is.nord.controller;

import is.nord.model.Event;
import is.nord.model.News;
import is.nord.model.Registration;
import is.nord.model.User;
import is.nord.service.NewsService;
import is.nord.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/*
 * Author:
 *       Ólafur Georg Gylfason (ogg4@hi.is)
*/

/**
 * A controller that controls registration-related things
 */
@Controller
public class RegistrationController {
    @Autowired
    RegistrationService registrationService;    // Establish a connection to the registrationService

    @Autowired
    NewsService newsService;    // Establish a connection to the newsService

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

        // delete the registration for the authenticated user for this particular event
        registrationService.delete((Event)news, user);

        // Redirect browser to /
        return "redirect:/";
    }
}
