package ch.foodchain.meattracking.repository;

import ch.foodchain.meattracking.model.Farm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FarmRepository extends JpaRepository<Farm, String> {

    Farm findById(String farmId);

}