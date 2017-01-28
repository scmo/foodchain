package ch.foodchain.meattracking.repository;

import ch.foodchain.meattracking.model.MovementMeasurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface MovementMeasurementRepository extends JpaRepository<MovementMeasurement, Integer> {

    MovementMeasurement findById(long movementMeasurementId);

    //Set<MovementMeasurement> findByCowId(long cowId);

}