package is.nord.controller;

import is.nord.FlashMessage;
import is.nord.model.InfoBoard;
import is.nord.model.InfoNord;
import is.nord.service.InfoBoardService;
import is.nord.service.InfoNordService;
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
 *       Stella Rut Guðmðundsdóttir (srg30@hi.is)
*/

/**
 * A controller that handles information about Nörd
 */
@Controller
public class InformationController {

    @Autowired
    private InfoNordService infoNordService;    // Establish a connection to the infoNordService

    @Autowired
    private InfoBoardService infoBoardService; // Establish a connection to the InfoBoardService

    /**
     * Displays informations about Nörd
     * @param model the model
     * @return a website that displays informations about Nörd
     */
    @RequestMapping(path = "/about", method = RequestMethod.GET)
    public String about(Model model) {
        // Get all information items
        Iterable<InfoNord> infoNord = infoNordService.findAll();
        // Add them to the model
        model.addAttribute("infoNord", infoNord);

        Iterable<InfoBoard> infoBoard = infoBoardService.findAll();
        model.addAttribute("infoBoard", infoBoard);

        return "information/information";
    }

    /**
     * Form for adding new information about Nörd
     * @param model the model
     * @return a webpage with a form for adding new information
     */
    @RequestMapping("/infoNord/add")
    public String formNewInfoNord(Model model) {

        if(!model.containsAttribute("infoNord")) {
            model.addAttribute("infoNord", new InfoNord());
        }

        // Add model attributes needed for new form
        model.addAttribute("action", "/infoNord/save");
        model.addAttribute("heading", "Nýjar upplýsingar");
        model.addAttribute("submit","Vista upplýsingar");

        return "information/form";
    }

    /**
     * Edits information if valid data is received TODO: validate the received data
     * @param infoId the id of the information to be edited
     * @param model  the model
     * @return a website with a form to edit
     */
    @RequestMapping("/infoNord/{infoId}/edit")
    public String formEditInfoNord(@PathVariable Long infoId, Model model) {

        if(!model.containsAttribute("infoNord")) {
            model.addAttribute("infoNord", infoNordService.findOne(infoId));
        }

        //model.addAttribute("infoNord", infoNordService.findOne(infoId));
        model.addAttribute("action", String.format("/infoNord/%s", infoId));
        model.addAttribute("heading", "Breyta upplýsingum");
        model.addAttribute("submit","Uppfæra upplýsingar");

        return "information/form";
    }

    /**
     * Add new information if valid data is received TODO: validate the received data
     * @param infoNord the information from the user input that is to be added
     * @return back to the main page
     */
    @RequestMapping(value = "/infoNord/save", method = RequestMethod.POST)
    public String saveInfoNord(@Valid InfoNord infoNord, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            // Include validation errors upon redirect
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.infoNord", result);

            // Add ad if invalid was received
            redirectAttributes.addFlashAttribute("infoNord", infoNord);

            // Redirect back to the form
            return "redirect:/infoNord/add";
        }

        // Save to database through aboutService
        infoNordService.save(infoNord);

        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Tókst að bæta við upplýsingum", FlashMessage.Status.SUCCESS));

        // Redirect browser
        return "redirect:/about";
    }

    /**
     * Updates information if valid data is received TODO: validate the received data
     * @param infoNord the information to update
     * @return back to the infoNord page
     */
    @RequestMapping(value = "/infoNord/{infoId}", method = RequestMethod.POST)
    public String updateInfoNord(@Valid InfoNord infoNord, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            // Include validation errors upon redirect
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.infoNord", result);

            // Add data if invalid was received
            redirectAttributes.addFlashAttribute("infoNord", infoNord);

            // Redirect back to the form
            return String.format("redirect:/infoNord/%s/edit",infoNord.getId());
        }

        // Save to database through aboutService
        infoNordService.save(infoNord);

        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Tókst að uppfæra upplýsingar", FlashMessage.Status.SUCCESS));

        return "redirect:/about";
    }

    /**
     * Deletes certain information if valid data is received TODO: validate the received data
     * @param infoId the id of the information item to be deleted
     * @return back to the information page
     */
    @RequestMapping("/infoNord/{infoId}/delete")
    public String deleteInfoNord(@PathVariable Long infoId, RedirectAttributes redirectAttributes) {
        InfoNord infoNord = infoNordService.findOne(infoId);
        infoNordService.delete(infoNord);

        redirectAttributes.addFlashAttribute("flash",new FlashMessage("Upplýsingum hefur verið eytt!", FlashMessage.Status.SUCCESS));


        return "redirect:/about";
    }

    /**
     * Form for adding new information about the board
     * @param model the model
     * @return a webpage with a form for adding new information
     */
    @RequestMapping("/infoBoard/add")
    public String formNewInfoBoard(Model model) {
        if(!model.containsAttribute("infoBoard")) {
            model.addAttribute("infoBoard", new InfoBoard());
        }

        // Add model attributes needed for new form
        model.addAttribute("action", "/infoBoard/save");
        model.addAttribute("heading", "Nýjar upplýsingar");
        model.addAttribute("submit","Vista upplýsingar");

        return "information/boardForm";
    }

    /**
     * Edits information if valid data is received TODO: validate the received data
     * @param infoId the id of the information to be edited
     * @param model  the model
     * @return a website with a form to edit
     */
    @RequestMapping("/infoBoard/{infoId}/edit")
    public String formEditInfoBoard(@PathVariable Long infoId, Model model) {
        if(!model.containsAttribute("infoBoard")) {
            model.addAttribute("infoBoard", infoBoardService.findOne(infoId));
        }

        model.addAttribute("action", String.format("/infoBoard/%s", infoId));
        model.addAttribute("heading", "Breyta upplýsingum");
        model.addAttribute("submit","Uppfæra upplýsingar");

        return "information/boardForm";
    }

    /**
     * Add new information if valid data is received TODO: validate the received data
     * @param infoBoard the information from the user input that is to be added
     * @return back to the main page
     */
    @RequestMapping(value = "/infoBoard/save", method = RequestMethod.POST)
    public String saveInfoBoard(@Valid InfoBoard infoBoard,  BindingResult result, RedirectAttributes redirectAttributes, @RequestParam MultipartFile file) {
        if (result.hasErrors()) {
            // Include validation errors upon redirect
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.infoBoard", result);

            // Add ad if invalid was received
            redirectAttributes.addFlashAttribute("infoBoard", infoBoard);

            // Redirect back to the form
            return "redirect:/infoBoard/add";
        }

        // Save to database through aboutService
        infoBoardService.save(infoBoard, file);

        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Tókst að bæta við upplýsingum", FlashMessage.Status.SUCCESS));

        // Redirect browser
        return "redirect:/about";
    }

    /**
     * Updates information if valid data is received TODO: validate the received data
     * @param infoBoard the information to update
     * @return back to the information page
     */
    @RequestMapping(value = "/infoBoard/{infoId}", method = RequestMethod.POST)
    public String updateInfoBoard(@Valid InfoBoard infoBoard, BindingResult result, RedirectAttributes redirectAttributes, @RequestParam MultipartFile file) {
        if (result.hasErrors()) {
            // Include validation errors upon redirect
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.infoBoard", result);

            // Add data if invalid was received
            redirectAttributes.addFlashAttribute("infoBoard", infoBoard);

            // Redirect back to the form
            return String.format("redirect:/infoBoard/%s/edit",infoBoard.getId());
        }

        // Save to database through aboutService
        if(file.isEmpty()) {
            infoBoardService.save(infoBoard);
        } else {
            infoBoardService.save(infoBoard, file);
        }

        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Tókst að uppfæra upplýsingar", FlashMessage.Status.SUCCESS));

        return "redirect:/about";
    }

    /**
     * Deletes certain information if valid data is received TODO: validate the received data
     * @param infoId the id of the information item to be deleted
     * @return back to the information page
     */
    @RequestMapping("/infoBoard/{infoId}/delete")
    public String deleteInfoBoard(@PathVariable Long infoId, RedirectAttributes redirectAttributes) {
        InfoBoard infoBoard = infoBoardService.findOne(infoId);
        infoBoardService.delete(infoBoard);

        redirectAttributes.addFlashAttribute("flash",new FlashMessage("Upplýsingum hefur verið eytt!", FlashMessage.Status.SUCCESS));

        return "redirect:/about";
    }

    /**
     * Retrieves an image file from the database
     * @param infoBoardId the id of the image file
     * @return a single image file from the database
     */
    @RequestMapping("/infoBoard/{infoBoardId}.jpg")
    @ResponseBody
    public byte[] boardImage(@PathVariable Long infoBoardId) {
        return infoBoardService.findOne(infoBoardId).getBytes();
    }
}
