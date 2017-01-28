package ch.foodchain.meattracking.model;

import ch.foodchain.meattracking.helpers.StepTypes;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;


@Entity
@Table(name = "movement_measurement")
public class MovementMeasurement {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @ManyToOne()
    @NotNull
    private Cow cow;

    @Column(name = "nr_of_steps")
    private long nrOfSteps;

    @Column(name = "step_types")
    private StepTypes stepTypes;

    @Column(name = "time_end")
    private LocalDateTime timeEnd;

    @Column(name = "duration_in_seconds")
    private long durationInSeconds;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonIgnore
    public Cow getCow() {
        return cow;
    }

    public void setCow(Cow cow) {
        this.cow = cow;
    }

    public long getNrOfSteps() {
        return nrOfSteps;
    }

    public void setNrOfSteps(long nr_of_steps) {
        this.nrOfSteps = nr_of_steps;
    }

    public StepTypes getStepTypes() {
        return stepTypes;
    }

    public void setStepTypes(StepTypes stepTypes) {
        this.stepTypes = stepTypes;
    }

    public long getDurationInSeconds() {
        return durationInSeconds;
    }

    public void setDurationInSeconds(long durationInSeconds) {
        this.durationInSeconds = durationInSeconds;
    }

    public LocalDateTime getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(LocalDateTime timeEnd) {
        this.timeEnd = timeEnd;
    }
}
