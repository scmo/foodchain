package ch.foodchain.meattracking.repository;

import ch.foodchain.meattracking.model.CowEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CowEventRepository extends JpaRepository<CowEvent, Long> {
}
