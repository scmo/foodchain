package ch.foodchain.meattracking.model;


import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
    @Table(name = "cow")
    public class Cow {

        @Id
        @GeneratedValue(strategy= GenerationType.AUTO)
        private Long id;

        @Column(name = "name")
        private String name;

        @Column(name = "animal_id", unique = true)
        private String animalId;

        @Column(name = "picture")
        private String picture;

        @Column(name = "breed")
        private String breed;

        @Column(name = "description")
        private String description;

        @Column(name = "birthdate")
        private Date birthdate;

        @ManyToOne()
        @NotNull
        private Farm farm;

        @OneToMany(mappedBy = "cow")
        private List<MovementMeasurement> movementMeasurements;


        @OneToMany(mappedBy = "cow")
        private List<CowEvent> events;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Farm getFarm() {
            return farm;
        }

        public void setFarm(Farm farm) {
            this.farm = farm;
        }

        public List<MovementMeasurement> getMovementMeasurements() {
            return movementMeasurements;
        }

        public void setMovementMeasurements(List<MovementMeasurement> movementMeasurements) {
            this.movementMeasurements = movementMeasurements;
        }

        public String getBreed() {
            return breed;
        }

        public void setBreed(String breed) {
            this.breed = breed;
        }

        public Date getBirthdate() {
            return birthdate;
        }

        public void setBirthdate(Date birthdate) {
            this.birthdate = birthdate;
        }

        public List<CowEvent> getEvents() {
            return events;
        }

        public void setEvents(List<CowEvent> events) {
            this.events = events;
        }
}

