package net.engineeringdigest.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName) {
           User user = userService.findByUserName(userName);
           journalEntry.setDate((LocalDateTime.now()));
           JournalEntry saved = journalEntryRepository.save(journalEntry);
           List<JournalEntry> list = user.getJournalEntryList();
           list = new ArrayList<>();
           list.add(journalEntry);
           user.setJournalEntryList(list);
           userService.saveNewUser(user);
    }

    public void saveEntry(JournalEntry journalEntry) {
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> findAll() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }

    @Transactional
    public boolean deleteById(ObjectId id,String userName) {
        boolean removed = false;
        try{
            User user = userService.findByUserName(userName);
            removed = user.getJournalEntryList().removeIf(journalEntry -> journalEntry.getId().equals(id));
            if (removed) {
                userService.saveUser(user);
                journalEntryRepository.deleteById(id);
            }
        }catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("Error deleting journal entry",e);
        }
        return removed;
    }


}
