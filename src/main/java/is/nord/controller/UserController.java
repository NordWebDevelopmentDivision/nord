package is.nord.controller;

import is.nord.model.Role;
import is.nord.model.User;
import is.nord.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    @RequestMapping("/user/userList")
    public String viewUsers(Model model) {

        Iterable<User> user = userService.findAll();

        model.addAttribute("user", user);
        return "user/list";
    }


    /**
     * Add a user item if valid data is received
     * @param user the user object formed from the user input that is to be added
     * @return back to the main page
     */
    @RequestMapping(value = "/user/save", method = RequestMethod.POST)
    public String saveUser(@RequestParam(value = "roleID", required = false) boolean id, User user) {
        user.setEnabled(true);
        Role role = new Role();
        System.out.println(id);
        if(id){
            role.setId((long) 2);
        } else{
            role.setId((long) 1);
        }

        role.setName("user");
        user.setRole(role);
        userService.save(user);

        // Redirect browser to /
        return "redirect:/";
    }

}
