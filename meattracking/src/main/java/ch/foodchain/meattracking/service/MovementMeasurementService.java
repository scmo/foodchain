package ch.foodchain.meattracking.service;

import ch.foodchain.meattracking.model.Cow;
import ch.foodchain.meattracking.model.MovementMeasurement;
import ch.foodchain.meattracking.repository.CowRepository;
import ch.foodchain.meattracking.transfer.MovementMeasurementDto;
import org.springframework.stereotype.Service;

@Service
public class MovementMeasurementService {

    private CowRepository cowRepository;


    public MovementMeasurement convertToEntity(MovementMeasurementDto mmDto, Cow cow){
        MovementMeasurement mm = new MovementMeasurement();

        mm.setCow(cow);
        mm.setNrOfSteps(mmDto.getSteps());
        mm.setDurationInSeconds(mmDto.getDuration());
        mm.setNrOfSteps(mmDto.getSteps());
        mm.setStepTypes(mmDto.getSteptype());

        return mm;


    }

}
