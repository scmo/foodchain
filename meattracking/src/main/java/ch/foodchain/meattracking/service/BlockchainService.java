package ch.foodchain.meattracking.service;

import ch.foodchain.meattracking.ethereum.SmartContract;
import ch.foodchain.meattracking.helpers.StepTypes;
import ch.foodchain.meattracking.model.Cow;
import ch.foodchain.meattracking.model.MovementMeasurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlockchainService {

    @Autowired
    private SmartContract smartContract;

    public boolean saveCowMovementToChain(Cow cow, MovementMeasurement mm) {
        String farmID = cow.getFarm().getName();
        String cowID = cow.getAnimalId();
        Integer newSteps = Math.toIntExact(mm.getNrOfSteps());

        // Track time outside/inside
        int timeInside = 0;
        int timeOutside = 0;
        if (mm.getStepTypes().equals(StepTypes.INSIDE)) {
            timeInside += mm.getDurationInSeconds();
        } else if (mm.getStepTypes().equals(StepTypes.OUTSIDE)) {
            timeOutside += mm.getDurationInSeconds();
        }

        try {
            smartContract.update(farmID, cowID, cow.getFarm().getAddress(), newSteps, timeOutside, timeInside);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
