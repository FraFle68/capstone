package de.neuefische.backend.repository;

import de.neuefische.backend.model.GameMap;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends MongoRepository<GameMap, String> {

}
