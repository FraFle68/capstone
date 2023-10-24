package de.neuefische.backend.repository;

import de.neuefische.backend.game.core.Room;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends MongoRepository<Room, String> {

}