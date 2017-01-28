package ch.foodchain.meattracking.transfer;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class CowDto {

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private String animalId;

    @NotNull
    private String picture;

    @NotNull
    private String breed;

    private Date birthdate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAnimalId() {
        return animalId;
    }

    public void setAnimalId(String animalId) {
        this.animalId = animalId;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }
}
