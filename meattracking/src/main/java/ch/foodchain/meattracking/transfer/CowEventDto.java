package ch.foodchain.meattracking.transfer;

import javax.validation.constraints.NotNull;
import java.sql.Date;

public class CowEventDto {

    @NotNull
    private String event;

    @NotNull
    private Date timestamp;

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
