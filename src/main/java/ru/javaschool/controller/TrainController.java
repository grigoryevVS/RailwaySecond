package ru.javaschool.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.javaschool.model.entities.Train;
import ru.javaschool.services.TrainService;

import javax.validation.Valid;

/**
 * This controller implements actions with trainService,
 * layer between jsp views and services.
 */
@Controller
@RequestMapping("/trainView")
public class TrainController {

    @Autowired
    private TrainService trainService;

    /**
     * Get all trains from database.
     *
     * @param model - model of parameters, which will be used in the service layer.
     * @return - concrete url of jsp which will be redirected to.
     */
    @RequestMapping("/trains")
    public String getTrainList(Model model) {
        model.addAttribute("train", new Train());
        model.addAttribute("trainList", trainService.getAllTrains());
        return "trainView/trains";
    }

    /**
     * Get all trains for custom user.
     *
     * @param model - model of parameters, which will be used in the service layer.
     * @return - concrete url of jsp which will be redirected to.
     */
    @RequestMapping("/trainIndex")
    public String index(Model model) {
        model.addAttribute("train", new Train());
        model.addAttribute("trainList", trainService.getAllTrains());
        return "trainView/trainIndex";
    }

    /**
     * This method, open new page, where will be create train.
     *
     * @param model - web model of view
     * @return - needed url, which will return.
     */
    @RequestMapping(value = "/createTrain")
    public String createTrain(Model model) {
        model.addAttribute("train", new Train());
        return "trainView/createTrain";
    }

    /**
     * Create train in the database, if it is not exists yet.
     *
     * @param train - concrete train, which we need to input.
     * @return - redirection url, which will be appear i the browser.
     */
    @RequestMapping(value = "/add")
    public String addTrain(@Valid @ModelAttribute("train") Train train, BindingResult result, RedirectAttributes redAttr) {

        if (result.hasErrors()) {
            redAttr.addFlashAttribute("msg", "Wrong data!");
            return "redirect:/trainView/createTrain";
        }
        if (train == null || train.getName().equals("")) {
            redAttr.addFlashAttribute("msg", "Name can't be empty!");
            return "redirect:/trainView/createTrain";
        }

        if (0 >= train.getNumberOfSeats() || train.getNumberOfSeats() > 400) {
            redAttr.addFlashAttribute("msg", "Trains capacity must be between 1-400");
            return "redirect:/trainView/createTrain";
        }
        if (trainService.createTrain(train)) {
            redAttr.addFlashAttribute("msg", "Create train " + train.getName() + " successful!");
            return "trainView/trains";
        } else {
            redAttr.addFlashAttribute("msg", "Such train is already exist or wrong capacity of train, it must be between 1-400");
            return "redirect:/trainView/createTrain";
        }
    }

    /**
     * delete concrete train from the database.
     *
     * @param trainId - passed from the jsp, by what primary key will be deleted train.
     * @return - redirection jsp view, which will be appear when delete acts.
     */
    @RequestMapping(value = "/delete/{trainId}")
    public String deleteTrain(@PathVariable("trainId") Long trainId, RedirectAttributes redAttr) {

        Train train = trainService.findTrain(trainId);
        if (train != null) {
            if (!trainService.deleteTrain(trainId)) {
                redAttr.addFlashAttribute("msg", "Train " + train.getName() + " exist in schedule, you can't delete it!");
                return "redirect:/trainView/trains";
            } else {
                redAttr.addFlashAttribute("msg", "Delete train " + train.getName() + " successful!");
                return "redirect:/trainView/trains";
            }
        }
        return "error404";
    }

    /**
     * Update train, opens page with concrete form.
     *
     * @param trainId - to update entity, its primary key
     * @param model   - model of view
     * @return -  url.
     */
    @RequestMapping(value = "/updateTrain/{trainId}")
    public String updateTrain(@PathVariable("trainId") Long trainId, Model model) {

        Train train = trainService.findTrain(trainId);
        if (train == null) {
            return "error404";
        }
        model.addAttribute("train", train);
        return "trainView/updateTrain";
    }

    /**
     * Refresh, updating train in the database, redirects to main page
     *
     * @param train - concrete train, which we need to update.
     * @return - redirecting url.
     */
    @RequestMapping(value = "/refresh", method = RequestMethod.POST)
    public String refreshTrain(@Valid @ModelAttribute("train") Train train, BindingResult result, RedirectAttributes redAttr) {

        if (result.hasErrors()) {
            redAttr.addFlashAttribute("msg", "Wrong data!");
            return "redirect:/trainView/updateTrain/" + train.getTrainId();
        }
        Train checkTrain = trainService.getTrainByName(train.getName());
        if (checkTrain != null && checkTrain.getTrainId() != train.getTrainId()) {
            redAttr.addFlashAttribute("msg", "Such train name is already exist!");
            return "redirect:/trainView/updateTrain/" + train.getTrainId();
        }
        if (!trainService.updateTrain(train)) {
            redAttr.addFlashAttribute("msg", "Capacity must be more than bought tickets on it! now there are " + trainService.maxBoughtTicketsOnTrain(train.getTrainId()) + " sold tickets!");
            return "redirect:/trainView/updateTrain/" + train.getTrainId();
        }
        redAttr.addFlashAttribute("msg", "Update train " + train.getName() + " successful!");
        return "redirect:/trainView/trains";
    }
}
