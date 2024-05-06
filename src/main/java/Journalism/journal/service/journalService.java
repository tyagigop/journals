package Journalism.journal.service;


import Journalism.journal.entity.JournalEntry;
import Journalism.journal.entity.User;
import Journalism.journal.repository.journalRepository;
import Journalism.journal.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class journalService {

    @Autowired
    private journalRepository journalrepository;

    @Autowired
    private userRepository user_repository;

    @Transactional
    public void updateData(JournalEntry journalEntry,String username){
        journalEntry.setDate(LocalDateTime.now());
        JournalEntry saved = journalrepository.save(journalEntry);
        User user = user_repository.findByusername(username);
        user.getJournalEntries().add(saved);
        user_repository.save(user);
    }

    public List<JournalEntry> getAll(String username){
        User user = user_repository.findByusername(username);

        return user.getJournalEntries();
    }
    public void updateJournalById(String id,JournalEntry updatedEntry){
        JournalEntry entry = journalrepository.findById(id).orElse(null);
        if(entry !=null){
            entry.setName(updatedEntry.getName()!=null ? updatedEntry.getName() : entry.getName());
            entry.setContent(updatedEntry.getContent()!=null ? updatedEntry.getContent():entry.getContent());
            journalrepository.save(entry);
        }
    }
    public void deleteById(String id,String username){

        User user = user_repository.findByusername(username);
        boolean removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        if(removed){
            user_repository.save(user);
            journalrepository.deleteById(id);
        }
    }
    public Optional<JournalEntry> getById(String id){
        return journalrepository.findById(id);
    }

}
