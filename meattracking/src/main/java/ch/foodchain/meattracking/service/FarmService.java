package ch.foodchain.meattracking.service;

import ch.foodchain.meattracking.model.Farm;
import ch.foodchain.meattracking.transfer.FarmDto;
import org.springframework.stereotype.Service;


@Service
public class FarmService {

    public Farm convertToEntity(FarmDto farmDto) {
        Farm fm = new Farm();
        fm.setDescription(farmDto.getDescription());
        fm.setName(farmDto.getName());
        fm.setPicture(farmDto.getImage());
        fm.setAddress(farmDto.getAddress());
        fm.setLongitude(farmDto.getLongitude());
        fm.setLatitude(farmDto.getLatitude());
        return fm;

    }
}

