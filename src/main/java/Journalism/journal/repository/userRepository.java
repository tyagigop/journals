package Journalism.journal.repository;

import Journalism.journal.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.bson.types.ObjectId;

public interface userRepository extends MongoRepository<User,ObjectId> {
    User findByusername(String username);
}
