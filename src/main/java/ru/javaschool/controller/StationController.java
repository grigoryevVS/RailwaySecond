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
import ru.javaschool.model.entities.Station;
import ru.javaschool.services.StationService;

import javax.validation.Valid;

@Controller
@RequestMapping("/stationView")
public class StationController {

    @Autowired
    private StationService stationService;

    /**
     * Get station list.
     *
     * @param model - model of view.
     * @return - target url
     */
    @RequestMapping("/stations")
    public String getStationList(Model model) {
        model.addAttribute("station", new Station());
        model.addAttribute("stationList", stationService.getAllStations());
        return "stationView/stations";
    }

    /**
     * Get station list for custom user.
     *
     * @param model - model of view.
     * @return - target url
     */
    @RequestMapping("/stationIndex")
    public String index(Model model) {
        model.addAttribute("station", new Station());
        model.addAttribute("stationList", stationService.getAllStations());
        return "stationView/stationIndex";
    }

    /**
     * Apply new url, which will be the form,
     * to set needed fields, to create station.
     *
     * @param model - - model of view.
     * @return - target url
     */
    @RequestMapping(value = "/createStation")
    public String createStation(Model model) {
        model.addAttribute("station", new Station());
        return "stationView/createStation";
    }

    /**
     * Create new station and redirect us to central view.
     *
     * @param station - target station to be created
     * @return - redirect url.
     */
    @RequestMapping(value = "/add")
    public String addStation(@Valid @ModelAttribute("station") Station station, RedirectAttributes redAttr) {
        if (stationService.createStation(station)) {
            return "redirect:/stationView/stations";
        } else {
            redAttr.addFlashAttribute("msg", "Such station is already exist!");
            return "redirect:/stationView/createStation";
        }
    }

    /**
     * Delete station from the database
     *
     * @param stationId - targets identifier
     * @return - redirect url.
     */
    @RequestMapping("/delete/{stationId}")
    public String deleteStation(@PathVariable("stationId") Long stationId, RedirectAttributes redAttr) {
        Station station = stationService.findStation(stationId);
        if (station != null) {
            if (!stationService.deleteStation(station)) {
                redAttr.addFlashAttribute("msg", "Station " + station.getName() + " exist in some route, you can't delete it!");
                return "redirect:/stationView/stations";
            }
            redAttr.addFlashAttribute("msg", "Delete station " + station.getName() + " successful!");
            return "redirect:/stationView/stations";
        }
        return "error404";
    }

    /**
     * Throw us to the update form
     *
     * @param stationId - targets identifier
     * @param model     - model of view.
     * @return - target url.
     */
    @RequestMapping("/updateStation/{stationId}")
    public String updateStation(@PathVariable("stationId") Long stationId, Model model) {
        Station station = stationService.findStation(stationId);
        if (station == null) {
            return "error404";
        }
        model.addAttribute("station", station);
        return "stationView/updateStation";
    }

    /**
     * Update station
     *
     * @param station - target station to update
     * @return - redirect url.
     */
    @RequestMapping(value = "/refresh", method = RequestMethod.POST)
    public String refreshStation(@Valid @ModelAttribute("station") Station station, BindingResult result,
                                 RedirectAttributes redAttr) {
        if (result.hasErrors()) {
            redAttr.addFlashAttribute("msg", "Wrong data!");
            return "redirect:/stationView/updateStation/" + station.getStationId();
        }
        if(stationService.getStationByName(station.getName()) != null){
            redAttr.addFlashAttribute("msg", "Such station name is already exist!");
            return "redirect:/stationView/updateStation/" + station.getStationId();
        }
        stationService.updateStation(station);
        return "redirect:/stationView/stations";
    }
}
