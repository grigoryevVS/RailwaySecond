package ru.javaschool.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.javaschool.model.entities.Train;
import ru.javaschool.services.TrainService;

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
     * This method, open new page, where will be create train.
     *
     * @param model - web model of view
     * @return - needed url, which will return.
     */
    @RequestMapping(value = "/createTrain", method = RequestMethod.POST)
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
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addTrain(@ModelAttribute("train") Train train) {
        if (trainService.createTrain(train)) {
            return "redirect:/trainView/trains";
        } else {
            return "redirect:/trainView/trains";    // TODO  create error page, will be include info about double entty and button home.
        }
    }

    /**
     * delete concrete train from the database.
     *
     * @param trainId - passed from the jsp, by what primary key will be deleted train.
     * @return - redirection jsp view, which will be appear when delete acts.
     */
    @RequestMapping(value = "/delete/{trainId}")
    public String deleteTrain(@PathVariable("trainId") Long trainId) {
        trainService.deleteTrain(trainId);
        return "redirect:/trainView/trains";
    }

    /**
     * Update train, opens page with concrete form.
     *
     * @param trainId - to update entity, its primary key
     * @param model   - model of view
     * @return -  url.
     */
    @RequestMapping(value = "/updateTrain/{trainId}")
    public String updateTrain(@PathVariable("trainId") Long trainId,
                              Model model) {
        model.addAttribute("train", trainService.findTrain(trainId));
        return "trainView/updateTrain";
    }

    /**
     * Refresh, updating train in the database, redirects to main page
     *
     * @param train - concrete train, which we need to update.
     * @return - redirecting url.
     */
    @RequestMapping(value = "/refresh", method = RequestMethod.POST)
    public String refreshTrain(@ModelAttribute("station") Train train) {
        trainService.updateTrain(train);
        return "redirect:/trainView/trains";
    }
}
