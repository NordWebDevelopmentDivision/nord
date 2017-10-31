package is.nord.controller;

import is.nord.FlashMessage;
import is.nord.model.Ad;
import is.nord.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/*
 * Author:
 *       Stella Rut Guðmundsdóttir (srg30@hi.is)
 *       Ólafur Georg Gylfason (ogg4@hi.is)
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

        if(!model.containsAttribute("ad")) {
            model.addAttribute("ad", new Ad());
        }

        // Add model attributes needed for new form
        model.addAttribute("action", "/ad/save");
        model.addAttribute("heading", "Ný auglýsing");
        model.addAttribute("submit","Vista auglýsingu");

        return "ad/form";
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
     * Add a new ad if valid data is received TODO: validate the received data
     * @param ad the ad object formed from the user input that is to be added
     * @param file the image file
     * @return back to the main page
     */
    @RequestMapping(value = "/ad/save", method = RequestMethod.POST)
    public String saveAd(@Valid Ad ad, BindingResult result, RedirectAttributes redirectAttributes, @RequestParam("file") MultipartFile file) {
        if (result.hasErrors()) {
            // Include validation errors upon redirect
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.ad", result);

            // Add ad if invalid was received
            redirectAttributes.addFlashAttribute("ad", ad);

            // Redirect back to the form
            return "redirect:/ad/add";
        }

        // Save to database through adService
        adService.save(ad, file);

        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Tókst að bæta við auglýsingu", FlashMessage.Status.SUCCESS));

        // Redirect browser to /
        return "redirect:/";
    }

    /**
     * Updates a certain ad if valid data is received TODO: validate the received data
     * @param ad the ad to update
     * @param file the image file
     * @return back to the main page
     */
    @RequestMapping(value = "/ad/{adId}", method = RequestMethod.POST)
    public String updateAd(Ad ad, @RequestParam MultipartFile file) {

        if(file.isEmpty()) {
            adService.save(ad);
        } else {
            adService.save(ad, file);
        }

        return "redirect:/";
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
     * Retrieves an image file from the database
     * @param adId the id of the image file
     * @return a single image file from the database
     */
    @RequestMapping("/ad/{adId}.jpg")
    @ResponseBody
    public byte[] adLogo(@PathVariable Long adId) {
        return adService.findOne(adId).getBytes();
    }
}
