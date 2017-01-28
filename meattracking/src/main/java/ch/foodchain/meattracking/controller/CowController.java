package ch.foodchain.meattracking.controller;

import ch.foodchain.meattracking.model.Cow;
import ch.foodchain.meattracking.model.MovementMeasurement;
import ch.foodchain.meattracking.repository.CowRepository;
import ch.foodchain.meattracking.repository.MovementMeasurementRepository;
import ch.foodchain.meattracking.service.CowService;
import ch.foodchain.meattracking.service.MovementMeasurementService;
import ch.foodchain.meattracking.transfer.CowDto;
import ch.foodchain.meattracking.transfer.MovementMeasurementDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Produces;
import java.util.List;


@Controller
@EnableAutoConfiguration
@RequestMapping("/cow")
public class CowController {

    private CowService cowCervice;
    private MovementMeasurementService movementService;
    private CowRepository cowRepository;
    private MovementMeasurementRepository movementRepository;


    @Autowired
    public CowController(CowRepository cowRepository, CowService cowCervice,
                         MovementMeasurementService movementService, MovementMeasurementRepository movementRepository){
        this.cowRepository = cowRepository;
        this.cowCervice = cowCervice;
        this.movementService = movementService;
        this.movementRepository = movementRepository;
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    @Produces("application/json")
    public List<Cow> getCows() {
        return cowRepository.findAll();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    @Produces("application/json")
    public Cow createCow(@Validated @RequestBody CowDto input) {
        Cow cow = cowCervice.convertToEntity(input);
        Cow result = cowRepository.save(cow);
        return result;
    }

    @RequestMapping(value = "/{animalId}/movement-measurement", method = RequestMethod.POST)
    @ResponseBody
    @Produces("application/json")
    public MovementMeasurement crateMovementMeasurement(@PathVariable(value="animalId") String animalId, @Validated @RequestBody MovementMeasurementDto input) {
        System.out.println(animalId);
        Cow c = cowRepository.findByAnimalId(animalId);
        MovementMeasurement mm = movementService.convertToEntity(input, c);
        MovementMeasurement result = movementRepository.save(mm);
        return result;
    }




}
