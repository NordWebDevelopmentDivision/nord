package is.nord.controller;

import is.nord.model.InfoBoard;
import is.nord.model.InfoNord;
import is.nord.service.InfoBoardService;
import is.nord.service.InfoNordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
        // Add model attributes needed for new form
        model.addAttribute("infoNord", new InfoNord());
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
        model.addAttribute("infoNord", infoNordService.findOne(infoId));
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
    public String saveInfoNord(InfoNord infoNord) {
        // Save to database through aboutService
        infoNordService.save(infoNord);

        // Redirect browser
        return "redirect:/about";
    }

    /**
     * Updates information if valid data is received TODO: validate the received data
     * @param infoNord the information to update
     * @return back to the infoNord page
     */
    @RequestMapping(value = "/infoNord/{infoId}", method = RequestMethod.POST)
    public String updateInfoNord(InfoNord infoNord) {
        // Save to database through aboutService
        infoNordService.save(infoNord);

        return "redirect:/about";
    }

    /**
     * Deletes certain information if valid data is received TODO: validate the received data
     * @param infoId the id of the information item to be deleted
     * @return back to the information page
     */
    @RequestMapping("/infoNord/{infoId}/delete")
    public String deleteInfoNord(@PathVariable Long infoId) {
        InfoNord infoNord = infoNordService.findOne(infoId);
        infoNordService.delete(infoNord);

        return "redirect:/about";
    }

    /**
     * Form for adding new information about the board
     * @param model the model
     * @return a webpage with a form for adding new information
     */
    @RequestMapping("/infoBoard/add")
    public String formNewInfoBoard(Model model) {
        // Add model attributes needed for new form
        model.addAttribute("infoBoard", new InfoBoard());
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
        model.addAttribute("infoBoard", infoBoardService.findOne(infoId));
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
    public String saveInfoBoard(InfoBoard infoBoard, @RequestParam MultipartFile file) {
        // Save to database through aboutService
        infoBoardService.save(infoBoard, file);

        // Redirect browser
        return "redirect:/about";
    }

    /**
     * Updates information if valid data is received TODO: validate the received data
     * @param infoBoard the information to update
     * @return back to the information page
     */
    @RequestMapping(value = "/infoBoard/{infoId}", method = RequestMethod.POST)
    public String updateInfoBoard(InfoBoard infoBoard, @RequestParam MultipartFile file) {
        // Save to database through aboutService
        if(file.isEmpty()) {
            System.out.println("===============================banani");
            infoBoardService.save(infoBoard);
            System.out.println("===============================api");
        } else {
            infoBoardService.save(infoBoard, file);
        }

        return "redirect:/about";
    }

    /**
     * Deletes certain information if valid data is received TODO: validate the received data
     * @param infoId the id of the information item to be deleted
     * @return back to the information page
     */
    @RequestMapping("/infoBoard/{infoId}/delete")
    public String deleteInfoBoard(@PathVariable Long infoId) {
        InfoBoard infoBoard = infoBoardService.findOne(infoId);
        infoBoardService.delete(infoBoard);

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
