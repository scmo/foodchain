package ch.foodchain.meattracking.transfer;

import ch.foodchain.meattracking.helpers.StepTypes;

import javax.validation.constraints.NotNull;

public class MovementMeasurementDto {

    @NotNull
    private Long steps;

    @NotNull
    private StepTypes steptype;

    @NotNull
    private long duration; // in seconds since last movement

    public Long getSteps() {
        return steps;
    }

    public void setSteps(Long steps) {
        this.steps = steps;
    }

    public StepTypes getSteptype() {
        return steptype;
    }

    public void setSteptype(StepTypes steptype) {
        this.steptype = steptype;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }
}