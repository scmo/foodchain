package ch.foodchain.meattracking.model;

import javax.persistence.Column;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "cow_event")

public class CowEvent {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @JsonFormat(pattern="dd-MM-yyyy")
    @Column(name = "timestamp")
    private Date timestamp;

    @Column(name = "event")
    private String event;

    @ManyToOne()
    @NotNull
    private Cow cow;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    @JsonIgnore
    public Cow getCow() {
        return cow;
    }

    public void setCow(Cow cow) {
        this.cow = cow;
    }
}
