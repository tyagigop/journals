package Journalism.journal.repository;

import Journalism.journal.entity.JournalEntry;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface journalRepository extends MongoRepository<JournalEntry,String> {

}
