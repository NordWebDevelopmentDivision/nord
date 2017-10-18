package is.nord.controller;

import is.nord.model.*;
import is.nord.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

/*
 * Author:
 *       Ólafur Georg Gylfason (ogg4@hi.is)
*/

/**
 * A controller that controls news/event related things
 */
@Controller
public class NewsController {

    @Autowired
    private NewsService newsService;    // Establish a connection to the newsService

    @Autowired
    private UserService userService;    // Establish a connection to the userService

    @Autowired
    private RegistrationService registrationService;    // Establish a connection to the registrationService

    @Autowired
    private AdService adService;    // Establish a connection to the adService

    @Autowired
    private EventBanService eventBanService; // Establish a connection to the eventBanService

    /**
     * Index of all news/event items
     * @param model the model
     * @return a webpage displaying all news/event items
     */
    @RequestMapping("/")
    public String listNews(Model model, Principal principal) {
        // Get all news items
        Iterable<News> news = newsService.findAll();
        // Add them to the model
        model.addAttribute("news", news);

        // If a user is logged in then add his/her object to the model
        if (principal != null) {
            // Get the user
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            model.addAttribute("user", user);
        }

        // Allow method calls from the thymeleaf template to registrationService
        model.addAttribute("registrationService", registrationService);

        // Allow method calls from the thymeleaf template to adService
        model.addAttribute("adService", adService);

        // Allow method calls from the thymeleaf template to eventBanService
        model.addAttribute("eventBanService", eventBanService);

        // Allow method calls from the thymeleaf template to eventBanService
        model.addAttribute("userService", userService);

        return "home/index";
    }

    /**
     * Form for adding a new news item
     * @param model the model
     * @return a webpage with a form for adding a new news item
     */
    @RequestMapping("/news/add")
    public String formNewNews(Model model) {
        // Add model attributes needed for new form
        model.addAttribute("news", new News());
        model.addAttribute("action", "/news/save");
        model.addAttribute("heading", "Ný frétt");
        model.addAttribute("submit","Birta frétt");

        return "news/form";
    }

    /**
     * Form for editing a news item
     * @param newsId the id of the news item that should be edited
     * @param model the model
     * @return a webpage with a filled in form which is editable
     */
    @RequestMapping("/news/{newsId}/edit")
    public String formEditNews(@PathVariable Long newsId, Model model) {
        model.addAttribute("news", newsService.findOne(newsId));
        model.addAttribute("action", String.format("/news/%s", newsId));
        model.addAttribute("heading", "Breyta frétt");
        model.addAttribute("submit","Uppfæra frétt");

        return "news/form";
    }

    /**
     * Add a news item if valid data is received TODO: validate the received data
     * @param news the news object formed from the user input that is to be added
     * @return back to the main page
     */
    @RequestMapping(value = "/news/save", method = RequestMethod.POST)
    public String saveNews(News news) {
        // Get the user
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Set author
        news.setAuthor(user.getUsername());

        // Save to database through newsService
        newsService.save(news);

        // Redirect browser to /
        return "redirect:/";
    }

    /**
     * Update the news item in the datastore. The author of the news item is also updated.
     * @param news the news item that is to be updated
     * @return back to the main page.
     */
    @RequestMapping(value = "/news/{newsId}", method = RequestMethod.POST)
    public String updateNews(News news) {

        // Get the user
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Set author
        news.setAuthor(user.getUsername());

        newsService.save(news);

        return "redirect:/";
    }

    /**
     * Delete news item
     * @param newsId the id of the news item to be deleted
     * @return back to the home page
     */
    @RequestMapping("/news/{newsId}/delete")
    public String deleteNews(@PathVariable Long newsId) {
        News news = newsService.findOne(newsId);
        newsService.delete(news);

        return "redirect:/";
    }

    /**
     * Form for adding a new event
     * @param model the model
     * @return a webpage with a form for adding a new event
     */
    @RequestMapping("/event/add")
    public String formNewEvent(Model model) {
        // Add model attributes needed for new form
        model.addAttribute("event", new Event());
        model.addAttribute("action", "/event/save");
        model.addAttribute("heading","Nýr viðburður");
        model.addAttribute("submit","Birta viðburð");

        return "event/form";
    }

    /**
     * Form for editing an event
     * @param newsId the id of the event that should be edited
     * @param model the model
     * @return a webpage with a filled in form which is editable
     */
    @RequestMapping("/event/{newsId}/edit")
    public String formEditEvent(@PathVariable Long newsId, Model model) {
        model.addAttribute("event", newsService.findOne(newsId));
        model.addAttribute("action", String.format("/event/%s", newsId));
        model.addAttribute("heading", "Breyta viðburði");
        model.addAttribute("submit","Uppfæra viðburð");

        return "event/form";
    }

    /**
     * Add a event if valid data is received TODO: validate the received data
     * @param event the event object formed from the user input that is to be added
     * @return Back to the main page
     */
    @RequestMapping(value = "/event/save", method = RequestMethod.POST)
    public String saveEvent(Event event) {
        // Get the user
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Set author
        event.setAuthor(user.getUsername());

        // Save to database through newsService
        newsService.save(event);

        // Redirect browser to /
        return "redirect:/";
    }

    /**
     * Update the news item in the datastore. The author of the event is also updated.
     * @param event the event that is to be updated
     * @return back to the main page.
     */
    @RequestMapping(value = "/event/{newsId}", method = RequestMethod.POST)
    public String updateEvent(Event event) {

        // Get the user
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Set author
        event.setAuthor(user.getUsername());

        newsService.save(event);

        return "redirect:/";
    }

    /**
     * Delete event from the data store
     * @param newsId the id of the event to be deleted
     * @return back to the home page
     */
    @RequestMapping("/event/{newsId}/delete")
    public String deleteEvent(@PathVariable Long newsId) {
        News news = newsService.findOne(newsId);
        Iterable<Registration> registrations = registrationService.findRegistrationsByEvent((Event)news);
        registrationService.deleteAll(registrations);
        newsService.delete(news);

        return "redirect:/";
    }


}
