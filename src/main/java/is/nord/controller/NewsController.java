package is.nord.controller;

import is.nord.FlashMessage;
import is.nord.model.*;
import is.nord.service.*;
import org.hibernate.validator.internal.metadata.descriptor.ReturnValueDescriptorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
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

        // Allow method calls from the thymeleaf template to adService
        model.addAttribute("adService", adService);

/*
        // Allow method calls from the thymeleaf template to registrationService
        model.addAttribute("registrationService", registrationService);

        // Allow method calls from the thymeleaf template to eventBanService
        model.addAttribute("eventBanService", eventBanService);

        // Allow method calls from the thymeleaf template to eventBanService
        model.addAttribute("userService", userService);
*/
        return "home/index";
    }

    @RequestMapping("news/{newsId}")
    public String getNewsDetails(@PathVariable Long newsId, Model model, Principal principal) {
        model.addAttribute("news", newsService.findOne(newsId));

        // If a user is logged in then add his/her object to the model
        if (principal != null) {
            // Get the user
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            model.addAttribute("user", user);
        }

        // Allow method calls from the thymeleaf template to adService
        model.addAttribute("adService", adService);

        return "news/newsDetails";
    }

    @RequestMapping("event/{newsId}")
    public String getEventDetails(@PathVariable Long newsId, Model model, Principal principal) {
        model.addAttribute("news", newsService.findOne(newsId));

        // If a user is logged in then add his/her object to the model
        if (principal != null) {
            // Get the user
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            model.addAttribute("user", user);
        }

        // Allow method calls from the thymeleaf template to adService
        model.addAttribute("adService", adService);

        // Allow method calls from the thymeleaf template to registrationService
        model.addAttribute("registrationService", registrationService);

        return "event/eventDetails";
    }


    /**
     * Form for adding a new news item
     * @param model the model
     * @return a webpage with a form for adding a new news item
     */
    @RequestMapping("/news/add")
    public String formNewNews(Model model) {

        if(!model.containsAttribute("news")) {
            model.addAttribute("news", new News());
        }

        // Add model attributes needed for new form
        //model.addAttribute("news", new News());
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

        if(!model.containsAttribute("news")) {
            model.addAttribute("news", newsService.findOne(newsId));
        }

        //model.addAttribute("news", newsService.findOne(newsId));
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
    public String saveNews(@Valid News news, BindingResult result, RedirectAttributes redirectAttributes, @RequestParam("file") MultipartFile file) {

        if (result.hasErrors()) {
            // Include validation errors upon redirect
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.news", result);

            // Add ad if invalid was received
            redirectAttributes.addFlashAttribute("news", news);

            // Redirect back to the form
            return "redirect:/news/add";
        }

        // Get the user
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Set author
        news.setAuthor(user.getUsername());

        // Save to database through newsService
        newsService.save(news, file);

        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Tókst að bæta við frétt", FlashMessage.Status.SUCCESS));

        // Redirect browser to /
        return "redirect:/";
    }

    /**
     * Update the news item in the datastore. The author of the news item is also updated.
     * @param news the news item that is to be updated
     * @return back to the main page.
     */
    @RequestMapping(value = "/news/{newsId}", method = RequestMethod.POST)
    public String updateNews(@Valid News news, BindingResult result, RedirectAttributes redirectAttributes, @RequestParam MultipartFile file) {
        if (result.hasErrors()) {
            // Include validation errors upon redirect
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.news", result);

            // Add data if invalid was received
            redirectAttributes.addFlashAttribute("news", news);

            // Redirect back to the form
            return String.format("redirect:/news/%s/edit",news.getId());
        }

        // Get the user
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Set author
        news.setAuthor(user.getUsername());

        if(file.isEmpty()) {
            newsService.save(news);
        } else {
            newsService.save(news, file);
        }

        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Tókst að uppfæra frétt", FlashMessage.Status.SUCCESS));

        return "redirect:/";
    }

    /**
     * Delete news item
     * @param newsId the id of the news item to be deleted
     * @return back to the home page
     */
    @RequestMapping("/news/{newsId}/delete")
    public String deleteNews(@PathVariable Long newsId, RedirectAttributes redirectAttributes) {
        News news = newsService.findOne(newsId);
        newsService.delete(news);

        redirectAttributes.addFlashAttribute("flash",new FlashMessage("Frétt hefur verið eytt!", FlashMessage.Status.SUCCESS));

        return "redirect:/";
    }

    /**
     * Form for adding a new event
     * @param model the model
     * @return a webpage with a form for adding a new event
     */
    @RequestMapping("/event/add")
    public String formNewEvent(Model model) {

        if(!model.containsAttribute("event")) {
            model.addAttribute("event", new Event());
        }

        // Add model attributes needed for new form
        //model.addAttribute("event", new Event());
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

        if(!model.containsAttribute("event")) {
            model.addAttribute("event", newsService.findOne(newsId));
        }

        //model.addAttribute("event", newsService.findOne(newsId));
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
    public String saveEvent(@Valid Event event, BindingResult result, RedirectAttributes redirectAttributes, @RequestParam("file") MultipartFile file) {

        if (result.hasErrors()) {
            // Include validation errors upon redirect
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.event", result);

            // Add ad if invalid was received
            redirectAttributes.addFlashAttribute("event", event);

            // Redirect back to the form
            return "redirect:/event/add";
        }

        // Get the user
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Set author
        event.setAuthor(user.getUsername());

        // Save to database through newsService
        newsService.save(event, file);

        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Tókst að bæta við viðburði", FlashMessage.Status.SUCCESS));

        // Redirect browser to /
        return "redirect:/";
    }

    /**
     * Update the news item in the datastore. The author of the event is also updated.
     * @param event the event that is to be updated
     * @return back to the main page.
     */
    @RequestMapping(value = "/event/{newsId}", method = RequestMethod.POST)
    public String updateEvent(@Valid Event event, BindingResult result, RedirectAttributes redirectAttributes, @RequestParam MultipartFile file) {
        if (result.hasErrors()) {
            // Include validation errors upon redirect
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.event", result);

            // Add data if invalid was received
            redirectAttributes.addFlashAttribute("event", event);

            // Redirect back to the form
            return String.format("redirect:/event/%s/edit",event.getId());
        }

        // Get the user
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Set author
        event.setAuthor(user.getUsername());

        if(file.isEmpty()) {
            newsService.save(event);
        } else {
            newsService.save(event, file);
        }

        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Tókst að uppfæra viðburð", FlashMessage.Status.SUCCESS));

        return "redirect:/";
    }

    /**
     * Delete event from the data store
     * @param newsId the id of the event to be deleted
     * @return back to the home page
     */
    @RequestMapping("/event/{newsId}/delete")
    public String deleteEvent(@PathVariable Long newsId, RedirectAttributes redirectAttributes) {
        News news = newsService.findOne(newsId);
        Iterable<Registration> registrations = registrationService.findRegistrationsByEvent((Event)news);
        registrationService.deleteAll(registrations);
        newsService.delete(news);

        redirectAttributes.addFlashAttribute("flash",new FlashMessage("Viðburði hefur verið eytt!", FlashMessage.Status.SUCCESS));

        return "redirect:/";
    }

    /**
     * Retrieves an image file from the database
     * @param newsId the id of the image file
     * @return a single image file from the database
     */
    @RequestMapping("/news/{newsId}.jpeg")
    @ResponseBody
    public byte[] getBackgroundImage(@PathVariable Long newsId) {
        return newsService.findOne(newsId).getBytes();
    }

    @RequestMapping (value = "/lifir", method=RequestMethod.GET)
    public String lifir(Model model) {
        User k = new User();
        model.addAttribute("kennari", k);
        if(newsService.erALifi())
            return "heima";
        else
            return "listiKennara";
    }
}