package ru.skypro.lessons.springboot.weblibrary.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.lessons.springboot.weblibrary.model.Position;

import java.util.Optional;

@Repository
public interface PositionRepository extends CrudRepository<Position, Integer> {

    @Query(value = "SELECT * FROM positions WHERE position = :position", nativeQuery = true)
    Optional<Position> getEmployeesByPosition(String position);
}
