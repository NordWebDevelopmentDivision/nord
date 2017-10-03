package is.nord.controller;

import is.nord.model.About;
import is.nord.model.Ad;
import is.nord.service.AboutService;
import is.nord.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/*
 * Author:
 *       Stella Rut Guðmðundsdóttir (srg30@hi.is)
*/

/**
 * A controller that handles informations about Nörd
 */
@Controller
public class AboutController {

    @Autowired
    private AboutService aboutService;    // Establish a connection to the aboutService

    @Autowired
    private AdService adService;    // Establish a connection to the adService


    @RequestMapping(path = "/about", method = RequestMethod.GET)
    public String about(Model model, Principal principal) {
        // Get all about items
        Iterable<About> about = aboutService.findAll();
        // Add them to the model
        model.addAttribute("about", about);

        // Get all ads
        Iterable<Ad> ad = adService.findAll();
        // Add them to the model
        model.addAttribute("ad", ad);

        return "about/about";
    }

    /**
     * Form for adding new information about Nörd
     * @param model the model
     * @return a webpage with a form for adding new information
     */
    @RequestMapping("/about/add")
    public String formNewAd(Model model) {
        // Add model attributes needed for new form
        model.addAttribute("about", new About());
        model.addAttribute("action", "/about/save");
        model.addAttribute("heading", "Nýjar upplýsingar");
        model.addAttribute("submit","Vista upplýsingar");

        return "about/form";
    }

    /**
     * Add new information if valid data is received TODO: validate the received data
     * @param about the information from the user input that is to be added
     * @return back to the main page
     */
    @RequestMapping(value = "/about/save", method = RequestMethod.POST)
    public String saveAbout(About about) {
        // Save to database through aboutService
        aboutService.save(about);

        // Redirect browser
        return "redirect:/about";
    }

    /**
     * Edits information if valid data is received TODO: validate the received data
     * @param aboutId the id of the information about to be edited
     * @param model  the model
     * @return a website with a form to edit
     */
    @RequestMapping("/about/{aboutId}/edit")
    public String formEditNews(@PathVariable Long aboutId, Model model) {
        model.addAttribute("about", aboutService.findOne(aboutId));
        model.addAttribute("action", String.format("/about/%s", aboutId));
        model.addAttribute("heading", "Breyta upplýsingum");
        model.addAttribute("submit","Uppfæra upplýsingar");

        return "about/form";
    }

    /**
     * Updates information if valid data is received TODO: validate the received data
     * @param about the information to update
     * @return back to the about page
     */
    @RequestMapping(value = "/about/{aboutId}", method = RequestMethod.POST)
    public String updateInformation(About about) {

        aboutService.save(about);

        return "about/about";
    }

    /**
     * Deletes certain information if valid data is received TODO: validate the received data
     * @param aboutId the id of the information item to be deleted
     * @return back to the about page
     */
    @RequestMapping("/about/{aboutId}/delete")
    public String deleteAbout(@PathVariable Long aboutId) {
        About about = aboutService.findOne(aboutId);
        aboutService.delete(about);

        return "redirect:/about";
    }
}
