package ch.foodchain.meattracking.controller;

import ch.foodchain.meattracking.model.Farm;
import ch.foodchain.meattracking.repository.FarmRepository;
import ch.foodchain.meattracking.service.FarmService;
import ch.foodchain.meattracking.transfer.FarmDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Produces;
import java.util.List;

@Controller
@EnableAutoConfiguration
@RequestMapping("/farm")
public class FarmController {

    private Validator genericValidator;
    private FarmRepository farmRepository;
    private FarmService farmService;

    @Autowired
	public FarmController(FarmRepository farmRepository, FarmService farmService){
        this.farmRepository = farmRepository;
        this.farmService = farmService;

    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    @Produces("application/json")
    List<Farm> farm() { return farmRepository.findAll(); }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    @Produces("application/json")
    Farm crateFarm(@Validated @RequestBody FarmDto input) {
            HttpHeaders httpHeaders = new HttpHeaders();
            Farm farm = farmService.convertToEntity(input);
            Farm result = farmRepository.save(farm);
            return result;
        }

    @RequestMapping("/{farmId}/")
    @ResponseBody
    Farm getFarm(@PathVariable(value="farmId") String farmId) {
        Farm result = farmRepository.findById(farmId);
        return result;
	}


}
