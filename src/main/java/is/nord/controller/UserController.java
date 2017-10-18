package is.nord.controller;

import is.nord.model.Event;
import is.nord.model.EventBan;
import is.nord.model.Role;
import is.nord.model.User;
import is.nord.service.EventBanService;
import is.nord.service.UserService;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/*
 * Author:
 *       Kári Snær Kárason (ksk12@hi.is)
*/

/**
 * A controller that controls user related things
 */
@Controller
public class UserController {


    @Autowired
    private UserService userService;    // Establish a connection to the userService

    @Autowired
    private EventBanService eventBanService; // Establish a connection to the eventBanService

    private int eventBanPoints = -3;

    /**
     * Form for adding a new user item
     * @param model the model
     * @return a webpage with a form for adding a new user item
     */
    @RequestMapping("/user/add")
    public String formNewUser(Model model) {
        // Add model attributes needed for new form
        model.addAttribute("user", new User());
        model.addAttribute("action", "/user/save");
        model.addAttribute("heading", "Nýr notandi");
        model.addAttribute("submit","Búa til notanda");

        return "user/form";
    }

    /**
     * List of users
     * @param model the model
     * @return a webpage with a list of registered users
     */
    @RequestMapping("/user/userList")
    public String viewUsers(Model model) {
        // Get list of all users
        Iterable<User> user = userService.findAll();
        // Add model attribute
        model.addAttribute("user", user);

        // Allow method calls from the thymeleaf template to eventBanService
        model.addAttribute("eventBanService", eventBanService);

        return "user/list";
    }

    /**
     * Add a user item
     * @param user the user object formed from the user input that is to be added
     * @return back to the main page
     */
    @RequestMapping(value = "/user/save", method = RequestMethod.POST)
    public String saveUser(@RequestParam(value = "roleID", required = false) boolean id, User user) {
        // Mark the new user as enabled
        user.setEnabled(true);
        // Create a role and set the correct authorization to it
        Role role = new Role();
        if(id){
            role.setId((long) 2);
            role.setName("admin");
        } else{
            role.setId((long) 1);
            role.setName("user");
        }

        // Add the role to the user
        user.setRole(role);

        // Set the users beginning points as 0
        user.setPoints(0);
        // Save the new user
        userService.save(user);

        // Redirect browser to /
        return "redirect:/";
    }

    /**
     * Add an eventBan for the user
     * @param userId of the user object to be added to the ban
     * @return back to the userList page
     */
    @RequestMapping("/user/{userId}/eventBan")
    public String addEventBan(@PathVariable Long userId) {
        User user = userService.findOne(userId);
        EventBan eventBan = new EventBan();
        eventBan.setUser(user);
        eventBan.setCurrentlyBanned(true);
        eventBan.setTimeOfBan(LocalDateTime.now());

        // Deduct points from user for going on the banlist
        user.addPoints(eventBanPoints);
        userService.update(user);

        eventBanService.save(eventBan);
        return "redirect:/user/userList";
    }

    /**
     * Mark ban as not active for the user
     * @param userId of the user object to be marked as not banned anymore
     * @return back to the userList page
     */
    @RequestMapping("/user/{userId}/removeEventBan")
    public String removeEventBan(@PathVariable Long userId) {
        User user = userService.findOne(userId);
        EventBan eventBan = eventBanService.findOneByUser(user);
        eventBan.setCurrentlyBanned(false);
        eventBanService.save(eventBan);
        return "redirect:/user/userList";
    }

}
