package ch.foodchain.meattracking.service;

import ch.foodchain.meattracking.model.Cow;
import ch.foodchain.meattracking.model.CowEvent;
import ch.foodchain.meattracking.transfer.CowEventDto;
import org.springframework.stereotype.Service;


@Service
public class CowEventService {

    public CowEvent convertToEntity(CowEventDto eventDto, Cow cow){

        CowEvent ce = new CowEvent();

        ce.setCow(cow);
        ce.setEvent(eventDto.getEvent());
        ce.setTimestamp(eventDto.getTimestamp());

        return ce;

    }

}
