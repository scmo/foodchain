package ch.foodchain.meattracking.controller;

import ch.foodchain.meattracking.model.Cow;
import ch.foodchain.meattracking.model.CowEvent;
import ch.foodchain.meattracking.model.MovementMeasurement;
import ch.foodchain.meattracking.repository.CowEventRepository;
import ch.foodchain.meattracking.repository.CowRepository;
import ch.foodchain.meattracking.repository.MovementMeasurementRepository;
import ch.foodchain.meattracking.service.CowEventService;
import ch.foodchain.meattracking.service.BlockchainService;
import ch.foodchain.meattracking.service.CowService;
import ch.foodchain.meattracking.service.MovementMeasurementService;
import ch.foodchain.meattracking.transfer.CowDto;
import ch.foodchain.meattracking.transfer.CowEventDto;
import ch.foodchain.meattracking.transfer.MovementMeasurementDto;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Produces;
import java.util.List;

@CrossOrigin
@Controller
@EnableAutoConfiguration
@RequestMapping("/cow")
public class CowController {
    private static Logger LOG = Logger.getLogger(CowController.class);

    private MovementMeasurementService movementService;
    private CowEventService eventService;
    private CowEventRepository eventRepository;
    private CowService cowCervice;
    private CowRepository cowRepository;
    private MovementMeasurementRepository movementRepository;
    private BlockchainService blockchainService;


    @Autowired
    public CowController(CowRepository cowRepository, CowService cowCervice,
                         MovementMeasurementService movementService, MovementMeasurementRepository movementRepository,
                         CowEventService eventService, CowEventRepository eventRepository, BlockchainService blockchainService){
        this.cowRepository = cowRepository;
        this.cowCervice = cowCervice;
        this.movementService = movementService;
        this.movementRepository = movementRepository;
        this.eventService = eventService;
        this.eventRepository = eventRepository;
        this.blockchainService = blockchainService;

    }


    @RequestMapping(value = "/{animalId}/", method = RequestMethod.GET)
    @ResponseBody
    @Produces("application/json")
    public ResponseEntity<Cow> getCow(@PathVariable(value="animalId") String animalId) {
        Cow c = cowRepository.findByAnimalId(animalId);
        if (c == null) {
            return ResponseEntity.<MovementMeasurement>status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(c);
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


    @RequestMapping(value = "/{animalId}/event", method = RequestMethod.POST)
    @ResponseBody
    @Produces("application/json")
    public ResponseEntity<CowEvent> createcowEvent(@PathVariable(value="animalId") String animalId, @Validated @RequestBody CowEventDto input) {
        Cow c = cowRepository.findByAnimalId(animalId);
        if (c == null) {
            LOG.error("Bad request: cow of measurement not found (" + animalId + ")");
            return ResponseEntity.<MovementMeasurement>status(HttpStatus.BAD_REQUEST).body(null);
        }
        CowEvent ce = eventService.convertToEntity(input, c);
        CowEvent result = eventRepository.save(ce);

        // TODO: Save measuremnt in blockchain. For hack, after every call, for productino, once per day


        return ResponseEntity.ok(result);
    }


    @RequestMapping(value = "/{animalId}/movement-measurement", method = RequestMethod.POST)
    @ResponseBody
    @Produces("application/json")
    public ResponseEntity<MovementMeasurement> crateMovementMeasurement(@PathVariable(value="animalId") String animalId, @Validated @RequestBody MovementMeasurementDto input) {
        Cow c = cowRepository.findByAnimalId(animalId);
        if (c == null) {
            LOG.error("Bad request: cow of measurement not found (" + animalId + ")");
            return ResponseEntity.<MovementMeasurement>status(HttpStatus.BAD_REQUEST).body(null);
        }
        MovementMeasurement mm = movementService.convertToEntity(input, c);
        MovementMeasurement result = movementRepository.save(mm);

        // TODO: Save measuremnt in blockchain. For hack: after every call, for production: once per day
        blockchainService.saveCowMovementToChain(c, mm);

        return ResponseEntity.ok(result);
    }



}
