package ru.gost.egfdem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gost.egfdem.entity.StationEntity;

@Repository
public interface StationRepository extends JpaRepository<StationEntity, Long> {
}
