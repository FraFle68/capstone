package de.neuefische.backend.repository;

import de.neuefische.backend.model.Avatar;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends MongoRepository<Avatar, String> {

}
