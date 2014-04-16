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
public class TrainController {

    @Autowired
    private TrainService trainService;

    /**
     * Get all trains from database.
     * @param model - model of parameters, which will be used in the service layer.
     * @return - concrete url of jsp which will be redirected to.
     */
    @RequestMapping("/trains")
    public String getTrainList(Model model) {
        model.addAttribute("train", new Train());
        model.addAttribute("trainList", trainService.getAllTrains());
        return "trains";
    }

    /**
     * Create train in the database, if it is not exists yet.
     * @param train - concrete train, which we need to input.
     * @return - redirection url, which will be appear i the browser.
     */
    @RequestMapping(value = "/createTrain", method = RequestMethod.POST)
    public String createTrain(@ModelAttribute("train")Train train) {
        trainService.createTrain(train);
        return "redirect:/trains";
    }

    /**
     * delete concrete train from the database.
     * @param trainId - passed from the jsp, by what primary key will be deleted train.
     * @return - redirection jsp view, which will be appear when delete acts.
     */
    @RequestMapping(value = "/delete/{trainId}")
    public String deleteTrain(@PathVariable("trainId") Long trainId){
        trainService.deleteTrain(trainId);
        return "redirect:/trains";
    }

    @RequestMapping(value = "/update/{trainId}")
    public String updateTrain(@PathVariable("trainId") Long trainId) {
        trainService.updateTrain(trainId);
        return "redirect:/trains";
    }
}
