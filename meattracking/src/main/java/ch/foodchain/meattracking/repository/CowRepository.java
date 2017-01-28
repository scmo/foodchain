package ch.foodchain.meattracking.repository;

import ch.foodchain.meattracking.model.Cow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CowRepository extends JpaRepository<Cow, Long> {

    Cow findById(long cowId);
    Cow findByAnimalId(String animalId);

}