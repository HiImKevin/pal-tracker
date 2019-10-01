package io.pivotal.pal.tracker;

import java.util.*;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private Map<Long, TimeEntry> database = new HashMap<>();
    private long timeEntryId = 1L;

    @Override
    public TimeEntry create(TimeEntry timeEntry) {

        TimeEntry createdTimeEntry = new TimeEntry(timeEntryId,
                                                timeEntry.getProjectId(),
                                                timeEntry.getUserId(),
                                                timeEntry.getDate(),
                                                timeEntry.getHours());
        database.put(timeEntryId, createdTimeEntry);
        TimeEntry getTimeEntry = database.get(timeEntryId);

        timeEntryId++;
        return getTimeEntry;

    }

    @Override
    public TimeEntry find(long timeEntryId) {
        return database.get(timeEntryId);
    }

    @Override
    public List<TimeEntry> list() {
        return new ArrayList<>(database.values());
    }

    @Override
    public TimeEntry update(long timeEntryId, TimeEntry timeEntry) {
        if (!database.containsKey(timeEntryId)) {
            return null;
        }

        TimeEntry timeEntryOld = database.get(timeEntryId);
        timeEntry.setId(timeEntryOld.getId());
        database.put(timeEntryId, timeEntry);

        return database.get(timeEntryId);


    }

    @Override
    public void delete(long timeEntryId) {
        TimeEntry deletedTimeEntry = database.get(timeEntryId);
        database.remove(timeEntryId, deletedTimeEntry);
    }
}
