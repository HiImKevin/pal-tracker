package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeEntryController {
    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping("/time-entries")
    public ResponseEntity create(@RequestBody TimeEntry timeEntry) {
        TimeEntry createdTimeEntry = timeEntryRepository.create(timeEntry);
        return new ResponseEntity<>(createdTimeEntry, HttpStatus.CREATED);
    }

    @GetMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<TimeEntry> read(@PathVariable long timeEntryId) {
        TimeEntry readTimeEntry = timeEntryRepository.find(timeEntryId);
        if (readTimeEntry == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(readTimeEntry, HttpStatus.OK);
    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> timeEntryList = timeEntryRepository.list();
        return new ResponseEntity<>(timeEntryList, HttpStatus.OK);
    }

    @PutMapping("/time-entries/{timeEntryId}")
    public ResponseEntity update(@PathVariable long timeEntryId, @RequestBody TimeEntry timeEntry) {
        TimeEntry updatedTimeEntry = timeEntryRepository.update(timeEntryId, timeEntry);
        if (updatedTimeEntry == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedTimeEntry, HttpStatus.OK);
    }

    @DeleteMapping("/time-entries/{timeEntryId}")
    public ResponseEntity delete(@PathVariable long timeEntryId) {
        timeEntryRepository.delete(timeEntryId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
