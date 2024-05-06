package Journalism.journal.controller;

import Journalism.journal.entity.JournalEntry;
import Journalism.journal.service.journalService;
import Journalism.journal.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/journal")
public class journalController {

    @Autowired
    private journalService journalservice;

    @Autowired
    private userService user_service;


    @GetMapping("/{username}")
    public ResponseEntity<List<JournalEntry>> getData(@PathVariable String username){
        List<JournalEntry> UserJournals = journalservice.getAll(username);
        if(!UserJournals.isEmpty()){
            return new ResponseEntity<>(UserJournals,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/{username}")
    public ResponseEntity<JournalEntry> setData(@RequestBody JournalEntry journal,@PathVariable String username){
        try {
            journalservice.updateData(journal,username);
            return new ResponseEntity<JournalEntry>(journal,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<JournalEntry>(HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/updateById/{myId}")
    public ResponseEntity<?> updateData(@PathVariable String myId,@RequestBody JournalEntry newEntry){
        journalservice.updateJournalById(myId,newEntry);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("delete/{username}/{myId}")
    public ResponseEntity<?> deleteJournal(@PathVariable String myId,@PathVariable String username){
        journalservice.deleteById(myId,username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("getById/{id}")
    public ResponseEntity<JournalEntry> getById(@PathVariable String id){
        if(journalservice.getById(id).isPresent()){
            return new ResponseEntity<JournalEntry>(journalservice.getById(id).get(), HttpStatus.OK);

        }
        else{
            return new ResponseEntity<JournalEntry>(HttpStatus.NOT_FOUND);
        }
    }

}
