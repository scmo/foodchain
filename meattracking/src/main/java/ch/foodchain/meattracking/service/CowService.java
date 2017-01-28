package ch.foodchain.meattracking.service;

import ch.foodchain.meattracking.model.Cow;
import ch.foodchain.meattracking.transfer.CowDto;
import ch.foodchain.meattracking.transfer.FarmDto;
import org.springframework.stereotype.Service;


@Service
public class CowService {

    public Cow convertToEntity(CowDto cowDto) {
        Cow cow = new Cow();
        cow.setAnimalId(cowDto.getAnimalId());
        cow.setDescription(cowDto.getDescription());
        cow.setName(cowDto.getName());
        cow.setPicture(cowDto.getPicture());
        cow.setBreed(cowDto.getBreed());
        return cow;

    }
}

