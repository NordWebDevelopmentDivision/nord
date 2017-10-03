package is.nord.controller;

import is.nord.model.Ad;
import is.nord.model.Event;
import is.nord.model.News;
import is.nord.model.User;
import is.nord.service.AdService;
import is.nord.service.NewsService;
import is.nord.service.RegistrationService;
import is.nord.service.UserService;
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
 *       Stella Rut Guðmundsdóttir (srg30@hi.is)
*/

/**
 * A controller that controls ad related things
 */
@Controller
public class AdController {

    @Autowired
    private AdService adService;    // Establish a connection to the adService


    /**
     * Form for adding a new ad
     * @param model the model
     * @return a webpage with a form for adding a new ad
     */
    @RequestMapping("/ad/add")
    public String formNewAd(Model model) {
        // Add model attributes needed for new form
        model.addAttribute("ad", new Ad());
        model.addAttribute("action", "/ad/save");
        model.addAttribute("heading", "Ný auglýsing");
        model.addAttribute("submit","Vista auglýsingu");

        return "ad/form";
    }

    /**
     * Add a new ad if valid data is received TODO: validate the received data
     * @param ad the ad object formed from the user input that is to be added
     * @return back to the main page
     */
    @RequestMapping(value = "/ad/save", method = RequestMethod.POST)
    public String saveAd(Ad ad) {
        // Save to database through adService
        adService.save(ad);

        // Redirect browser to /
        return "redirect:/";
    }

    /**
     * Edit an ad if valid data is received TODO: validate the received data
     * @param adId the id of the ad to be deleted
     * @return a webpage with a form for editing the ad
     */
    @RequestMapping("/ad/{adId}/edit")
    public String formEditAd(@PathVariable Long adId, Model model) {
        model.addAttribute("ad", adService.findOne(adId));
        model.addAttribute("action", String.format("/ad/%s", adId));
        model.addAttribute("heading", "Breyta auglýsingu");
        model.addAttribute("submit","Uppfæra");

        return "ad/form";
    }

    /**
     * Deletes a certain ad if valid data is received TODO: validate the received data
     * @param adId the id of the ad to be deleted
     * @return back to the main page
     */
    @RequestMapping("/ad/{adId}/delete")
    public String deleteAd(@PathVariable Long adId) {
        Ad ad = adService.findOne(adId);
        adService.delete(ad);

        return "redirect:/";
    }

    /**
     * Updates a certain ad if valid data is received TODO: validate the received data
     * @param ad the ad to update
     * @return back to the main page
     */
    @RequestMapping(value = "/ad/{adId}", method = RequestMethod.POST)
    public String updateAd(Ad ad) {
        adService.save(ad);

        return "redirect:/";
    }

}
