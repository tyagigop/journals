package Journalism.journal.entity;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "journalEntries")
@Getter
@Setter
public class JournalEntry {
    private String id;
    private String name;
    private String content;
    private LocalDateTime date;
}
