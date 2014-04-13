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

@Controller
public class TrainController {

    @Autowired
    private TrainService trainService;

    @RequestMapping("/trains")
    public String getTrainList(Model model) {
        model.addAttribute("train", new Train());
        model.addAttribute("trainList", trainService.getAllTrains());
        return "/trains";
    }

    @RequestMapping(value = "/createTrain", method = RequestMethod.POST)
    public String createTrain(@ModelAttribute("train")Train train) {
        trainService.createTrain(train);
        return "redirect:/trains";
    }

    @RequestMapping(value = "/deleteTrain/{trainId}")
    public String deleteTrain(@PathVariable("trainId") Long trainId){
        trainService.deleteTrain(trainId);
        return "redirect:/trains";
    }
}
