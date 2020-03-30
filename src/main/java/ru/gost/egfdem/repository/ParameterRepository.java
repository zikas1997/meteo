package ru.gost.egfdem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gost.egfdem.entity.ParameterEntity;

@Repository
public interface ParameterRepository extends JpaRepository<ParameterEntity, Long> {
    ParameterEntity findByTitle(String title);
}
