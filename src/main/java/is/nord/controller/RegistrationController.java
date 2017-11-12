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
import org.springframework.web.bind.annotation.PathVariable;
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
    public String register(@RequestParam("eventIdReg") Long eventId) {
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
        return "redirect:/event/" + eventId;
    }


    /**
     * Allows an authenticated user to unregister from an event
     * @param eventId the id of the event from which the user is unregistering
     * @return back to the homepage
     */
    @RequestMapping(value = "/unregister", method = RequestMethod.POST)
    public String unregister(@RequestParam("eventIdUnreg") Long eventId) {
        // Get the user
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Get the event
        Event event = (Event)newsService.findOne(eventId);

        // delete the registration for the authenticated user for this particular event
        registrationService.delete(event, user);

        // Redirect browser to /
        return "redirect:/event/" + eventId;
    }

    @RequestMapping("/confirmRegistration/{registrationId}")
    public String confirmRegistration(@PathVariable Long registrationId ) {
        Registration registration = registrationService.findRegistrationById(registrationId);
        registration.setConfirmed(true);

        User user = registration.getUser();
        user.addPoints(registrationPoints);
        userService.update(user);

        Event event = registration.getEvent();

        return "redirect:/event/" + event.getId();
    }

    @RequestMapping("/unconfirmRegistration/{registrationId}")
    public String unconfirmRegistration(@PathVariable Long registrationId ) {
        Registration registration = registrationService.findRegistrationById(registrationId);
        registration.setConfirmed(false);
        registrationService.update(registration);

        User user = registration.getUser();
        user.addPoints(unregistrationPoints);
        userService.update(user);

        Event event = registration.getEvent();

        return "redirect:/event/" + event.getId();
    }
}
